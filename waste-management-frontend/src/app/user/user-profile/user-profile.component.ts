import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { LoginService } from 'src/app/service/login.service';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  user:any={"username":"","email":"","password":"","phonenumber":""};
  userId:any;

  constructor(private route:ActivatedRoute,private router:Router,private loginService:LoginService,private userService:UserService) { }

  ngOnInit(): void {
    this.userId=+this.route.snapshot.params['id'];
    console.log("user with id "+this.userId);
    this.fetchUserById();
  }

  fetchUserById(){
     this.userService.getUser(this.userId).subscribe(data=>{
       this.user=data;
       console.log(this.user+" "+data);
     }),
     (error)=>{
       console.log(error);
     }
  }

}
