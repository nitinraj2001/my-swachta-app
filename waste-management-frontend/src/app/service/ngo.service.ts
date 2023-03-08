import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baseUrl from './helper';

@Injectable({
  providedIn: 'root'
})
export class NGOService {

  constructor(private http:HttpClient) { }

  registerNgo(ngo){
    return this.http.post(`${baseUrl}/ngo/`,ngo);
  }

  getAllNGOS(){
    return this.http.get(`${baseUrl}/ngo/getAllNGO`);
  }

  getNGODetails(id:any){
    return this.http.get(`${baseUrl}/ngo/${id}`)
  }

  donateReusable(formdata:any){
    return this.http.post(`${baseUrl}/ngo/donate-waste`,formdata);
  }
}
