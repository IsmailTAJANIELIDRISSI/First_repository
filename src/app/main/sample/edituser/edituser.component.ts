import { HttpClient } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { User } from '../users/user';
@Component({
  selector: 'app-edituser',
  templateUrl: './edituser.component.html',
  styleUrls: ['./edituser.component.scss']
})
export class EdituserComponent implements OnInit {
  private apiUrl= 'https://jsonplaceholder.typicode.com/users'

  constructor(private route: ActivatedRoute,private http:HttpClient) { }
  idP:number=0
  User:User
  Users:User[]
  ngOnInit() {
    this.getUser()
    
    
    
     
  }
  getUser() {
    this.idP = Number(this.route.snapshot.params['id']);
    console.log(this.idP);
    console.log(this.apiUrl + "/" + this.idP);
  
     this.http.get(this.apiUrl + "/" + this.idP).subscribe(
      res => {
        this.User = res as User;
        console.log(this.User);
      }
    );
  }
  // getUser(){
  //   this.id = Number(this.route.snapshot.params['id'])  ;
  //   console.log(this.id) ;

  //   console.log(this.apiUrl+"/"+this.id);
    
  //    return this.http.get(this.apiUrl+"/"+this.id).subscribe(
  //     res=>{
  //      this.User =res as User


  //     }
  //     )
    

  // }


}
