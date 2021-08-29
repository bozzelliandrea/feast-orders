import {Component, Input, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {TokenStorageService} from "../../service/token-storage.service";

@Component({
  selector: 'navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  @Input() isLoggedIn: boolean;
  @Input() username?: string;

  private _hamburger: Element | null = null;
  private _navMenu: Element | null = null;
  private _isMenuActive: boolean = false;

  constructor(private _router: Router, private _tokenStorage: TokenStorageService) {
  }

  ngOnInit(): void {
    this._hamburger = document.querySelector(".hamburger");
    this._navMenu = document.querySelector(".nav-menu");
  }

  public navigateTo(routeName: string): void {
    if (routeName != 'dashboard')
      this.toggleMenu();

    this._router.navigateByUrl('/' + routeName);
  }

  public toggleMenu(): void {
    if (!this._isMenuActive) {
      this._hamburger?.classList.toggle("active");
      this._navMenu?.classList.toggle("active");
    } else {
      this._hamburger?.classList.remove("active");
      this._navMenu?.classList.remove("active");
    }
  }

  public logout(): void {
    this._tokenStorage.signOut();
    window.location.reload();
  }
}
