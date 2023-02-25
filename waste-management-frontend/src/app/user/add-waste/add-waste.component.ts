import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { CategoryService } from 'src/app/services/category.service';
import Swal from 'sweetalert2';
import { WasteService} from '../../service/waste.service';

@Component({
  selector: 'app-add-waste',
  templateUrl: './add-waste.component.html',
  styleUrls: ['./add-waste.component.css']
})
export class AddWasteComponent implements OnInit {

  categories:any=[];

  base64Data: any;

  categoryName:any;

  categoryId:any=1;

  retrievedImage: string;

  waste:any={"name":"","description":""}

  category:any={"id":"","categoryName":"","description":""};
  picByte: any;
  user:any;
  userId:any;
  thecategoryName:any;
  thecategory:any;

 

  constructor(private categoryService:CategoryService,private wasteService:WasteService,private router:Router,private route:ActivatedRoute,private matsnackBar:MatSnackBar) { }

  ngOnInit(): void {
    this.fetchAllCategories();
    this.user=localStorage.getItem("user");
    this.user=JSON.parse(this.user);
    console.log(JSON.parse(this.user));
    this.userId=3;
    console.log(this.userId);
  }

  onFileChanged(event) {
    this.picByte = event.target.files[0];
  }

  registerWaste(){
    this.fetchCategoryDetails();
    console.log(this.categories);
    const formdata=new FormData();
    console.log(this.waste);
    formdata.append("name",this.waste.name);
    formdata.append("description",this.waste.description);
    formdata.append("wasteImage",this.picByte);
    formdata.append("userId",this.user.userId);
    console.log("userid would be",this.user.userId);
    formdata.append("categoryName",this.thecategoryName);
    console.log(formdata);
    this.wasteService.registerYourWaste(formdata).subscribe((data)=>{
      console.log(data);
      this.router.navigate(['/user/view-waste-upload']);
      Swal.fire("success!!","waste is uploaded successfully now u can perform action as per type of waste reusable recycle reducable","success");

    })

  }
  fetchCategoryDetails(){
    this.categoryService.getCategoryDetails(this.thecategoryName).subscribe((data)=>{
      this.thecategory=data;
      console.log(data);
      this.categoryId=this.thecategory.id;
    })
  }

   fetchAllCategories(){
     this.categoryService.getAllCategories().subscribe((data)=>{
       console.log(data);
       this.categories=data;
        
      },
      (error)=>{
        console.log(error);
      }
    )
     
   }

  }