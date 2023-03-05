import { Component, OnInit } from '@angular/core';
import { NGOService } from 'src/app/service/ngo.service';

@Component({
  selector: 'app-view-all-ngoby-user',
  templateUrl: './view-all-ngoby-user.component.html',
  styleUrls: ['./view-all-ngoby-user.component.css']
})
export class ViewAllNGOByUserComponent implements OnInit {

  ngos:any=[];
  ngo:any={ngoId:"",name:"",description:"",location:"",emailId:"",ngoType:""}
  base64Data: any;
  retrievedImage: string;

  constructor(private ngoService:NGOService) { }

  ngOnInit(): void {
    this.fetchAllNGO();
  }

  fetchAllNGO(){
    this.ngoService.getAllNGOS().subscribe((data)=>{
      console.log(data);
      this.ngos=data;
      this.ngos.forEach(element=>{
        this.base64Data=element.picByte;
        this.retrievedImage = 'data:image/jpeg;base64,' + this.base64Data;
        element.picByte=this.retrievedImage;
      });
    },
    (error)=>{
    console.log(error);
    }
    )
  }
}
