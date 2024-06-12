import { AuthService } from './../../service/auth.service';
import { UserService } from './../../service/user.service';
import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { MenuItem } from 'primeng/api';
import { MenubarModule } from 'primeng/menubar';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [
    CommonModule,
    MenubarModule,
    RouterModule,
    NavbarComponent,
  ],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent implements OnInit {
  startItems: MenuItem[] = [];
  endItems: MenuItem[]= [];

  profileItems: MenuItem[]=[];

  userRole: string = "" ;

  constructor(private userService: UserService, private authService: AuthService){}
  
  ngOnInit(): void {
    
    this.userRole = this.userService.getRole();

    this.startItems = this.getItemsByRole(this.userRole);

    this.endItems = [
      { label: 'Chat', icon: 'pi pi-inbox', routerLink: ['/chat'] },
      { label: 'Notifications', icon: 'pi pi-bell', routerLink: ['/notifications'] },
      {
        label: "Profile", icon:"pi pi-user", items: [
          { label: 'User Settings', icon: 'pi pi-cog', routerLink: ['/setting'], styleClass: 'ml-auto' },
          { label: 'Sign out', icon: 'pi pi-sign-out', 
          command: (event) => {
            this.authService.logout();
          },
          styleClass: 'ml-auto' },
        ]
      }
    ]

    // console.log(this.userRole)

  }

  getItemsByRole(role: string): MenuItem[] {
    const commonItems: MenuItem[] = [
      { icon: 'pi pi-home', routerLink: [`/${role}/dashboard`] },
    ];

    const saItems: MenuItem[] = [
      ...commonItems, 
      { label: 'Create User', icon: 'pi pi-user-plus', routerLink: ['/SA/createuser'] },
      { label: 'Assign Client-PS', icon: 'pi pi-users', routerLink: ['/SA/assignuser'] },
    ]

    const psItems: MenuItem[] = [
      ...commonItems,
      { label: 'Client Schedules', icon: 'pi pi-calendar', routerLink: ['/calendar-list'] },
      {
        label: "Document", icon:"pi pi-file", items: [
          { label: 'Upload', icon: 'pi pi-upload', routerLink: ['/Document/upload'] },
          { label: 'Download', icon: 'pi pi-download', routerLink: ['/Document/download'] }
        ]
      },
      { label: 'Create Client Schedule', icon: 'pi pi-calendar-plus', routerLink: ['/PS/createschedule'] },
      { label: 'Schedule Change Approval', icon: 'pi pi-check-circle', routerLink: ['/PS/schedulechangeapproval'] },
    ]

    const clItems: MenuItem[] = [
      ...commonItems,
      { label: 'View Calendar', icon: 'pi pi-calendar', routerLink: ['/CL/viewcalendar'] },
      {
        label: "Document", icon:"pi pi-file", items: [
          { label: 'Upload', icon: 'pi pi-upload', routerLink: ['/Document/upload'] },
          { label: 'Download', icon: 'pi pi-download', routerLink: ['/Document/download'] }
        ]
      },
      { label: 'Request Change', icon: 'pi pi-send', routerLink: ['/CL/requestchange'] },
    ]

    if(role=="CL")return clItems;
    else if(role=="SA")return saItems;
    else if(role=="PS") return psItems;
    else return commonItems;
  }


}
