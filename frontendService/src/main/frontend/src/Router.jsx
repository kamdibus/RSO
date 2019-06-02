import React from 'react';
import { SupplierView } from './components/Supplier/SupplierView';
import { ConsumerView } from './components/Consumer/ConsumerView';
import { BrowserRouter, Switch, Route, Redirect } from 'react-router-dom';
import styled from 'styled-components';
import { ActionRow } from './components/Layout/ActionRow';
import { Action } from './components/Layout/Action';
import { AdminView } from './components/Admin/AdminView';
import SecuredRoute from './components/common/SecuredRoute';
import Callback from './Callback';
import auth0Client from './backend/auth/auth0';

class Router extends React.Component {
  render() {
    const { checkingSession } = this.props
    return (
      <React.Fragment>
        <SecuredRoute component={ConsumerView} path='/consumer' checkingSession={checkingSession} />
        <SecuredRoute component={SupplierView} path='/supplier' checkingSession={checkingSession} />
        <SecuredRoute component={AdminView} path='/admin' checkingSession={checkingSession} />
        <SecuredRoute component={RoleSelect} path='/' checkingSession={checkingSession} exact={true} />

        <Route exact path='/callback' component={Callback}/>
        {/* <Redirect from='/' to='/role-select' /> */}
      </React.Fragment>
    )
  }
}

function RoleSelect() {
  return (
    <Container>
      <ActionRow>
        <Action linkTo='/supplier' text="Supplier" />
        <Action linkTo='/consumer' text="Consumer" />
        <Action linkTo='/admin' text="Admin" />
        <Action onClick={auth0Client.signOut} text="Log out" /> 
      </ActionRow>
    </Container>
  )
}

const Container = styled.div`
  width: 100%;
  height: 100%;
`

export default Router