import { Component, OnInit } from '@angular/core';
import {User} from "../model/user";
import {UserService} from "../model/user.service";

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.scss']
})
export class UserListComponent implements OnInit {

  users: User[] = [];
  isError: boolean = false;

  constructor(public userService: UserService) {}

  ngOnInit(): void {
    this.retrieveAllUsers();
  }

  private retrieveAllUsers() {
    this.userService.findAll()
      .then(res => {
        this.isError = false;
        this.users = res;
      })
      .catch(err => {
        console.error(err);
        this.isError = true;
      });
  }

  delete(id:number) {
    this.userService.delete(id)
      .then(() => this.retrieveAllUsers())
      .catch(err => {
        console.error(err);
        this.isError = true;
      });
  }


}
