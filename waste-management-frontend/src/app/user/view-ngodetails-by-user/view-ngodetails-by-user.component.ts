import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { NGOService } from 'src/app/service/ngo.service';

@Component({
  selector: 'app-view-ngodetails-by-user',
  templateUrl: './view-ngodetails-by-user.component.html',
  styleUrls: ['./view-ngodetails-by-user.component.css']
})
export class ViewNGODetailsByUserComponent implements OnInit {

  ngoId:any;
  ngo:any={ngoId:"",name:"",description:"",location:"",emailId:"",ngoType:""}
  base64Data: any;
  retrievedImage: string;
  constructor(private route: ActivatedRoute,private ngoService:NGOService) { }

  ngOnInit(): void {
    this.ngoId = this.route.snapshot.paramMap.get('id');
    this.viewNGODetailByUser();
  }


  viewNGODetailByUser(){
      console.log("ngo id whose details to be shown is: "+this.ngoId);
      this.ngoService.getNGODetails(this.ngoId).subscribe((data)=>{
        console.log(data);
        this.ngo=data;
        this.base64Data=this.ngo.picByte;
        this.retrievedImage = 'data:image/jpeg;base64,' + this.base64Data;
        this.ngo.picByte=this.retrievedImage;

      })
  }

}
