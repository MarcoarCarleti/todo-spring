import { Component } from '@angular/core';

@Component({
  selector: 'app-bouncing-logo',
  standalone: true,
  imports: [],
  templateUrl: './bouncing-logo.component.html',
  styleUrl: './bouncing-logo.component.css'
})
export class BouncingLogoComponent {
  imageSrc = "assets/output.png"
}
