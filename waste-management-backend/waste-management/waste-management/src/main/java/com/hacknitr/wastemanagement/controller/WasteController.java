package com.hacknitr.wastemanagement.controller;

import com.hacknitr.wastemanagement.model.Category;
import com.hacknitr.wastemanagement.model.SchedulePickup;
import com.hacknitr.wastemanagement.model.User;
import com.hacknitr.wastemanagement.model.WasteMaterial;
import com.hacknitr.wastemanagement.repository.UserRepository;
import com.hacknitr.wastemanagement.response.WasteMaterialResponse;
import com.hacknitr.wastemanagement.sevice.CategoryService;
import com.hacknitr.wastemanagement.sevice.SchedulePickupService;
import com.hacknitr.wastemanagement.sevice.UserService;
import com.hacknitr.wastemanagement.sevice.WasteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@RestController
@CrossOrigin("*")
@RequestMapping("/waste")
public class WasteController {

    @Autowired
    private WasteService wasteService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    @Autowired
    private SchedulePickupService schedulePickupService;

    @PostMapping(value="/",headers = "content-type=multipart/*")
    public ResponseEntity registerWaste(@RequestParam("wasteImage") MultipartFile file, @RequestParam("name") String name, @RequestParam("description") String description,@RequestParam("userId") Long userId,@RequestParam("categoryName") String categoryName) {
        //fetch category by name
        Category category=categoryService.getCategoryDetails(categoryName);
        WasteMaterial wasteMaterial = new WasteMaterial();
        System.out.println(name+" "+category.getId());
        wasteMaterial.setName(name);
        wasteMaterial.setDescription(description);
        wasteMaterial.setUserId(userId);
        wasteMaterial.setCategory(category);
        try {
            wasteMaterial.setPicByte(compressBytes(file.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();

        }
        this.wasteService.uploadWaste(wasteMaterial);
        //once the waste is uploaded successfully update credits to users account

        User theUser=userService.fetchUser(userId);
        theUser.setCredit(theUser.getCredit()+5);
        userRepository.save(theUser);
        return ResponseEntity.ok("waste material is uploaded  succesfully");
    }

    @PostMapping(value="/predict-waste",headers = "content-type=multipart/*")
    public ResponseEntity<String> predictWaste(@RequestParam("wasteImage") MultipartFile file){
        String imageURL = "https://i.imgur.com/PEEvqPN.png"; // Replace Image URL
        String API_KEY = "kvgQYhFnmZM1aB34bXb0"; // Your API Key
        String MODEL_ENDPOINT = "dataset/v"; // model endpoint

        // Upload URL
        String uploadURL = "https://detect.roboflow.com/" + MODEL_ENDPOINT + "?api_key=" + API_KEY + "&image="
                + URLEncoder.encode(imageURL, StandardCharsets.UTF_8);

        // Http Request
        HttpURLConnection connection = null;
        String line = null;
        try {
            // Configure connection to URL
            URL url = new URL(uploadURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            connection.setRequestProperty("Content-Length", Integer.toString(uploadURL.getBytes().length));
            connection.setRequestProperty("Content-Language", "en-US");
            connection.setUseCaches(false);
            connection.setDoOutput(true);

            // Send request
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(uploadURL);
            wr.close();

            // Get Response
            InputStream stream = new URL(uploadURL).openStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return ResponseEntity.ok(line);
    }

    @PostMapping("/schedule-pickUp")
    public ResponseEntity scheduleWaste(@RequestBody SchedulePickup schedulePickup){
        SchedulePickup schedulePickup1=schedulePickupService.schedulePickUp(schedulePickup);
        return ResponseEntity.ok("waste pick up is schedule successfully check your mail for further details");
    }

    @GetMapping("/schedule/{id}")
    public ResponseEntity<List<SchedulePickup>> getScheduleDetails(@PathVariable("id") Long userId){
       List<SchedulePickup> schedulePickup=this.schedulePickupService.getScheduleDetail(userId);
        return ResponseEntity.ok(schedulePickup);
    }

    @DeleteMapping("/schedule/{id}")
    public ResponseEntity deleteSchedulePickUp(@PathVariable("id") Long id){

        this.schedulePickupService.deleteSchedule(id);

        return ResponseEntity.ok("schedule is deleted successfully");
    }

    @GetMapping("/getAllWasteMaterial/{id}")
    public ResponseEntity<?> getAllWasteMaterialDetails(@PathVariable("id") Long userId){
        List<WasteMaterial> wasteMaterials=this.wasteService.getAllWasteMaterialsByUserId(userId);
        for(WasteMaterial wasteMaterial:wasteMaterials) {
            wasteMaterial.setPicByte(decompressBytes(wasteMaterial.getPicByte()));
        }
        return ResponseEntity.ok(wasteMaterials);
    }
    //This api is used to delete the waste material uploaded by the user
    @DeleteMapping("/{id}")
    public ResponseEntity deleteUploadedWaste(@PathVariable("id") Long wasteId) throws Exception {
        //collect waste material details of waste id
        WasteMaterial wasteMaterial=wasteService.getWasteDetails(wasteId);
        //delete waste credit from user account
        User theUser=userService.fetchUser(wasteMaterial.getUserId());
        theUser.setCredit(theUser.getCredit()-5);
        userRepository.save(theUser);
        this.wasteService.deleteWaste(wasteId);

        return ResponseEntity.ok("uploaded waste is deleted successfully");
    }

    @GetMapping("/getWasteDetails/{id}")
    public ResponseEntity getWasteProductDetails(@PathVariable("id") Long id){
        WasteMaterial wasteMaterial=this.wasteService.getWasteByUserId(id);
        WasteMaterialResponse wasteMaterialResponse=new WasteMaterialResponse();
        wasteMaterialResponse.setId(wasteMaterial.getId());
        wasteMaterialResponse.setName(wasteMaterial.getName());
        wasteMaterialResponse.setCategoryName(wasteMaterial.getCategory().getCategoryName());
        wasteMaterialResponse.setPicByte(decompressBytes(wasteMaterial.getPicByte()));
        wasteMaterialResponse.setUserId(wasteMaterial.getUserId());
        return ResponseEntity.ok(wasteMaterialResponse);
    }



    @GetMapping("/getAllWastes")
    public ResponseEntity<List<WasteMaterial>> getAllWastes(){
        List<WasteMaterial> wastes=wasteService.getAllWastes();
        for(WasteMaterial wasteMaterial:wastes) {
            wasteMaterial.setPicByte(decompressBytes(wasteMaterial.getPicByte()));
        }
        return ResponseEntity.ok(wastes);
    }

    // compress the image bytes before storing it in the database
    public static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
        return outputStream.toByteArray();
    }
    // uncompress the image bytes before returning it to the angular application
    public static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException ioe) {
        } catch (DataFormatException e) {
        }
        return outputStream.toByteArray();
    }



}
