import { AuthService } from './../../service/auth.service';
import { UserService } from './../../service/user.service';
import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { MenuItem } from 'primeng/api';
import { ButtonModule } from 'primeng/button';
import { MenubarModule } from 'primeng/menubar';
import { OverlayPanelModule } from 'primeng/overlaypanel';
import { MessageService } from 'primeng/api';
import { ToastModule } from 'primeng/toast';
import { NotificationService } from '../../service/notification.service';
import { EmailDto } from '../../dto/email/email.dto';
import { NonNullableFormBuilder, Validators } from '@angular/forms';
import { NotificationResDto } from '../../dto/notification/notification-res.dto';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [
    CommonModule,
    MenubarModule,
    RouterModule,
    NavbarComponent,
    ButtonModule,
    OverlayPanelModule,
    ToastModule
  ],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css',
  providers: [MessageService]
})
export class NavbarComponent implements OnInit {
  startItems: MenuItem[] = [];
  endItems: MenuItem[]= [];

  profileItems: MenuItem[]=[];

  userRole: string = "" ;

  emailDto: EmailDto = {email:localStorage.getItem('email') || ''}

  result!: string;
  messages!: string[];

  constructor(private userService: UserService, private authService: AuthService, private messageService: MessageService, private notificationService: NotificationService, private fb:NonNullableFormBuilder ){}
  
  ngOnInit(): void {
    
    this.userRole = this.userService.getRole();

    this.startItems = this.getItemsByRole(this.userRole);

    this.endItems = [
      { label: 'Chat', icon: 'pi pi-inbox', routerLink: ['/chat'] },
      { label: 'Notifications', icon: 'pi pi-bell', command: () => {this.showNotification("success","success","works"); }},
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
      { label: 'Client Schedules', icon: 'pi pi-calendar', routerLink: ['/PS/calendarview'] },
      { label: 'Documents', icon: 'pi pi-file', routerLink: ['/client-calendar'] },
      { label: 'Create Client Schedule', icon: 'pi pi-calendar-plus', routerLink: ['/PS/createschedule'] },
      { label: 'Schedule Change Approval', icon: 'pi pi-check-circle', routerLink: ['/PS/schedulechangeapproval'] },
    ]

    const clItems: MenuItem[] = [
      ...commonItems,
      { label: 'View Calendar', icon: 'pi pi-calendar', routerLink: ['/CL/viewcalendar'] },
      { label: 'Request Change', icon: 'pi pi-send', routerLink: ['/CL/requestchange'] },
    ]

    if(role=="CL")return clItems;
    else if(role=="SA")return saItems;
    else if(role=="PS") return psItems;
    else return commonItems;


    this.checkForNotification();
  }

  showNotification(severity: string, summary: string, detail: string) {
    this.notificationService.notificationsGetAll(this.emailDto).subscribe(res => {{
      for(let i = 0; i < res.length; i++){
          console.log(res[i])
          this.messageService.add({ severity:"info", summary:"Notification", detail:res[i].message, life:9999999999 });
      }
    }})
    this.notificationService.notificationsDeleteAll(this.emailDto).subscribe(res => {{
      console.log("This should be deleted already")
    }})
  }

  checkForNotification(){
    this.notificationService.notificationsCheckDeadlines(this.emailDto).subscribe(res => {{
      for(let i = 0; i < res.length; i++){
        console.log(res[i])
        this.messageService.add({ severity:"info", summary:"Notification", detail:res[i].message, life:9999999999 });
    }
    }})
  }
}
