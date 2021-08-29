import { Component, OnInit } from '@angular/core';
import {TokenStorageService} from "../../../shared/service/token-storage.service";
import {Router} from "@angular/router";

@Component({
  selector: 'dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  constructor(private _router: Router, private _tokenStorage: TokenStorageService) { }

  ngOnInit(): void {
    // todo spostare logica in guardia comune a tutte le rotte
    if (!this._tokenStorage.getUser()) {
      this._router.navigateByUrl('login');
    }
  }

}
