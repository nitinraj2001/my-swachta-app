import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import {PickUpScheduleService} from '../../service/pick-up-schedule.service';

@Component({
  selector: 'app-schedule-pickup',
  templateUrl: './schedule-pickup.component.html',
  styleUrls: ['./schedule-pickup.component.css']
})
export class SchedulePickupComponent implements OnInit {

  user:any;

  pickUpSchedule:any={"wasteDetails":"","userId":"","date":"","time":"","location":""}

  constructor(private schedulePickUpService:PickUpScheduleService,private matSnackBar:MatSnackBar,private router:Router) { }

  ngOnInit(): void {
    this.user=localStorage.getItem("user");
    this.user=JSON.parse(this.user);
    this.pickUpSchedule.userId=this.user.userId;
  }

  navigateToPickUpSchedule(){
    this.router.navigate(['/user/view-pickup-schedule']);
  }

  scheduleYourPickUp(){
     console.log(this.pickUpSchedule);
     this.schedulePickUpService.scheduleYourWastePickUp(this.pickUpSchedule).subscribe((data)=>{
      console.log(data);
      this.matSnackBar.open("your pickup is schedule check ur email","ok");
      this.router.navigate(['/user/view-pickup-schedule']);
     }),
     (error)=>{
      //console.log(error);
     // this.matSnackBar.open("your pickup is schedule check ur email","ok");
      this.router.navigate(['/user/view-pickup-schedule']);

     }
  }

}
