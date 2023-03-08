package com.hacknitr.wastemanagement.controller;

import com.hacknitr.wastemanagement.model.SchedulePickup;
import com.hacknitr.wastemanagement.sevice.SchedulePickupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SchedulePickupController {

    @Autowired
    private SchedulePickupService schedulePickupService;

    @PostMapping("/")
    public ResponseEntity scheduleWaste(@RequestBody SchedulePickup schedulePickup){

        SchedulePickup schedulePickup1=schedulePickupService.schedulePickUp(schedulePickup);


        return ResponseEntity.ok("waste pick up is schedule successfully check your mail for further details");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteSchedulePickUp(@PathVariable Long id){

        this.schedulePickupService.deleteSchedule(id);

        return ResponseEntity.ok("schedule is deleted successfully");
    }
}
