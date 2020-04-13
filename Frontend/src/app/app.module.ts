import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {HttpClient, HttpClientModule} from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { NavbarComponent } from './navbar/navbar.component';
import { RegistracijaComponent } from './modules/home/registracija/registracija.component';
import {RouterModule} from '@angular/router';
import { AgmCoreModule} from '@agm/core';
import { AdvertisementComponent } from './modules/advertisement/advertisement.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { AdvertisementService } from './modules/service/advertisement.service';
import { LoginComponent } from './modules/home/login/login.component';
import { HomepageComponent } from './modules/home/homepage/homepage.component';


@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    RegistracijaComponent,
    AdvertisementComponent,
    LoginComponent,
    HomepageComponent
],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    RouterModule.forRoot([
      { path: '', component: HomepageComponent },
      {path: 'app-advertisement', component: AdvertisementComponent},
    ]),
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyBcBUQxfS6JldNG0Ltoju5YxE_0-CKJsu4',
      libraries: ['places']
    })

  ],
  exports: [RouterModule],
  providers: [AdvertisementService],
  bootstrap: [AppComponent]
})
export class AppModule { }
