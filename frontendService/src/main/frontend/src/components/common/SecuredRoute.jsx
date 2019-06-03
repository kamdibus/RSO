import React from 'react';
import { Route } from 'react-router-dom';
import auth0Client from '../../backend/auth/auth0';

function SecuredRoute(props) {
  const { component: Component, path, checkingSession, exact } = props;
  return (
    <Route path={path} exact={exact} render={(props) => {
      if (checkingSession) { 
        return <h3 className="text-center">Validating session...</h3>;
      }
      if (!auth0Client.isAuthenticated()) {
        auth0Client.signIn();
        return <div>Redirecting...</div>;
      }
      return <Component {...props} />
    }} />
  );
}

export default SecuredRoute;