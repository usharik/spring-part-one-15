import { Component, OnInit } from '@angular/core';
import {UserService} from "../model/user.service";
import {User} from "../model/user";

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.scss']
})
export class UserListComponent implements OnInit {

  public users: User[] = [];
  public isError: boolean = false;

  constructor(public userService: UserService) { }

  ngOnInit(): void {
    this.retrieveUsers();
  }

  private retrieveUsers() {
    this.userService.findAll()
      .then(res => {
        this.isError = false;
        this.users = res;
      })
      .catch(err => {
        this.isError = true;
        console.log(err);
      })
  }

  delete(id: number) {
    this.userService.delete(id)
      .then(() => {
        this.retrieveUsers();
      })
  }
}
