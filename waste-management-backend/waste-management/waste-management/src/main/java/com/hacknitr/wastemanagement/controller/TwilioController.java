package com.hacknitr.wastemanagement.controller;

import com.hacknitr.wastemanagement.model.MessageModel;
import com.hacknitr.wastemanagement.sevice.TwilioMessageSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.net.URISyntaxException;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.type.PhoneNumber;

@RestController
@RequestMapping("/message")
public class TwilioController {

    @Autowired
    private TwilioMessageSenderService twilioMessageSenderService;

    @Value("${accountSID}")
    private String accountSID;

    @Value("${accountAuthToken}")
    private String accountAuthToken;

    @Value("${twilloSenderNumber}")
    private String twilloSenderNumber;

    @PostMapping("/sendSMS")
    public String sendSMSByTwillo(@RequestBody MessageModel messageRequest) {
        String sendMessageResponse = twilioMessageSenderService.sendMessage(messageRequest);
        return sendMessageResponse;
    }

    @GetMapping("/voiceMessage")
    public String sendVoiceMessage() throws URISyntaxException {
        Twilio.init(accountSID,accountAuthToken);

        String from =twilloSenderNumber;


        Call call = Call.creator(new PhoneNumber("+919560293905"), new PhoneNumber(from),
                new URI("http://demo.twilio.com/docs/voice.xml")).create();

        System.out.println(call.getSid());
        return "Voice message is send successfully";
    }

}
