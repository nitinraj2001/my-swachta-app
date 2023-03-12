import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baseUrl from './helper';


@Injectable({
  providedIn: 'root'
})
export class SlumAreaService {

  constructor(private http:HttpClient) { }

  public registerSlumArea(slumArea:any){
    return this.http.post(`${baseUrl}/slum-area/`,slumArea);
  }

  public getAllSlumArea(){
    return this.http.get(`http://localhost:9090/slum-area/getAllSlumArea`);
  }

  public getSlumAreaDetails(id:any){
    return this.http.get(`${baseUrl}/slum-area/${id}`);
  }

  public donateRecycle(formdata:any){
    return this.http.post(`${baseUrl}/slum-area/donate-waste`,formdata);
  }
}
