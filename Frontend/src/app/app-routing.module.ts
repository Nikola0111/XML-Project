import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RegistracijaComponent } from './modules/home/registracija/registracija.component';
import { AdvertisementComponent } from './modules/advertisement/advertisement.component';
import {HomepageComponent} from './modules/home/homepage/homepage.component';


const routes: Routes = [
  { path: '', component: HomepageComponent},
  { path: 'advertisement', component: AdvertisementComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
