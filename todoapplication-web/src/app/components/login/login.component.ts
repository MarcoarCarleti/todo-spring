import { HttpClient } from '@angular/common/http';
import { Component, OnInit, inject } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { Router } from '@angular/router';
import { BouncingLogoComponent } from '../bouncing-logo/bouncing-logo.component';
import { JwtService } from '../../service/jwt/jwt.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    BouncingLogoComponent,
    CommonModule,
  ],
  styleUrl: './login.component.css',
  providers: [JwtService],
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup | any;

  password: string = 'password';
  show: boolean = false;

  constructor(
    private service: JwtService,
    private fb: FormBuilder,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
    });
    console.log(this.password);
  }

  showPassword() {
    if (this.password === 'password') {
      this.password = 'text';
      this.show = true;
    } else {
      this.password = 'password';
      this.show = false;
    }
  }

  submitForm() {
    this.service.login(this.loginForm.value).subscribe((response) => {
      if (response.jwt != null) {
        const jwtToken = response.jwt;
        const isAdmin = response.admin;

        const userEmail = this.loginForm.value.email;

        localStorage.setItem('email', userEmail);
        localStorage.setItem('jwt', jwtToken);
        localStorage.setItem('admin', isAdmin);

        this.router.navigateByUrl('/app');
      }
    });
  }
}
