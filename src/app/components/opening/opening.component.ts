import { Component, OnInit } from '@angular/core';
import { Opening } from './opening.model';
import { OpeningService } from '../../services/OpeningService/opening.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-opening',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './opening.component.html',
  styleUrl: './opening.component.scss'
})
export class OpeningComponent implements OnInit {
  openings: Opening[] = [];
  isAdmin: boolean = true;

  constructor(private openingService: OpeningService, private router: Router) {}

  ngOnInit(): void {
    this.checkUserRole();
    this.loadOpenings();
  }

  checkUserRole() {
    //const userRole = localStorage.getItem('userRole');
    //this.isAdmin = userRole === 'HR';
  }

  loadOpenings() {
    this.openingService.getOpenings().subscribe(data => {
      this.openings = data;
    });
  }

  viewOpening(id: number) {
    this.router.navigate([`/opening/${id}`]);
  }

  addOpening() {
    this.router.navigate(['openings/add']);
  }

  deleteOpening(id: number) {
    this.openingService.deleteOpening(id).subscribe(() => {
      this.loadOpenings();
    });
  }
}