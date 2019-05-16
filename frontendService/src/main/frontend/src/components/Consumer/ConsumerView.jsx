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

export class ConsumerView extends React.Component {
  render() {
    const { id: offerId } = this.props.match.params 
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
          <Route path='/consumer/offers/new' component={() => <BreadCrumb text="Offer" />} />
        </PageHeader>
        <Body>
          <Route path='/consumer' component={ActionSection} exact={true} />
          <Route path='/consumer/offers/new' component={OfferEdit} />
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

function ActionSection(props) {
  return (
    <ActionRow>
      <Action linkTo='/consumer/offers/new'>
        CREATE OFFER
      </Action>
    </ActionRow>
  )
}