import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  user:any;
  username:any;
  credit:any;

  token:any;

  constructor() { }

  ngOnInit(): void {
    this.token=localStorage.getItem("token");
    this.user=JSON.parse(localStorage.getItem("user"));
    this.username=this.user.username;
    this.credit=this.user.credit;
  }

}
