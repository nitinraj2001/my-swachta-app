package com.hacknitr.wastemanagement.controller;

import com.hacknitr.wastemanagement.model.Industry;
import com.hacknitr.wastemanagement.model.SlumArea;
import com.hacknitr.wastemanagement.model.User;
import com.hacknitr.wastemanagement.repository.UserRepository;
import com.hacknitr.wastemanagement.sevice.SlumAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/slum-area")
public class SlumAreaController {

    @Autowired
    private SlumAreaService slumAreaService;

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/")
    public ResponseEntity<SlumArea> registerSlumAreaInApp(@RequestBody SlumArea slumArea){
        SlumArea theSlumArea=this.slumAreaService.registerSlumArea(slumArea);
        return ResponseEntity.ok(theSlumArea);
    }

    @GetMapping("/getAllSlumArea")
    public ResponseEntity<List<SlumArea>> getAllRegisteredSlumArea(){
        List<SlumArea> slumAreas=this.slumAreaService.getAllSlumAreas();
        return ResponseEntity.ok(slumAreas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SlumArea> getSlumArea(@PathVariable Long id){
        SlumArea slumArea=this.slumAreaService.getSlumAreaDetails(id);
        return ResponseEntity.ok(slumArea);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRegisteredSlumArea(@PathVariable Long id){
        this.slumAreaService.deleteSlumArea(id);
        return ResponseEntity.ok("slum area is successfully deleted");
    }


    @PostMapping(value="/donate-waste",headers = "content-type=multipart/*")
    public ResponseEntity donateToIndustry(@RequestParam("wasteImage") MultipartFile file, @RequestParam("userId") Long userId, @RequestParam("id") Long slumId) throws MessagingException {
        //fetch user details
        User theUser=this.userRepository.findById(userId).get();

        //logic to send the waste details and image to the industry
        SlumArea slumArea=this.slumAreaService.getSlumAreaDetails(slumId);
        String slumAreaEmail=slumArea.getEmail();

        //logic for email sending to the slum area for reusable waste donation
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());
        helper.setTo(slumArea.getEmail());
        helper.setSubject("Reusable material Donation");
        helper.addAttachment(file.getOriginalFilename(),file);
        helper.setText("Hi this side "+theUser.getUsername()+" from "+theUser.getSocietyName()+" as I want to donate some reusable material, please find the below attachment! my contact number: "+theUser.getPhonenumber());
        emailSender.send(message);
        //logic to add credits to the user account after successful waste donation
        theUser.setCredit(theUser.getCredit()+10);
        this.userRepository.save(theUser);
        System.out.println("User account credit after waste reusable submitted to slum area "+theUser.getCredit());

        return ResponseEntity.ok("reusable material is donated to the slum area ");
    }





}
