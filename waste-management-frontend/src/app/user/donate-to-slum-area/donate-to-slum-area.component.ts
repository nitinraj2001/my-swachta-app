import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { SlumAreaService } from 'src/app/service/slum-area.service';

@Component({
  selector: 'app-donate-to-slum-area',
  templateUrl: './donate-to-slum-area.component.html',
  styleUrls: ['./donate-to-slum-area.component.css']
})
export class DonateToSlumAreaComponent implements OnInit {

  picByte:any;
  user:any;
  userId:any;
  email:any;
  slumId:any;
  slumArea:any={"id":"","location":"","email":"","contact":"","description":""}

  constructor(private route:Router,private slumAreaService:SlumAreaService,private router:ActivatedRoute) { }

  ngOnInit(): void {
    this.user=JSON.parse(localStorage.getItem("user"));
    this.userId=this.user.userId;
    this.slumId=this.router.snapshot.paramMap.get('id');
    this.getSlumAreaInformation();
  }


  getSlumAreaInformation(){
    this.slumAreaService.getSlumAreaDetails(this.slumId).subscribe((data)=>{
      console.log(data);
      this.slumArea=data;
      this.email=this.slumArea.email;
    }),
    (error)=>{
      console.log(error);
    }
  }

  onFileChanged(event) {
    this.picByte = event.target.files[0]
  }

  donateMaterial(){
    const formdata=new FormData();
    formdata.append("userId",this.userId);
    formdata.append("id",this.slumId);
    formdata.append("wasteImage",this.picByte);
    console.log(formdata);
  this.slumAreaService.donateRecycle(formdata).subscribe((data)=>{
    console.log(data);
  }),
  (error)=>{
    console.log(error);
  }
  }

  redirectToCreditPage(){
    this.route.navigate(['/user/view-user-profile']);
  }




}
