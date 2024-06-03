import { Component, OnInit } from '@angular/core';
import { RouterModule, Router } from '@angular/router';
import { NavbarComponent } from './pages/navbar/navbar.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    RouterModule,
    NavbarComponent,
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {
  title="app"
  hide = false

  constructor(private router: Router){}

  ngOnInit(): void {
    console.log(this.router.url=='/')
    if(this.router.url=='/')this.hide=true;
  }
}
