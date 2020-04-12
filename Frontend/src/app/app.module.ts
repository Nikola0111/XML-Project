import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {HttpClient, HttpClientModule} from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { NavbarComponent } from './navbar/navbar.component';
import { RegistracijaComponent } from './registracija/registracija.component';
import {RouterModule} from '@angular/router';
import { AgmCoreModule} from '@agm/core';

import { RegistrationComponent } from './modules/registration/registration.component';


@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    RegistracijaComponent,
    RegistrationComponent
],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    RouterModule.forRoot([
      { path: 'registracija', component: RegistracijaComponent },
      { path: '', redirectTo: 'registracija', pathMatch: 'full' }
    ]),
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyBcBUQxfS6JldNG0Ltoju5YxE_0-CKJsu4',
      libraries: ['places']
    })

  ],
  exports: [RouterModule],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
