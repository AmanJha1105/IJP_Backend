import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-register',
  standalone: true,
  templateUrl: './register.component.html',
  imports: [CommonModule, ReactiveFormsModule],
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent {
  registerForm: FormGroup;
  registrationSuccess: string | null = null;
  registrationError: string | null = null;

  constructor(private fb: FormBuilder, private authService: AuthService) {
    this.registerForm = this.fb.group({
      empName: ['', Validators.required],
      empRole: ['Employee', Validators.required],
      empId: ['', Validators.required],
      username: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      isAdmin: [false]
    });
  }

  onRegister() {
    if (this.registerForm.valid) {
      const formData = this.registerForm.value;
      this.authService.register(formData).subscribe(
        response => {
          this.registrationSuccess = 'Registration successful!';
          this.registrationError = null;
          this.registerForm.reset();
        },
        error => {
          this.registrationError = 'Registration failed. Please try again.';
          this.registrationSuccess = null;
        }
      );
    }
  }
}
