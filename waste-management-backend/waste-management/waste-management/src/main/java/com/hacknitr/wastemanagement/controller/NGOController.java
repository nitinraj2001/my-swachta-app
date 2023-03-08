package com.hacknitr.wastemanagement.controller;

import com.hacknitr.wastemanagement.model.NGO;
import com.hacknitr.wastemanagement.model.User;
import com.hacknitr.wastemanagement.repository.UserRepository;
import com.hacknitr.wastemanagement.sevice.NGOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@RestController
@RequestMapping("/ngo")
public class NGOController {

    @Autowired
    private NGOService ngoService;

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private UserRepository userRepository;

    @PostMapping(value="/",headers = "content-type=multipart/*")
    public ResponseEntity<?> registerNGO(@RequestParam("ngoDocument") MultipartFile file, @RequestParam("name") String name,@RequestParam("email") String email, @RequestParam("description") String description,@RequestParam("ngoType") String ngoType,@RequestParam("location") String location) throws IOException {

        NGO ngo=new NGO();
        ngo.setName(name);
        ngo.setDescription(description);
        ngo.setNgoType(ngoType);
        ngo.setEmailId(email);
        ngo.setLocation(location);
        try {
            ngo.setPicByte(compressBytes(file.getBytes()));
        }catch(Exception e) {
            e.printStackTrace();

        }
        this.ngoService.registerNGO(ngo);
        return ResponseEntity.ok("ngo is added succesfully");
    }

    @GetMapping("/getAllNGO")
    public ResponseEntity<?> getAllNGODetails(){
        List<NGO> ngos=this.ngoService.getAllNGOs();
        for(NGO ngo:ngos) {
            ngo.setPicByte(decompressBytes(ngo.getPicByte()));
        }
        return ResponseEntity.ok(ngos);
    }



    @GetMapping("/{id}")
    public ResponseEntity<NGO> getNGO(@PathVariable Long id){
        NGO ngo=this.ngoService.getNGODetails(id);
        System.out.println(ngo);
        ngo.setPicByte(decompressBytes(ngo.getPicByte()));
        return ResponseEntity.ok(ngo);
    }


    //delete any category
    @DeleteMapping("/{id}")
    public ResponseEntity deleteNGO(@PathVariable Long id){
        this.ngoService.deleteNGO(id);
        return ResponseEntity.ok("ngo deleted successfully");
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

    @PostMapping(value="/donate-waste",headers = "content-type=multipart/*")
    public ResponseEntity donateToNGO(@RequestParam("wasteImage") MultipartFile file,@RequestParam("userId") Long userId, @RequestParam("id") Long ngoId) throws MessagingException {
        //fetch user details
        User theUser=this.userRepository.findById(userId).get();
        //logic to send the waste details and image to the ngo
        NGO ngo=this.ngoService.getNGODetails(ngoId);
        String ngoEmail=ngo.getEmailId();
        //logic to email sending to ngo
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());
        helper.setTo(ngo.getEmailId());
        helper.setSubject("Reusable material Donation");
        helper.addAttachment(file.getOriginalFilename(),file);
        helper.setText("Hi this side "+theUser.getUsername()+" from "+theUser.getSocietyName()+" as I want to donate some reusable material for the needy people so that they can use these material, please find the below attachment! my contact number: "+theUser.getPhonenumber());
        emailSender.send(message);
        //logic to add credits to the user account after successful waste donation
        theUser.setCredit(theUser.getCredit()+10);
        this.userRepository.save(theUser);
        System.out.println("User account credit after waste reusable submitted to NGO "+theUser.getCredit());

        return ResponseEntity.ok("reusable material is donated to the respective NGO");
    }

}
