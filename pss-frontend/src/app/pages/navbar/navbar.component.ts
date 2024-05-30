import { UserService } from './../../service/user.service';
import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import { MenuItem } from 'primeng/api';
import { MenubarModule } from 'primeng/menubar';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [
    CommonModule,
    MenubarModule,
    RouterModule,
  ],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent implements OnInit {
  startItems: MenuItem[] = [];
  endItems: MenuItem[]= [];

  profileItems: MenuItem[]=[];

  userRole: string = "" ;

  constructor(private userService: UserService){}
  
  ngOnInit(): void {
    
    this.userRole = this.userService.getRole();

    this.startItems = this.getItemsByRole(this.userRole);

    this.endItems = [
      { label: 'Notifications', icon: 'pi pi-bell', routerLink: ['/notifications'] },
      {
        label: "Profile", icon:"pi pi-user", items: [
          { label: 'User Settings', icon: 'pi pi-cog', routerLink: ['/profile'], styleClass: 'ml-auto' },
          { label: 'Sign out', icon: 'pi pi-sign-out', routerLink: ['/sign-out'], styleClass: 'ml-auto' },
        ]
      }
    ]

    console.log(this.userRole)

  }

  getItemsByRole(role: string): MenuItem[] {
    const commonItems: MenuItem[] = [
      { icon: 'pi pi-home', routerLink: [`${role}/dashboard`] },
    ];

    const saItems: MenuItem[] = [
      ...commonItems, 
      { label: 'Create User', icon: 'pi pi-user-plus', routerLink: ['/create-user'] },
      { label: 'Assign Client-PS', icon: 'pi pi-users', routerLink: ['/assign-client-ps'] },
    ]

    const psItems: MenuItem[] = [
      ...commonItems,
      { label: 'Calendar List View', icon: 'pi pi-calendar', routerLink: ['/calendar-list'] },
      { label: 'Client Calendar View', icon: 'pi pi-calendar-plus', routerLink: ['/client-calendar'] },
      { label: 'Create Client Calendar', icon: 'pi pi-calendar-edit', routerLink: ['/create-client-calendar'] },
      { label: 'Schedule Change Approval', icon: 'pi pi-check-circle', routerLink: ['/schedule-change-approval'] },
    ]

    const clItems: MenuItem[] = [
      ...commonItems,
      { label: 'View Calendar', icon: 'pi pi-calendar', routerLink: ['/view-calendar'] },
      { label: 'Request Change', icon: 'pi pi-send', routerLink: ['/request-change'] },
    ]

    if(role=="CL")return clItems;
    else if(role=="SA")return saItems;
    else if(role=="PS") return psItems;
    else return commonItems;
  }


}
