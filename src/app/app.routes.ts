import { Routes } from '@angular/router';
import { RegisterComponent } from './components/register/register.component';
import { LoginComponent } from './components/login/login.component';
import { OpeningComponent } from './components/opening/opening.component';
import { OpeningDetailComponent } from './components/opening-detail/opening-detail.component';
import { AddOpeningComponent } from './components/add-opening/add-opening.component';


export const routes: Routes = [
  { path: 'register', component: RegisterComponent },
  { path: 'login', component: LoginComponent },
  { path: 'openings', component: OpeningComponent },
  { path: 'opening/:id', component: OpeningDetailComponent },
  { path: 'openings/add', component: AddOpeningComponent },
  { path: '**', redirectTo: 'register' } // Default route
];
