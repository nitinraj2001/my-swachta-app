import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { SlumAreaService } from 'src/app/service/slum-area.service';

@Component({
  selector: 'app-view-slum-area-details-by-user',
  templateUrl: './view-slum-area-details-by-user.component.html',
  styleUrls: ['./view-slum-area-details-by-user.component.css']
})
export class ViewSlumAreaDetailsByUserComponent implements OnInit {

  slumId:any;

  slumArea:any={"id":"","location":"","email":"","contact":"","description":""}

  constructor(private route:ActivatedRoute,private slumAreaService:SlumAreaService,private router:Router) { }

  ngOnInit(): void {
    this.slumId=this.route.snapshot.paramMap.get('id');
    this.getSlumAreaInformation();
  }

  getSlumAreaInformation(){
    this.slumAreaService.getSlumAreaDetails(this.slumId).subscribe((data)=>{
      console.log(data);
      this.slumArea=data;
    }),
    (error)=>{
      console.log(error);
    }
  }

  redirectToDonateSlum(){
     this.router.navigate([`/user/donate-reusable-material-slum/${this.slumId}`]);
  }

}
