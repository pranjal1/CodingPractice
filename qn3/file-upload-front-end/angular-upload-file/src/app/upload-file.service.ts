import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
 
@Injectable({
 providedIn: 'root'
})
export class UploadService {
 
 constructor(
   private httpClient: HttpClient,
 ) { }
 
 public uploadfile(file: File): Observable<String> {
   let formParams = new FormData();
   formParams.append('file', file)
   return this.httpClient.post('http://localhost:8888/upload', formParams, { responseType: 'text' });
 }
}
