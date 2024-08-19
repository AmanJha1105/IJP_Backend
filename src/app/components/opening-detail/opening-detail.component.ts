import { Component, OnInit } from '@angular/core';
import { Opening } from '../opening/opening.model';
import { OpeningService } from '../../services/OpeningService/opening.service';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-opening-detail',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './opening-detail.component.html',
  styleUrl: './opening-detail.component.scss'
})
export class OpeningDetailComponent implements OnInit {
apply() {
throw new Error('Method not implemented.');
}
  opening: Opening | null = null;

  constructor(private route: ActivatedRoute, private openingService: OpeningService) {}

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.openingService.getOpeningById(id).subscribe(data => {
      this.opening = data;
    });
  }
}
