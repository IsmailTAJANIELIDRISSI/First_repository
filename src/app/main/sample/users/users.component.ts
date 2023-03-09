
import { UserService } from './../../../auth/service/user.service';
// import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { User } from './user';
import { HttpClient } from '@angular/common/http';
import { CoreSidebarService } from '@core/components/core-sidebar/core-sidebar.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.scss']
})
export class UsersComponent implements OnInit {
  private apiUrl= 'https://jsonplaceholder.typicode.com/users'
  public sidebarToggleRef = false;
  ngOnInit() {
     this.getUsers()
   } 
   Users:User[]=[]
   public pageBasic = 2;
   constructor(
    private http: HttpClient,
    private service:UserService,
    private _coreSidebarService: CoreSidebarService,){
  }
  getUsers(){
    this.http.get(this.apiUrl).subscribe(res=>{this.Users = res as []})
    // this.service.getAll().subscribe(res=>{this.Users = res as []})
  }
  
  toggleSidebar(name: String): void {
    this._coreSidebarService.getSidebarRegistry(name).toggleOpen();
  }

    
deleteUser(id: any){
  // console.log(id);
  


  // this.http.delete(apiUrl+"/"+id).subscribe()
  
      
  //   }
  
}}
