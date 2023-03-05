package com.hacknitr.wastemanagement.controller;

import com.hacknitr.wastemanagement.model.Category;
import com.hacknitr.wastemanagement.model.NGO;
import com.hacknitr.wastemanagement.sevice.NGOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@RestController
@RequestMapping("/ngo")
public class NGOController {

    @Autowired
    private NGOService ngoService;

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

}
