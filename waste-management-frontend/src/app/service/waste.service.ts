import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baseUrl from './helper';

@Injectable({
  providedIn: 'root'
})
export class WasteService {

  constructor(private http:HttpClient) { }

  registerYourWaste(waste:any){
    return this.http.post(`${baseUrl}/waste/`,waste);
  }

  getAllWastes(){
    return this.http.get(`${baseUrl}/waste/getAllWastes`);
  }

  getAllWastesUploadByUser(id){
    return this.http.get(`${baseUrl}/waste/getAllWasteMaterial/${id}`);
  }
  
  deleteWasteUploadedByUser(wasteId:any){
    return this.http.delete(`${baseUrl}/waste/${wasteId}`)
  }
}
