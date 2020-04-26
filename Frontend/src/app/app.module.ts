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
import {
  AdvertisementListComponent, ImagesDialogComponent
} from './modules/advertisement/advertisement-list/advertisement-list.component';
import { HomepageComponent } from './modules/home/homepage/homepage.component';
import { LoginComponent } from './modules/home/login/login.component';
import { MatButtonModule} from '@angular/material/button';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatSelectModule} from '@angular/material/select';
import {MAT_DIALOG_DATA, MatDialogModule} from '@angular/material/dialog';
import {NgImageSliderModule} from 'ng-image-slider';
import {ImageModelService} from './services/imageModel.service/imageModel.service';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    RegistracijaComponent,
    AdvertisementComponent,
    LoginComponent,
    HomepageComponent,
    AdvertisementListComponent,
    ImagesDialogComponent

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
    MatButtonModule,
    RouterModule.forRoot([

      {path: '', component: RegistracijaComponent},
      {path: 'app-advertisement', component: AdvertisementComponent},
      {path: 'advertisement-list', component: AdvertisementListComponent},

    ]),
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyBcBUQxfS6JldNG0Ltoju5YxE_0-CKJsu4',
      libraries: ['places']
    }),
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatFormFieldModule,
    MatSelectModule,
    MatDialogModule,
    NgImageSliderModule
  ],
  exports: [RouterModule],
  providers: [AdvertisementService, ImageModelService],
  bootstrap: [AppComponent],
  entryComponents: [ImagesDialogComponent]
})
export class AppModule { }
