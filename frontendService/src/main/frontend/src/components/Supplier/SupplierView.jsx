import React from 'react';
import styled from 'styled-components';
import { Route } from 'react-router-dom';
import { PureLink } from '../common/PureLink';
import { OfferEdit } from './OfferEdit';
import { BreadCrumb } from '../common/BreadCrumb';
import { DataTable } from '../common/DataTable';
import { SupplierService } from '../../backend/supplier/service/supplier';
import { PageHeader } from '../Layout/PageHeader';
import { ActionRow } from '../Layout/ActionRow';
import { Action } from '../Layout/Action';
import { LoadingOverlay } from '../common/Loading/Overlay';
import { DataRow } from '../Layout/DataRow';
import { TopHeader } from '../Layout/TopHeader';
import { DataSection } from './DataSection';

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
          <Route path='/supplier/offers/new' component={() => <BreadCrumb text="Offer" />} />
        </PageHeader>
        <Route path='/supplier' component={ActionSection} exact={true} />
        <Route path='/supplier' component={DataSection} exact={true} />
        <Route path='/supplier/offers/new' component={OfferEdit} />
      </Container>
    )
  }
}

const Container = styled.div`
  width: 100%;
  height: 100%;
`;


function ActionSection(props) {
  return (
    <ActionRow>
      <Action linkTo='/supplier/offers/new'>
        CREATE OFFER
      </Action>
    </ActionRow>
  )
}