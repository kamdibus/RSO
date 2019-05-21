import React from 'react';
import styled from 'styled-components';
import { PageHeader } from '../Layout/PageHeader';
import { PureLink } from '../common/PureLink';
import { BreadCrumb } from '../common/BreadCrumb';
import { TopHeader } from '../Layout/TopHeader';
import { Route } from 'react-router-dom';
import { ActionRow } from '../Layout/ActionRow';
import { Action } from '../Layout/Action';
import { UserList } from './UserList';
import { AccountEdit } from '../Party/AccountEdit';

export class AdminView extends React.Component {
  render() {
    return (
      <Container>
        <TopHeader>
          <BreadCrumb first={true} Container={props => <PureLink to='/' {...props} />}>
            Home
          </BreadCrumb>
        </TopHeader>
        <PageHeader>
          <BreadCrumb first={true} Container={props => <PureLink to='/admin' {...props} />}>
            Admin
          </BreadCrumb>
          <Route path='/admin/users' component={() => <BreadCrumb text="Users" />} />
        </PageHeader>
        <AdminNavigation />
        <Body>
          <Route path='/admin/users' component={UserList} exact={true} />
          <Route path='/admin/users/:id' component={(props) => <AccountEdit userId={props.match.params.id} />} />
        </Body>
      </Container>
    )
  }
}

const Container = styled.div`
  width: 100%;
  height: 100%;
`;

const Body = styled.div`
  display: flex;
  flex-direction: column;
`

function AdminNavigation(props) {
  return (
    <section>
      <ActionRow>
        <Action linkTo='/admin/users'>
          USER LIST
        </Action>
      </ActionRow>
    </section>
  )
}