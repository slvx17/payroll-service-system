import { Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { ClDashboardComponent } from './pages/cl/cl-dashboard/cl-dashboard.component';
import { PsDashboardComponent } from './pages/ps/ps-dashboard/ps-dashboard.component';
import { SaDashboardComponent } from './pages/sa/sa-dashboard/sa-dashboard.component';
import { CreateUserComponent } from './pages/sa/create-user/create-user.component';
import { AssignUserComponent } from './pages/sa/assign-user/assign-user.component';
import { CreateScheduleComponent } from './pages/ps/create-schedule/create-schedule.component';
import { ViewCalendarComponent } from './pages/cl/view-calendar/view-calendar.component';
import { RequestChangeComponent } from './pages/cl/request-change/request-change.component';
import { ScheduleChangeApprovalComponent } from './pages/ps/schedule-change-approval/schedule-change-approval.component';
import { PSCalendarViewComponent } from './pages/ps/ps-calendarview/ps-calendarview.component';

export const routes: Routes = [

    {
        path: "login",
        component: LoginComponent,
    }, { path: '', redirectTo: '/login', pathMatch: 'full' }, 
    {
        path: "PS/dashboard",
        component: PsDashboardComponent
    },
    {
        path:"SA/createuser",
        component: CreateUserComponent
    }
    ,{ path: 'SA/dashboard', redirectTo: 'SA/createuser', pathMatch: 'full' }, 
    {
        path:"SA/assignuser",
        component: AssignUserComponent
    },
    {
        path:"PS/createschedule",
        component: CreateScheduleComponent
    },
    {
        path:"PS/schedulechangeapproval",
        component: ScheduleChangeApprovalComponent
    },
    {
        path:"CL/viewcalendar",
        component: ViewCalendarComponent
    }
    ,{ path: 'CL/dashboard', redirectTo: "CL/viewcalendar", pathMatch: 'full' }, 
    {
        path:"CL/requestchange",
        component: RequestChangeComponent
    },
    {
        path:"PS/calendarview",
        component: PSCalendarViewComponent
    }
];
