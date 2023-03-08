import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { LoginService } from 'src/app/service/login.service';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  user:any={"username":"","email":"","password":"","phonenumber":"","societyName":"","credit":""};
  userId:any;

  constructor(private route:ActivatedRoute,private router:Router,private snakeBar:MatSnackBar,private loginService:LoginService,private userService:UserService) { }

  ngOnInit(): void {
    this.user=JSON.parse(localStorage.getItem("user"));
    this.fetchUserById();
  }

  fetchUserById(){
     this.userService.getUser(this.user.userId).subscribe(data=>{
       this.user=data;
       console.log(this.user+" "+data);
     }),
     (error)=>{
       console.log(error);
     }
  }

  logout(){
    console.log("request to logout came");
   this.loginService.logout();
   this.router.navigate(['/login']);
   window.location.reload();
   
   this.snakeBar.open("you have successfully logout!!","ok",{duration:3000});
 }
}
