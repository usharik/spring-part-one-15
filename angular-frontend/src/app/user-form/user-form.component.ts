import { Component, OnInit } from '@angular/core';
import {User} from "../model/user";
import {UserService} from "../model/user.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-user-form',
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.scss']
})
export class UserFormComponent implements OnInit {

  public user = new User(-1, "", 0);
  public isError:boolean = false;

  constructor(private userService: UserService,
              private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit(): void {
    this.route.params.subscribe(param => {
      if (param.id == 'new') {
        this.user = new User(-1, "", 0);
        return;
      }
      this.userService.findById(param.id)
        .then(res => {
          this.isError = false;
          this.user = res;
        })
        .catch(err => {
          console.error(err);
          this.isError = true;
        });
    })
  }

  public save() {
    this.userService.save(this.user)
      .then(() => {
        this.isError = false;
        this.router.navigateByUrl('/user');
      })
      .catch(err => {
        console.error(err);
        this.isError = true;
      });
  }

}
