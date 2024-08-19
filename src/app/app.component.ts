import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { RegisterComponent } from './components/register/register.component';
import { LoginComponent } from './components/login/login.component';
import { NavbarComponent } from "./components/navbar/navbar.component";
import { OpeningComponent } from './components/opening/opening.component';
import { OpeningDetailComponent } from './components/opening-detail/opening-detail.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, RegisterComponent, LoginComponent, NavbarComponent,OpeningComponent,OpeningDetailComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'ijp_client';
}
