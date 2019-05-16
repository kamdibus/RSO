import React from 'react';
import styled from 'styled-components';
import { Route } from 'react-router-dom';
import { PureLink } from '../common/PureLink';
import { BreadCrumb } from '../common/BreadCrumb';
import { PageHeader } from '../Layout/PageHeader';
import { TopHeader } from '../Layout/TopHeader';
import { OfferList } from './OfferList';
import { OfferDetails } from './OfferDetails';

export class SupplierView extends React.Component {
  render() {
    return (
      <Container>
        <TopHeader>
          <BreadCrumb first={true} Container={props => <PureLink to='/' {...props} />}>
            Home
          </BreadCrumb>
        </TopHeader>
        <PageHeader>
          <BreadCrumb first={true} Container={props => <PureLink to='/supplier' {...props} />}>
            Supplier
          </BreadCrumb>
        </PageHeader>
        <Route path='/supplier' component={OfferList} exact={true} />
        <Route path='/supplier/offers/:id' component={props => <OfferDetails offerId={props.match.params.id} />} /> />
      </Container>
    )
  }
}

const Container = styled.div`
  width: 100%;
  height: 100%;
`;
