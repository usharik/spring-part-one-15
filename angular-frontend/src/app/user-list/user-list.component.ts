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
  totalPages: number = 0;
  numbers: number[] = [];
  pageNumber: number = 1;
  isError: boolean = false;
  last: boolean = false;
  first: boolean = false;

  constructor(public userService: UserService) {}

  ngOnInit(): void {
    this.retrieveAllUsers();
  }

  private retrieveAllUsers() {
    this.userService.findAll(this.pageNumber)
      .then(res => {
        this.isError = false;
        this.users = res.content;
        this.numbers = Array.from(Array(res.totalPages).keys());
        this.pageNumber = res.pageable.pageNumber;
        this.totalPages = res.totalPages;
        this.last = res.last;
        this.first = res.first;
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

  goToPage(num: number) {
    this.pageNumber = num;
    this.retrieveAllUsers();
  }
}
