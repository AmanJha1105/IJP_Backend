import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth.service';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  loginForm: FormGroup;
  errorMessage: string | null = null;
  isAdmin: String="Employee";

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {
    this.loginForm = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
      isAdmin: [false]
    });
  }

  onSubmit(): void {
    if (this.loginForm.valid) {
      const { username, password ,isAdmin} = this.loginForm.value;
      this.authService.login(username, password,isAdmin).subscribe(
        () => {
          localStorage.setItem('userRole', this.isAdmin ? 'HR' : 'Employee');
          this.router.navigate(['/openings']);
        },
        (error) => {
          this.errorMessage = 'Invalid username or password';
        }
      );
    }
  }
}
