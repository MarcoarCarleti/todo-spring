import { Component, OnInit } from '@angular/core';
import { BouncingLogoComponent } from '../bouncing-logo/bouncing-logo.component';
import { JwtService } from '../../service/jwt/jwt.service';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [BouncingLogoComponent, ReactiveFormsModule, CommonModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css',
  providers: [JwtService],
})
export class RegisterComponent implements OnInit {
  registerForm: FormGroup | any;

  password: string = 'password';
  show: boolean = false;

  constructor(private service: JwtService, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.registerForm = this.fb.group(
      {
        name: ['', Validators.required],
        email: ['', [Validators.required, Validators.email]],
        password: ['', Validators.required],
        confirmPassword: ['', Validators.required],
        admin: [false],
      },
      { validator: this.passwordMathValidator }
    );
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

  passwordMathValidator(formGroup: FormGroup) {
    const password = formGroup.get('password')?.value;
    const confirmPassword = formGroup.get('confirmPassword')?.value;

    if (password !== confirmPassword) {
      formGroup.get('confirmPassword')?.setErrors({
        passwordMismatch: true,
      });
    } else {
      formGroup.get('confirmPassword')?.setErrors(null);
    }
  }

  submitForm() {
    console.log(this.registerForm.value);
    this.service.register(this.registerForm.value).subscribe((response) => {
      if (response.id != null) {
        alert('Conta criada ' + response.name);
      }
    });
  }
}
