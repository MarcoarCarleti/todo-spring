import { Component } from '@angular/core';
import { BouncingLogoComponent } from '../bouncing-logo/bouncing-logo.component';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [BouncingLogoComponent],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {

}
