import {Component, OnInit} from '@angular/core';
import {AuthService} from "../shared/service/auth.service";
import {TokenStorageService} from "../shared/service/token-storage.service";

@Component({
  selector: 'login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  form: any = {
    username: null,
    password: null
  };
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = [];

  constructor(private _auth: AuthService, private _tokenStorage: TokenStorageService) {
  }

  ngOnInit(): void {
    if (this._tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.roles = this._tokenStorage.getUser()?.roles;
    }
  }

  onSubmit(): void {
    const {username, password} = this.form;

    this._auth.login(username, password).subscribe(
      data => {
        this._tokenStorage.saveToken(data.token);
        this._tokenStorage.saveUser(data);

        this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.roles = this._tokenStorage.getUser()?.roles;
        this.reloadPage();
      },
      err => {
        this.errorMessage = err.error.message;
        this.isLoginFailed = true;
      }
    );
  }

  reloadPage(): void {
    window.location.reload();
  }

}
