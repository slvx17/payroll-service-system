import { Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { ClDashboardComponent } from './pages/cl/cl-dashboard/cl-dashboard.component';
import { PsDashboardComponent } from './pages/ps/ps-dashboard/ps-dashboard.component';
import { SaDashboardComponent } from './pages/sa/sa-dashboard/sa-dashboard.component';

export const routes: Routes = [

    {
        path: "login",
        component: LoginComponent,
    }, { path: '', redirectTo: '/login', pathMatch: 'full' }, 
    {
        path: "CL/dashboard",
        component: ClDashboardComponent
    },

    {
        path: "SA/dashboard",
        component: SaDashboardComponent
    },
    {
        path: "PS/dashboard",
        component: PsDashboardComponent
    },
];
