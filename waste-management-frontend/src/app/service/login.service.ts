import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import baseUrl from './helper';
import { Subject } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class LoginService {

  loginStatusSubject=new Subject<boolean>();
   
  constructor(private http:HttpClient) { }

 generateJwtToken(user:any){
   return this.http.post(`${baseUrl}/generatetoken`,user);
 }

 getCurrentUser(username:any){

  return this.http.get(`${baseUrl}/current-user`);
}

generatejwtTokenStatus(token:any){
  return this.http.get(`${baseUrl}/jwt-token-status/${token}`,);
}

loginUser(token){
  localStorage.setItem("token",token);
  
  return true;
}

IsloggedIn(){
  let tokenStr=localStorage.getItem("token");
  if(tokenStr==null||tokenStr==''||tokenStr==undefined){
    return false;
  }
  return true;
}

logout(){
  localStorage.removeItem("token");
  localStorage.removeItem("user");
  return true;
}

getToken(){
  return localStorage.getItem("token");
}

setUserDetails(user:any){
  localStorage.setItem("user",JSON.stringify(user));
  return true;
}

getUserDetails(){
  let userdetailsStr=localStorage.getItem("user");
  if(userdetailsStr!=null){
  return JSON.parse(userdetailsStr);
  }
  else{
    this.logout();
    return null;
  }
}

getUserAuthority(){
  let user=localStorage.getItem("user");
  let userauthority=JSON.parse(user).authorities[0].authority;
  //console.log("user authority is: "+userauthority);
  return userauthority;
}

resetPassword(email:any){
  return this.http.get(`${baseUrl}/user/forget-password/${email}`);

}

newPassword(user){
  const formdata=new FormData();

  return this.http.post(`${baseUrl}/user/new-password`,user);
}


}
