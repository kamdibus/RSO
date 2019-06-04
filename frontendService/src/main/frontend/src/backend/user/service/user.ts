import { User } from "../model/User";
import { delay } from "../../common/utils/delay";
import { HttpResponses, HttpResponse } from "../../common/model/HttpResponse";
import Axios from "axios";
import auth0Client from "../../auth/auth0";

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
    const headers = auth0Client.getAuthHeaders();
    return Axios
      .get('/api/users/', { headers })
      .then(r => r.data)
  }
}