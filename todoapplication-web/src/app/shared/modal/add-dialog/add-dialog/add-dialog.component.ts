import { Component } from '@angular/core';
import {
  FormGroup,
  FormBuilder,
  Validators,
  ReactiveFormsModule,
} from '@angular/forms';
import { BsModalRef } from 'ngx-bootstrap/modal';
import { JwtService } from '../../../../service/jwt/jwt.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-dialog',
  standalone: true,
  imports: [ReactiveFormsModule],
  providers: [JwtService],
  templateUrl: './add-dialog.component.html',
  styleUrl: './add-dialog.component.css',
})
export class AddDialogComponent {
  form!: FormGroup;

  constructor(
    public bsModalRef: BsModalRef,
    private formBuilder: FormBuilder,
    private service: JwtService,
    private route: Router
  ) {}

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      title: ['', Validators.required],
      description: ['', Validators.required],
      done: [false],
    });
  }

  onSubmit() {
    const body = {
      title: this.form.value.title,
      description: this.form.value.description,
      done: false,
      customerId: localStorage.getItem('email'),
    };

    this.service.addTask(body).subscribe(() => {
      this.service.task('false');
      location.reload();
    });
  }
}
