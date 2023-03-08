import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { IndustryService } from 'src/app/service/industry.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-donate-to-industry',
  templateUrl: './donate-to-industry.component.html',
  styleUrls: ['./donate-to-industry.component.css']
})
export class DonateToIndustryComponent implements OnInit {

  picByte:any;
  user:any;
  userId:any;
  email:any;
  industryId:any;
  industry:any={"id":"","name":"","description":"","email":"","industryType":"","sector":"","address":""};


  constructor(private route:ActivatedRoute,private industryService:IndustryService,private router:Router) { }

  ngOnInit(): void {
    this.user=JSON.parse(localStorage.getItem("user"));
    this.userId=this.user.userId;
    this.industryId=this.route.snapshot.paramMap.get('id');
    this.getIndustryInfo();
  }

  onFileChanged(event) {
    this.picByte = event.target.files[0]
  }

  donateRecycleMaterial(){
    const formdata=new FormData();
    formdata.append("userId",this.userId);
    formdata.append("id",this.industryId);
    formdata.append("wasteImage",this.picByte);
    console.log(formdata);
    this.industryService.donateToIndustry(formdata).subscribe((data)=>{
      console.log(data);
      Swal.fire("success","Credits added to ur account for successful waste upload","success");
    }),
    (error)=>{
      console.log(error);
      Swal.fire("success","Credits added to ur account for successful waste upload","success");
    }
  }


  getIndustryInfo(){
    this.industryService.getIndustryDetails(this.industryId).subscribe((data)=>{
      console.log(data);
      this.industry=data;
      this.email=this.industry.email;
    }),
    (error)=>{
      console.log(error);
    }
  }

  redirectToCreditPage(){
    this.router.navigate(['/user/view-user-profile']);
  }
}
