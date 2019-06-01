import React from 'react';
import styled from 'styled-components';
import { Route } from 'react-router-dom';
import { PureLink } from '../common/PureLink';
import { BreadCrumb } from '../common/BreadCrumb';
import { PageHeader } from '../Layout/PageHeader';
import { TopHeader } from '../Layout/TopHeader';
import { OfferList } from '../Party/OfferList';
import { OfferDetails } from './OfferDetails';
import SupplierNavigation from './SupplierNavigation';
import { OfferHistory } from '../Party/OfferList';
import { AccountEdit } from '../Party/AccountEdit';

export class SupplierView extends React.Component {
  render() {
    return (
      <Container>
        <TopHeader>
          <BreadCrumb first={true} Container={props => <PureLink to='/' {...props} />}>
            Home
          </BreadCrumb>
        </TopHeader>
        <Header />
        <SupplierNavigation />
        <Route
          path='/supplier/offers'
          component={props => <OfferList {...props} buildHref={id => `/supplier/offers/${id}`} />}
          exact={true} />
        <Route
          path='/supplier/offers-history'
          component={props => <OfferHistory {...props} buildHref={id => `/supplier/offers-history/${id}`} />}
          exact={true} />
        <Route
          path='/supplier/account'
          component={AccountEdit}
          exact={true} />
        <Route
          path='/supplier/(offers|offers-history)/:id'
          component={props => <OfferDetails offerId={props.match.params.id} />} />
      </Container>
    )
  }
}

function Header() {
  return (
    <PageHeader>
      <BreadCrumb first={true} Container={props => <PureLink to='/supplier' {...props} />}>
        Supplier
      </BreadCrumb>
      <Route
        path='/supplier/offers'
        component={() => (
          <BreadCrumb
            text="Offers"
            Container={props => <PureLink to='/supplier/offers' {...props} />}
          />
        )}
      />
      <Route path='/supplier/offers/:id' component={({ match }) => <BreadCrumb text={match.params.id} />} />
      <Route
        path='/supplier/offers-history'
        component={() => (
          <BreadCrumb
            text="History"
            Container={props => <PureLink to='/supplier/offers-history' {...props} />}
          />
        )}
      />
      <Route path='/supplier/offers-history/:id' component={({ match }) => <BreadCrumb text={match.params.id} />} />
      <Route path='/supplier/account' component={() => <BreadCrumb text="Account" />} />
    </PageHeader>
  )
}

const Container = styled.div`
  width: 100%;
  height: 100%;
`;
