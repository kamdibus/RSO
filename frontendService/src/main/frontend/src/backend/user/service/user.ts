import { User } from "../model/User";
import { delay } from "../../common/utils/delay";
import { HttpResponses, HttpResponse } from "../../common/model/HttpResponse";

export const UserService = {
  getCurrentUser: async function(): Promise<User> {
    await delay(1000);
    return {
      name: "User X",
      nip: "192031092",
      id: 11
    }
  },
  getUser: async function(): Promise<User> {
    await delay(1000);

    return {
      name: "User ZZ",
      nip: "1231290390",
      id: 4201
    }
  },
  editUser: async function(user: User): Promise<HttpResponse> {
    await delay(1500);

    return HttpResponses.ok();
  },
  getUsers: async function(): Promise<User[]> {
    await delay(1500);

    return [
      {
        name: "User X",
        nip: '1231231231',
        id: 112
      },
      {
        name: "User Y",
        nip: '442',
        id: 997
      },
      {
        name: "User Z",
        nip: '420',
        id: 420
      }
    ]
  }
}