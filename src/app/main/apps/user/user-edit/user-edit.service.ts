import { query } from '@angular/animations';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable, OnInit } from '@angular/core';
import { ActivatedRoute, ActivatedRouteSnapshot, Resolve, RouterStateSnapshot } from '@angular/router';

import { BehaviorSubject, Observable } from 'rxjs';

@Injectable()
export class UserEditService implements Resolve<any>,OnInit {
  public apiData: any;
  public onUserEditChanged: BehaviorSubject<any>;

  /**
   * Constructor
   *
   * @param {HttpClient} _httpClient
   */
  id:number
  ngOnInit(){
     
  }
  constructor(private _httpClient: HttpClient,private route: ActivatedRoute) {
    // Set the defaults
    this.route.queryParamMap.subscribe(params => {
      if (params.get('id')!=null) {

         this.id=Number(params.get('id')) 
        
      }
    
      
      
    });

    
   
   
    
    
    this.onUserEditChanged = new BehaviorSubject({});
  }
  

  /**
   * Resolver
   *
   * @param {ActivatedRouteSnapshot} route
   * @param {RouterStateSnapshot} state
   * @returns {Observable<any> | Promise<any> | any}
   */
  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<any> | Promise<any> | any {
    if (!this.id) {
      
      new Promise<void>((resolve, reject) => {
        Promise.all([this.getApiData()]).then(() => {
          resolve();
        }, reject);
      });
    }
  }

  /**
   * Get API Data
   */
  private header=new HttpHeaders({"content-type":"application/json"})
  getApiData(): Promise<any[]> {
    return new Promise((resolve, reject) => {
 
      console.log(this.id);  
      this._httpClient.get('http://localhost:8080/users/'+this.id).subscribe((response: any) => {
        this.apiData = response;
        this.onUserEditChanged.next(this.apiData);
        console.log(response);
        
        resolve(this.apiData);
      }, reject);
    });
  }
}
