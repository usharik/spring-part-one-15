import { Injectable } from '@angular/core';
import {User} from "./user";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private identity: number = 6;

  private users: { [key: number]: User } = {
    1: new User(1, 'First user', 18),
    2: new User(2, 'Second user', 27),
    3: new User(3, 'Third user', 34),
    4: new User(4, 'Fourth user', 52),
    5: new User(5, 'Fifth user', 21),
  };

  constructor(public http: HttpClient) { }

  public findAll() {
    return this.http.get<User[]>('/api/v1/user/all').toPromise();
    // return new Promise<User[]>((resolve, reject) =>
    // {
    //   resolve(
    //     Object.values(this.users)
    //   )
    // })
  }

  public findById(id: number) {
    return this.http.get<User>(`/api/v1/user/${id}`).toPromise();
    // return new Promise<User>((resolve, reject) =>
    // {
    //   resolve(
    //     this.users[id]
    //   )
    // })
  }

  public save(user: User) {
    if (user.id == null) {
      return this.http.post('/api/v1/user', user).toPromise()
    } else {
      return this.http.put('/api/v1/user', user).toPromise()
    }
    // return new Promise<void>((resolve, reject) => {
    //   if (user.id == null) {
    //     user.id = this.identity++;
    //   }
    //   this.users[user.id] = user;
    //   resolve();
    // })
  }

  public delete(id: number) {
    return this.http.delete<User>(`/api/v1/user/${id}`).toPromise();
    // return new Promise<void>((resolve, reject) => {
    //   delete this.users[id];
    //   resolve();
    // })
  }

}
