import {Component, OnInit} from '@angular/core';
import {AuthService} from "../shared/service/auth.service";

@Component({
    selector: 'register',
    templateUrl: './register.component.html',
    styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

    form: any = {
        username: null,
        email: null,
        password: null
    };
    isSuccessful = false;
    isSignUpFailed = false;
    errorMessage = '';

    constructor(private _auth: AuthService) {
    }

    ngOnInit(): void {
    }

    onSubmit(): void {
        const {username, email, password} = this.form;

        this._auth.register(username, email, password).subscribe(
            data => {
                console.log(data);
                this.isSuccessful = true;
                this.isSignUpFailed = false;
            },
            err => {
                this.errorMessage = err.error.message;
                this.isSignUpFailed = true;
            }
        );
    }

}
