import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IndustryService } from 'src/app/service/industry.service';

@Component({
  selector: 'app-view-industry-details-by-user',
  templateUrl: './view-industry-details-by-user.component.html',
  styleUrls: ['./view-industry-details-by-user.component.css']
})
export class ViewIndustryDetailsByUserComponent implements OnInit {

  industryId:any;
  industry:any={"id":"","name":"","description":"","email":"","industryType":"","sector":"","address":""};

  constructor(private route:ActivatedRoute,private industryService:IndustryService) { }

  ngOnInit(): void {
     this.industryId=this.route.snapshot.paramMap.get('id');
     this.getIndustryInfo();
  }

  getIndustryInfo(){
    this.industryService.getIndustryDetails(this.industryId).subscribe((data)=>{
      console.log(data);
      this.industry=data;
    }),
    (error)=>{
      console.log(error);
    }
  }

}
