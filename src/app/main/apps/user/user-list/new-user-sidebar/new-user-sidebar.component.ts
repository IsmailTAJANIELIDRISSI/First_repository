import { HttpClient, HttpHeaders } from '@angular/common/http';
import { DomSanitizer } from '@angular/platform-browser';
import { FormControl, FormGroup, UntypedFormGroup, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { CoreSidebarService } from '@core/components/core-sidebar/core-sidebar.service';
@Component({
  selector: 'app-new-user-sidebar',
  templateUrl: './new-user-sidebar.component.html'
})
export class NewUserSidebarComponent implements OnInit {
  private header=new HttpHeaders({"content-type":"application/json"})
  // public fullname;
  // public username;
  // public email;
  base64?:String
  User:any
  fileSelected?:Blob
  imageUrl?:String
  public addform=new FormGroup({
    fullname:new FormControl('',[Validators.required]),
    username:new FormControl('',[Validators.required]),
    password:new FormControl('',[Validators.required,Validators.minLength(4)]),
    email:new FormControl('',[Validators.required,Validators.email]),
    role:new FormControl('',[Validators.required]),
    avatar:new FormControl('',[Validators.required])
  })
  /**
   * Constructor
   *
   * @param {CoreSidebarService} _coreSidebarService
   */
  constructor(private _coreSidebarService: CoreSidebarService,private sant:DomSanitizer,private http:HttpClient) {}

  /**
   * Toggle the sidebar
   *
   * @param name
   */
  toggleSidebar(name): void {
    this._coreSidebarService.getSidebarRegistry(name).toggleOpen();
  }
  onSelectNewFile(files:FileList){
    this.fileSelected=files[0]
    this.imageUrl=this.sant.bypassSecurityTrustUrl( window.URL.createObjectURL(this.fileSelected)) as String 
    console.log(this.imageUrl);
    
    this.convertFileToBase64()
    
    
    

  }
  convertFileToBase64(){
    let reader=new FileReader()
    reader.readAsDataURL(this.fileSelected as Blob)
    reader.onloadend=()=>{ 
      this.base64=reader.result as String
      
    }
  }

  /**
   * Submit
   *
   * @param form
   */
  get fullName(){
    return this.addform.controls['fullname']
  }
  get password(){
    return this.addform.controls['password']
  }
  get avatar(){
    return this.addform.controls['avatar']
  }
  get Email(){
    return this.addform.controls['email']
  }
  get userName(){
    return this.addform.controls['username']
  }
  get role(){
    return this.addform.controls['role']
  }
  submit(form) {
    if (form.valid) {
      this.toggleSidebar('new-user-sidebar');
    }
  }
  
 
  addUser() {
  
    const appUser= {
           id: null,
           fullname: this.fullName.value,
           username: this.userName.value,
           avatar: this.base64,
           email: this.Email.value,
           password: this.password.value
           
         }
         
         
    
     return this.http.post('http://localhost:8080/add/'+this.role.value, appUser, { headers: this.header }).subscribe();

  }
    
    
    
    
    
  
  ngOnInit(): void {}
}
