import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from './main/sample/users/user';

// const header=new HttpHeaders({"content-type":"application/json"})
@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http:HttpClient) { }
  private apiUrl= 'https://jsonplaceholder.typicode.com/users'

  getAll() {
      
   return this.http.get<User[]>(`${this.apiUrl}`)
  }
}
