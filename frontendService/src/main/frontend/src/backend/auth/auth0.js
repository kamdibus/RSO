import auth0 from 'auth0-js';
import { USERS_URL} from "../../environment";

class Auth {
  constructor() {
    this.auth0 = new auth0.WebAuth({
      domain: 'dev-fvyydqa0.eu.auth0.com',
      clientID: 'qoWgjWbU-A8ycOm7jKZcqiL4h2tyE7-Y',
      redirectUri: `${window.location.origin}/callback?redirect_to=${window.location.pathname}`,
      responseType: 'token id_token',
      scope: 'openid profile',
      leeway: 60
    });

    this.getProfile = this.getProfile.bind(this);
    this.handleAuthentication = this.handleAuthentication.bind(this);
    this.isAuthenticated = this.isAuthenticated.bind(this);
    this.signIn = this.signIn.bind(this);
    this.signOut = this.signOut.bind(this);
    this.authHeaders = this.getAuthHeaders.bind(this);
  }

  getProfile() {
    return this.profile;
  }

  getIdToken() {
    return this.idToken;
  }

  getAccessToken() {
    return this.accessToken;
  }

  isAuthenticated() {
    return new Date().getTime() < this.expiresAt;
  }

  signIn() {
    this.auth0.authorize();
  }

  handleAuthentication() {
    return new Promise((resolve, reject) => {
      this.auth0.parseHash((err, authResult) => {
        if (err) return reject(err);
        if (!authResult || !authResult.idToken) {
          return reject(err);
        }
        this.setSession(authResult);
        resolve();
      });
    })
  }

  setSession(authResult) {
    this.idToken = authResult.idToken;
    this.profile = authResult.idTokenPayload;
    this.accessToken = authResult.accessToken;
    // set the time that the id token will expire at
    this.expiresAt = authResult.idTokenPayload.exp * 1000;
    this.shareUserData(authResult, USERS_URL);
  }

  signOut() {
    this.auth0.logout({
      return_to: `${window.location.origin}`,
    });
  }

  silentAuth() {
    return new Promise((resolve, reject) => {
      this.auth0.checkSession({}, (err, authResult) => {
        if (err) return reject(err);
        this.setSession(authResult);
        resolve();
      });
    });
  }

  getAuthHeaders() {
    const token = this.getAccessToken();
    if (token == null) {
      return {}
    }
    return { 'Authorization': `Bearer ${token}` };
  }

    shareUserData = (authResult, url) => {
        let metaDataUrl = this.auth0.domain + '/userInfo' + "?access_token=" + authResult.accessToken;
        fetch(metaDataUrl, {
            method: 'GET',
            credentials: 'same-origin',
            headers: {
            'Content-Type': 'application/json'
            }
        }).then(function(response) {
          return response.json();
        }).then(function(data) {
            return fetch(url, {
                method: 'POST',
                credentials: 'same-origin',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(data)
            })
        })
    };
}

const auth0Client = new Auth();

export default auth0Client;