import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { UserLogin } from './domian/user.login.model';
import { BaseHttpService } from 'src/app/shared/services/http/base.http.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  title = 'taqweme-FE';
  @ViewChild('form') form: NgForm;

  constructor(private service: BaseHttpService) { }

  ngOnInit(): void {
  }

  onSubmit(): boolean {
    console.log(this.form);
    const user: UserLogin = this.constructUser(this.form);
    console.log(JSON.stringify(user));

    this.service.login(user).subscribe(
      (data) => {
        localStorage.setItem('user', JSON.stringify(data));
        console.log('User ==> ' + localStorage.getItem('user'));
      },
      (error) => {
        console.log(error);
      }
    );

    if (!this.form.valid) {
      console.log('invalid');
      return false;
    }
    console.log('valid');
    return true;
  }

  private constructUser(form: NgForm): UserLogin {
    return new UserLogin(form.value.username, form.value.password);
  }

}
