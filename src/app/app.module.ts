// import { UserModule } from './main/sample/user/user.module';
import { CoreSidebarService } from './../@core/components/core-sidebar/core-sidebar.service';

import { UserService } from './auth/service/user.service';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule, Routes } from '@angular/router';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule } from '@angular/common/http';

import 'hammerjs';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { TranslateModule } from '@ngx-translate/core';
import { ToastrModule } from 'ngx-toastr'; // For auth after login toast

import { CoreModule } from '@core/core.module';
import { CoreCommonModule } from '@core/common.module';
import { CoreSidebarModule, CoreThemeCustomizerModule } from '@core/components';
import { NgxDatatableModule } from '@swimlane/ngx-datatable';
import { coreConfig } from 'app/app-config';

import { AppComponent } from 'app/app.component';
import { LayoutModule } from 'app/layout/layout.module';
import { SampleModule } from 'app/main/sample/sample.module';
import { UsersComponent } from './main/sample/users/users.component';
import { CoreDirectivesModule } from '@core/directives/directives';
import { EdituserComponent } from './main/sample/edituser/edituser.component';
import { AdduserComponent } from './main/sample/adduser/adduser.component';
import { UserModule } from './main/apps/user/user.module';


const appRoutes: Routes = [
  {
    path: 'pages',
    loadChildren: () => import('./main/pages/pages.module').then(m => m.PagesModule)
  },{
    path: 'users',
    loadChildren: () => import('./main/apps/user/user.module').then(m => m.UserModule)
  },
  {
    path: '',
    redirectTo: '/home',
    pathMatch: 'full'
  }
  
  ,
  {
    path: '**',
    redirectTo: '/pages/miscellaneous/error' //Error 404 - Page not found
  }
];

@NgModule({
  declarations: [AppComponent, AdduserComponent,],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,NgxDatatableModule,CoreDirectivesModule,
    RouterModule.forRoot(appRoutes, {
      scrollPositionRestoration: 'enabled', // Add options right here
      relativeLinkResolution: 'legacy'
    }),
    TranslateModule.forRoot(),

    //NgBootstrap
    NgbModule,
    ToastrModule.forRoot(),

    // Core modules
   
    CoreModule.forRoot(coreConfig),
    CoreCommonModule,

    CoreThemeCustomizerModule,

    // App modules
    UserModule,
    LayoutModule,
    SampleModule
  ], providers: [UserService,CoreSidebarService
    
 ],

  bootstrap: [AppComponent]
})
export class AppModule {}
