import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/service/login.service';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css']
})
export class ResetPasswordComponent implements OnInit {

  user:any={"email":"","password":""}

  constructor(private loginService:LoginService,private matSnakeBar:MatSnackBar,private router:Router) { }

  ngOnInit(): void {
  }

  resetPassword(){
       this.loginService.newPassword(this.user).subscribe((data)=>{
        console.log(data);
        
        this.router.navigate['/login'];
        
       },
       (error)=>{
        this.matSnakeBar.open("password is reset successfully","ok");
        this.router.navigate(['/login']);
       })
       
  }

}
