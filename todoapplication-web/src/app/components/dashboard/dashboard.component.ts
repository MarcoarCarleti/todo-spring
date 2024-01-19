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

interface Tasks {
  id: number;
  title: string;
  description: string;
  done: boolean;
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
  ],
})
export class DashboardComponent implements OnInit {
  tasks!: Tasks[];
  bsModalRef: BsModalRef | undefined;
  taskDone: boolean | undefined;

  constructor(
    private route: Router,
    private service: JwtService,
    private modalService: NgbModal
  ) {}

  ngOnInit(): void {
    this.getTask();
  }

  getTask() {
    this.service.task().subscribe(
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

  handleDeleteClick(taskId: number) {
    this.service.deleteTask(taskId).subscribe(() => {
      this.getTask();
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
      this.getTask();
    });
  }

  handleAddClick() {
    this.modalService.open(AddDialogComponent, {
      backdrop: true,
    });
  }
}

// openAccordion() {
//   this.button.coll
// }
