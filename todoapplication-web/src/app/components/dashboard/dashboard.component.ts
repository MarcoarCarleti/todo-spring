import { Component, OnInit } from '@angular/core';
import { JwtService } from '../../service/jwt.service';
import { Observable } from 'rxjs';
import { CommonModule } from '@angular/common';
import { NgbAccordionModule } from '@ng-bootstrap/ng-bootstrap';
import { HeaderComponent } from "../header/header.component";

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
    providers: [JwtService],
    imports: [CommonModule, NgbAccordionModule, HeaderComponent]
})
export class DashboardComponent implements OnInit {
  constructor(private service: JwtService) {}
  tasks!: Tasks[];

  ngOnInit(): void {
    this.getTask();

    setTimeout(() => {
      console.log(this.tasks);
    }, 3000);
  }

  getTask() {
    this.service.task().subscribe((res) => {
      console.log(res);
      this.tasks = res;
    });
  }

  // openAccordion() {
  //   this.button.coll
  // }
}
