import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {HttpClient, HttpClientModule} from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { NavbarComponent } from './navbar/navbar.component';
import { RegistracijaComponent } from './modules/home/registracija/registracija.component';
import {RouterModule} from '@angular/router';
import { AgmCoreModule} from '@agm/core';
import { AdvertisementComponent } from './modules/advertisement/advertisement.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { AdvertisementService } from './services/advertisement.service/advertisement.service';
import { AdvertisementListComponent } from './modules/advertisement/advertisement-list/advertisement-list.component';
import { HomepageComponent } from './modules/home/homepage/homepage.component';
import { LoginComponent } from './modules/home/login/login.component';



@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    RegistracijaComponent,
    AdvertisementComponent,

    LoginComponent,

    HomepageComponent,
    AdvertisementListComponent

],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    RouterModule.forRoot([

      { path: '', component: RegistracijaComponent },
      {path: 'app-advertisement', component: AdvertisementComponent},
      { path: 'advertisement-list', component: AdvertisementListComponent},

    ]),
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyBcBUQxfS6JldNG0Ltoju5YxE_0-CKJsu4',
      libraries: ['places']
    }),
    MatTableModule,
    MatPaginatorModule,
    MatSortModule

  ],
  exports: [RouterModule],
  providers: [AdvertisementService],
  bootstrap: [AppComponent]
})
export class AppModule { }
