import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { OpeningService } from '../../services/OpeningService/opening.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-add-opening',
  standalone: true,
  imports: [CommonModule,ReactiveFormsModule],
  templateUrl: './add-opening.component.html',
  styleUrl: './add-opening.component.scss'
})
export class AddOpeningComponent {
  addOpeningForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private openingService: OpeningService,
    private router: Router
  ) {
    this.addOpeningForm = this.fb.group({
      jobId: [null, Validators.required],
      description: ['', Validators.required],
      location: ['', Validators.required],
      lastDateToApply: [null, Validators.required],
      title: ['', Validators.required],
      skills: ['', Validators.required],
      salary: ['', Validators.required],
      experience: ['', Validators.required]
    });
  }

  onSubmit() {
    if (this.addOpeningForm.valid) {
      this.openingService.addOpening(this.addOpeningForm.value).subscribe({
        next: () => {
          alert('Opening added successfully');
          this.router.navigate(['/openings']);
        },
        error: (err) => {
          console.error('Error adding opening', err);
          alert('Failed to add opening');
        }
      });
    }
  }
}