import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/service/login.service';

@Component({
  selector: 'app-forget-password',
  templateUrl: './forget-password.component.html',
  styleUrls: ['./forget-password.component.css']
})
export class ForgetPasswordComponent implements OnInit {

  email:any;

  constructor(private http:HttpClient,private loginService:LoginService) { }

  ngOnInit(): void {
  }

  forgetPassword(){
    this.loginService.resetPassword(this.email).subscribe((data)=>{
      console.log(data);
    })
  }

}
