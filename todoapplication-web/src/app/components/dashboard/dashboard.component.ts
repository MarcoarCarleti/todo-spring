import { Component, OnInit } from '@angular/core';
import { JwtService } from '../../service/jwt.service';
import { Observable } from 'rxjs';
import { CommonModule } from '@angular/common';

interface Tasks {
  id: number;
  title: string;
  description: string;
  done: boolean;
}

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css',
  providers: [JwtService],
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
}
