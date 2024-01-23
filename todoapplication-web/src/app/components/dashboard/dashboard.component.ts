import { Component, OnInit, ViewChild } from '@angular/core';
import { JwtService } from '../../service/jwt/jwt.service';
import { Observable } from 'rxjs';
import { CommonModule } from '@angular/common';
import {
  NgbAccordionModule,
  NgbModal,
  NgbModalModule,
} from '@ng-bootstrap/ng-bootstrap';
import { HeaderComponent } from '../header/header.component';
import { MatIconModule } from '@angular/material/icon';
import { BsModalService, BsModalRef, ModalModule } from 'ngx-bootstrap/modal';
import { EditDialogComponent } from '../../shared/modal/edit-dialog/edit-dialog.component';
import { AddDialogComponent } from '../../shared/modal/add-dialog/add-dialog/add-dialog.component';
import { Route, Router, Routes } from '@angular/router';
import { MatSelectModule } from '@angular/material/select';
import {
  FormBuilder,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';

import { MatInputModule } from '@angular/material/input';

interface Tasks {
  id: number;
  title: string;
  description: string;
  done: boolean;
  customerEmail: string;
}

interface Filters {
  value: string;
  viewValue: string;
}

@Component({
  selector: 'app-dashboard',
  standalone: true,
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css',
  providers: [JwtService, BsModalService],
  imports: [
    CommonModule,
    NgbAccordionModule,
    HeaderComponent,
    MatIconModule,
    ModalModule,
    EditDialogComponent,
    NgbModalModule,
    MatSelectModule,
    FormsModule,
    MatFormFieldModule,
    MatSelectModule,
    MatInputModule,
    ReactiveFormsModule,
  ],
})
export class DashboardComponent implements OnInit {
  tasks!: Tasks[];
  filteredTasks!: Tasks[];
  bsModalRef: BsModalRef | undefined;
  taskDone: boolean | undefined;
  admin: boolean | undefined;
  form!: FormGroup;

  selectedEmail: string = '';
  selectedFilter: string = '';

  filters: Filters[] = [
    { value: '', viewValue: 'Todos' },
    { value: 'true', viewValue: 'Completos' },
    { value: 'false', viewValue: 'Incompletos' },
  ];

  costumerEmail: any = localStorage.getItem('email');

  emails!: Tasks[];
  uniqueArray!: any[];

  constructor(
    private route: Router,
    private service: JwtService,
    private modalService: NgbModal,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.admin = localStorage.getItem('admin') === 'true' ? true : false;

    this.form = this.fb.group({
      filter: ['', Validators.required],
    });

    if (this.admin) {
      this.getAllTasks();
    } else {
      this.getTask();
    }
    setTimeout(() => {
      this.handleFilterChange();
      this.uniqueArray = Array.from(
        new Set(this.emails.map((obj) => obj.customerEmail))
      ).map((email) => this.emails.find((obj) => obj.customerEmail === email));
    }, 500);
  }

  handleEmitRelatorio() {
    if (this.admin) {
      return this.service
        .emitRelatorio(this.selectedEmail, this.selectedFilter)
        .subscribe(
          (res) => {
            const blob = new Blob([res], { type: 'application/pdf' });
            const url = window.URL.createObjectURL(blob);
            window.open(url, '_blank');
          },
          (error) => {
            console.error('Erro na chamada da API:', error);
          }
        );
    } else {
      return this.service
        .emitRelatorio(this.costumerEmail, this.selectedFilter)
        .subscribe(
          (res) => {
            const blob = new Blob([res], { type: 'application/pdf' });
            const url = window.URL.createObjectURL(blob);
            window.open(url, '_blank');
          },
          (error) => {
            console.error('Erro na chamada da API:', error);
          }
        );
    }
  }

  handleFilterTextChange() {
    if (this.admin) {
      if (this.selectedEmail !== '') {
        this.service
          .getTasksByCustomerEmailAndTitle(
            this.selectedEmail,
            this.form.value.filter
          )
          .subscribe((res) => {
            this.tasks = res;
          });
      } else {
        this.service.getAllTasks(this.form.value.filter).subscribe(
          (res) => {
            this.tasks = res;
          },
          (err) => {}
        );
      }
    } else {
      this.service
        .getTasksByCustomerEmailAndTitle(
          this.costumerEmail,
          this.form.value.filter
        )
        .subscribe((res) => {
          this.tasks = res;
        });
    }
  }

  handleFilterChange() {
    if (this.admin) {
      if (this.selectedEmail !== '') {
        this.service
          .task(this.selectedEmail, this.selectedFilter)
          .subscribe((res) => {
            this.tasks = res;
          });
      } else {
        this.service.getAllTasksByDone(this.selectedFilter).subscribe(
          (res) => {
            this.tasks = res;
          },
          (err) => {
            if (err) {
              this.route.navigateByUrl('/');
            }
          }
        );
      }
    } else {
      this.service.task(this.costumerEmail, this.selectedFilter).subscribe(
        (res) => {
          this.tasks = res;

          console.log(res);
        },
        (err) => {
          if (err) {
            this.route.navigateByUrl('/');
          }
        }
      );
    }
  }

  handleEmailFilterChange() {
    if (this.admin) {
      this.service.filterTaskEmail(this.selectedEmail).subscribe((res) => {
        this.tasks = res;
      });

      this.service.getAllTasks(this.form.value.filter).subscribe((res) => {
        this.emails = res;
      });
    }
  }

  getTask() {
    const completedTask =
      this.selectedFilter === 'completos-1' ? 'true' : 'false';

    this.service.task(this.costumerEmail, completedTask).subscribe(
      (res) => {
        this.tasks = res;
      },
      (err) => {
        if (err) {
          this.route.navigateByUrl('/');
        }
      }
    );
  }

  getAllTasks() {
    this.service.getAllTasks(this.form.value.filter).subscribe(
      (res) => {
        this.tasks = res;
        this.emails = res;
      },
      (err) => {
        if (err) {
          this.route.navigateByUrl('/');
        }
      }
    );
  }

  handleDeleteClick(taskId: number) {
    this.service.deleteTask(taskId).subscribe(() => {
      if (this.admin) {
        this.getAllTasks();
      } else {
        this.getTask();
      }
    });
  }

  handleEditClick(task: Tasks) {
    const modalRef = this.modalService.open(EditDialogComponent, {
      backdrop: true,
    });
    modalRef.componentInstance.task = task;
  }

  handleUpdateTaskDone(task: Tasks) {
    const taskObject = {
      title: task.title,
      description: task.description,
      done: !task.done,
    };

    this.taskDone = taskObject.done;

    this.service.updateTask(task.id, taskObject).subscribe(() => {
      if (this.admin) {
        this.getAllTasks();
        location.reload();
      } else {
        this.getTask();
        location.reload();
      }
    });
  }

  handleAddClick() {
    this.modalService.open(AddDialogComponent, {
      backdrop: true,
    });
  }
}
