import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { IndustryService } from 'src/app/service/industry.service';

@Component({
  selector: 'app-user-view-industry',
  templateUrl: './user-view-industry.component.html',
  styleUrls: ['./user-view-industry.component.css']
})
export class UserViewIndustryComponent implements OnInit {

  industryId:any;

  industry:any={"id":"","name":"","description":"","email":"","industryType":"","sector":"","address":""};
  industries:any=[];

  constructor(private industryService:IndustryService,private router:Router) { }

  ngOnInit(): void {
    this.getIndustryDetailsToView();
  }

  getIndustryDetailsToView(){
    this.industryService.fetchAllIndustriesDetails().subscribe((data)=>{
      this.industries=data;
    }),
    (error)=>{
      console.log(error);
    }
  }

  viewIndustryDetails(id:any){
    console.log("Industry with id: "+id+ "is to be displayed");
    this.router.navigate([`/user/view-industry-details/${id}`]);
  }

  


}
