import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RegistracijaComponent } from './registracija/registracija.component';
import { AdvertisementComponent } from './modules/advertisement/advertisement.component';


const routes: Routes = [
  { path: '', component: RegistracijaComponent},
  { path: 'advertisement', component: AdvertisementComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
