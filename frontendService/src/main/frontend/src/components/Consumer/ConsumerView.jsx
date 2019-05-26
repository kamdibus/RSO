import React from 'react';
import styled from 'styled-components';
import { PageHeader } from '../Layout/PageHeader';
import { PureLink } from '../common/PureLink';
import { BreadCrumb } from '../common/BreadCrumb';
import { TopHeader } from '../Layout/TopHeader';
import { Route } from 'react-router-dom';
import { OfferEdit } from './OfferEdit';
import { ActionRow } from '../Layout/ActionRow';
import { Action } from '../Layout/Action';
import { OfferHistory } from '../Party/OfferList';
import { AccountEdit } from '../Party/AccountEdit';
import { Switch } from 'react-router-dom';

export class ConsumerView extends React.Component {
  render() {
    return (
      <Container>
        <TopHeader>
          <BreadCrumb first={true} Container={props => <PureLink to='/' {...props} />}>
            Home
          </BreadCrumb>
        </TopHeader>
        <PageHeader>
          <BreadCrumb first={true} Container={props => <PureLink to='/consumer' {...props} />}>
            Consumer
          </BreadCrumb>
          <Switch>
            <Route path='/consumer/offers/new' component={() => <BreadCrumb text="Create offer" />} />
            <Route path='/consumer/offers-history' component={() => <BreadCrumb text="History" />} exact={true} />
            <Route path='/consumer/account' component={() => <BreadCrumb text="Account" />} exact={true} />
          </Switch>
        </PageHeader>
        <ConsumerNavigation />
        <Body>
          <Switch>
            <Route
              path='/consumer/offers-history'
              /// consider this
              // component={props => <OfferHistory {...props} buildHref={id => `/consumer/offers/${id}`} />}
              component={OfferHistory}
              exact={true} />
            <Route
              path='/consumer/offers/new'
              component={OfferEdit}
              exact={true} />
            <Route
              path='/consumer/account'
              component={AccountEdit}
              exact={true} />
          </Switch>
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

function ConsumerNavigation(props) {
  return (
    <section>
      <ActionRow>
        <Action linkTo='/consumer/offers/new'>
          CREATE OFFER
        </Action>
        <Action linkTo='/consumer/offers-history'>
          HISTORY
        </Action>
        <Action linkTo='/consumer/account'>
          ACCOUNT SETTINGS
        </Action>
      </ActionRow>
    </section>
  )
}