import { Routes } from '@angular/router';
import { RegisterComponent } from './components/register/register.component';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { AccountComponent } from './components/account/account.component';
import { NotFoundComponent } from './components/not-found/not-found.component';

export const routes: Routes = [
  { path: '', component: LoginComponent },
  { path: 'register', component: RegisterComponent, pathMatch: 'full' },
  { path: 'app', component: DashboardComponent },
  { path: 'account', component: AccountComponent },
  { path: '**', redirectTo: 'not-found' },
  { path: 'not-found', component: NotFoundComponent },
];
