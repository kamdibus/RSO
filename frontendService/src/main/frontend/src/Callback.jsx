import React, {Component} from 'react';
import {withRouter} from 'react-router-dom';
import auth0Client from './backend/auth/auth0';
import { parse as parseQuery } from 'query-string'

class Callback extends Component {
  async componentDidMount() {
    const redirect = (to) => {
      const query = parseQuery(window.location.search)
      let redirectTo = '/'
      if (to != null) {
        redirectTo = to
      }
      else {
        redirectTo = query && query.redirect_to || '/'
      }
      const userType = auth0Client.userType
      if (redirectTo === '/' && userType) {
        redirectTo = '/' + userType
      }

      /// prevent page refresh related errors during authorization process
      if (redirectTo === '/callback') {
        redirectTo = '/'
      }

      this.props.history.replace(redirectTo);
    }
    try {
      await auth0Client.handleAuthentication();
    }
    catch(e) {
      console.log(e)
      return redirect('/')
    }
    redirect()
  }

  render() {
    return (
      <p>Loading profile...</p>
    );
  }
}

export default withRouter(Callback);