import { Component, OnInit } from '@angular/core';
import { WasteService } from 'src/app/service/waste.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-view-uploaded-waste',
  templateUrl: './view-uploaded-waste.component.html',
  styleUrls: ['./view-uploaded-waste.component.css']
})
export class ViewUploadedWasteComponent implements OnInit {
  
  userId:any;
  user:any;
  waste:any={"id":"","name":"","description":""}

  category:any={"id":"","categoryName":"","description":""};
  picByte: any;
  base64Data: any;
  wastes:any=[];
  retrievedImage: string;


  constructor(private wasteService:WasteService) { }



  ngOnInit(): void {
    this.user=localStorage.getItem("user");
    this.user=JSON.parse(this.user);
    this.fetchAllWasteUploadedByUser();
  }


  deleteWaste(id){
    console.log("waste uploaded by user with id "+id+" is to be deleted");
    Swal.fire({
     icon:'info',title:'Are you sure you want to delete this question?',confirmButtonText:'Delete',showCancelButton:true
   }).then((result)=>{
     if(result.isConfirmed){
         this.wasteService.deleteWasteUploadedByUser(id).subscribe(data=>{
           console.log(data);
          Swal.fire("success!!","waste is successfully deleted","success");
        },
        (error)=>{
          //console.log(error);
          Swal.fire("success","waste is successfully deleted","success");
          location.reload();
        }
      )
     }
   }
   )
  }
  




 
  fetchAllWasteUploadedByUser(){
    this.wasteService.getAllWastesUploadByUser(this.user.userId).subscribe((data)=>{
      console.log(data);
      this.wastes=data;
       this.wastes.forEach(element => {
         this.base64Data=element.picByte;
       this.retrievedImage = 'data:image/jpeg;base64,' + this.base64Data;
       element.picByte=this.retrievedImage;
    })
  });
}




}
  
