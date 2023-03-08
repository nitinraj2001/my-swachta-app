import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NGOService } from 'src/app/service/ngo.service';

@Component({
  selector: 'app-donate-to-ngo',
  templateUrl: './donate-to-ngo.component.html',
  styleUrls: ['./donate-to-ngo.component.css']
})
export class DonateToNGOComponent implements OnInit {

  picByte:any;
  user:any;
  userId:any;
  email:any;
  ngoId:any;
  ngo:any={ngoId:"",name:"",description:"",location:"",emailId:"",ngoType:""}
  base64Data: any;
  retrivedImage:any;

  constructor(private router:Router,private route:ActivatedRoute,private ngoService:NGOService) { }

  ngOnInit(): void {
    this.user=JSON.parse(localStorage.getItem("user"));
    this.userId=this.user.userId;
    this.ngoId=this.route.snapshot.paramMap.get('id');
    this.getNGOInfo();
  }

  onFileChanged(event) {
    this.picByte = event.target.files[0]
  }


  getNGOInfo(){
    console.log("ngo id whose details to be shown is: "+this.ngoId);
    this.ngoService.getNGODetails(this.ngoId).subscribe((data)=>{
      console.log(data);
      this.ngo=data;
      this.email=this.ngo.emailId
      this.base64Data=this.ngo.picByte;
      this.retrivedImage = 'data:image/jpeg;base64,' + this.base64Data;
      this.ngo.picByte=this.retrivedImage;

    })
}

donateReusableMaterialToNGO(){
  const formdata=new FormData();
    formdata.append("userId",this.userId);
    formdata.append("id",this.ngoId);
    formdata.append("wasteImage",this.picByte);
    console.log(formdata);
  this.ngoService.donateReusable(formdata).subscribe((data)=>{
    console.log(data);
  }),
  (error)=>{
    console.log(error);
  }
}


  redirectToCreditPage(){
    this.router.navigate(['/user/view-user-profile']);
  }

}
