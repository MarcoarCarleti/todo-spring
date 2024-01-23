import { Component, Input, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { BsModalRef } from 'ngx-bootstrap/modal';
import { JwtService } from '../../../service/jwt/jwt.service';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';

interface Tasks {
  id: number;
  title: string;
  description: string;
  done: boolean;
}

@Component({
  selector: 'app-edit-dialog',
  standalone: true,
  imports: [HttpClientModule, ReactiveFormsModule],
  templateUrl: './edit-dialog.component.html',
  styleUrl: './edit-dialog.component.css',
  providers: [JwtService],
})
export class EditDialogComponent implements OnInit {
  task!: Tasks;
  form!: FormGroup;

  costumerEmail: any = localStorage.getItem('email');

  constructor(
    public bsModalRef: BsModalRef,
    private formBuilder: FormBuilder,
    private service: JwtService
  ) {}

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      title: [this.task.title, Validators.required],
      description: [this.task.description, Validators.required],
    });
  }

  onSubmit(taskId: number) {
    this.service.updateTask(taskId, this.form.value).subscribe(() => {
      this.service.task(this.costumerEmail, 'false');
      location.reload();
    });
  }
}
