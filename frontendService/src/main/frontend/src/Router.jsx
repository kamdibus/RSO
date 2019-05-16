import React from 'react';
import { SupplierView } from './components/Supplier/SupplierView';
import { ConsumerView } from './components/Consumer/ConsumerView';
import { BrowserRouter, Switch, Route, Redirect, withRouter } from 'react-router-dom';
import styled from 'styled-components';
import { ActionRow } from './components/Layout/ActionRow';
import { Action } from './components/Layout/Action';

class Router extends React.Component {
  render() {
    return (
      <BrowserRouter>
        <Switch>
          <Route component={ConsumerView} path='/consumer' />
          <Route component={SupplierView} path='/supplier' />
          <Route component={RoleSelect} path='/role-select' />
          <Redirect from='/' to='/role-select' />
        </Switch>
      </BrowserRouter>
    )
  }
}

function RoleSelect() {
  return (
    <Container>
      <ActionRow>
        <Action linkTo='/supplier' text="Supplier" />
        <Action linkTo='/consumer' text="Consumer" />
      </ActionRow>
    </Container>
  )
}

const Container = styled.div`
  width: 100%;
  height: 100%;
`

export default Router