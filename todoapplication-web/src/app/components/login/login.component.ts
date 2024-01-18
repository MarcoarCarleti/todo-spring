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
import { JwtService } from '../../service/jwt.service';



@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  standalone: true,
  imports: [ReactiveFormsModule, BouncingLogoComponent],
  styleUrl: './login.component.css',
  providers: [JwtService],
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup | any;
  

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
  }

  submitForm() {
    console.log(this.loginForm.value);
    this.service.login(this.loginForm.value).subscribe((response) => {
      if (response.jwt != null) {
        alert('Hello, your token is ' + response.jwt);
        const jwtToken = response.jwt;
        localStorage.setItem('jwt', jwtToken);
        this.router.navigateByUrl('/app');
      }
    });
  }
}
