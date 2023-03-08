package com.hacknitr.wastemanagement.controller;

import com.hacknitr.wastemanagement.model.Industry;
import com.hacknitr.wastemanagement.model.User;
import com.hacknitr.wastemanagement.repository.UserRepository;
import com.hacknitr.wastemanagement.sevice.IndustryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
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
@RequestMapping("/industry")
public class IndustryController {

    @Autowired
    private IndustryService industryService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JavaMailSender emailSender;

    @PostMapping("/")
    public ResponseEntity registerIndustry(@RequestBody Industry industry){
        Industry theIndustry=this.industryService.registerIndustry(industry);
        return ResponseEntity.ok(theIndustry);
    }

    @GetMapping("/getAllIndustries")
    public ResponseEntity<?> getAllindustriesDetails(){
        List<Industry> industries=this.industryService.getAllIndustries();
        return ResponseEntity.ok(industries);
    }

    @GetMapping("/{id}")
    public ResponseEntity getIndustry(@PathVariable("id") Long id){
        Industry industry=this.industryService.getIndustryDetails(id);
        return ResponseEntity.ok(industry);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteIndustry(@PathVariable("id") Long id){
        this.industryService.deleteIndustry(id);
        return ResponseEntity.ok("industry deleted successfully");
    }

    @PostMapping(value="/donate-waste",headers = "content-type=multipart/*")
    public ResponseEntity donateToIndustry(@RequestParam("wasteImage") MultipartFile file,@RequestParam("userId") Long userId, @RequestParam("id") Long industryId) throws MessagingException {
        //fetch user details
        User theUser=this.userRepository.findById(userId).get();
        //logic to send the waste details and image to the industry
        Industry industry=this.industryService.getIndustryDetails(industryId);
        String industryEmail=industry.getEmail();

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());
        helper.setTo(industry.getEmail());
        helper.setSubject("Recycle material Donation");
        helper.addAttachment(file.getOriginalFilename(),file);
        helper.setText("Hi this side "+theUser.getUsername()+" from "+theUser.getSocietyName()+" as I want to donate some recycle material, please find the below attachment! my contact number: "+theUser.getPhonenumber());
        emailSender.send(message);
        //logic to add credits to the user account after successful waste donation
        theUser.setCredit(theUser.getCredit()+10);
        this.userRepository.save(theUser);
        System.out.println("User account credit after waste recycle submitted to industry "+theUser.getCredit());

        return ResponseEntity.ok("waste is donated to the industry ");
    }


}
