import React from 'react';
import styled from 'styled-components';
import { PageHeader } from '../Layout/PageHeader';
import { PureLink } from '../common/PureLink';
import { BreadCrumb } from '../common/BreadCrumb';
import { TopHeader } from '../Layout/TopHeader';
import { DataSection } from './DataSection';

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
          <BreadCrumb first={true}>
            Consumer
          </BreadCrumb>
        </PageHeader>
        <DataSection offerId={offerId} />
      </Container>
    )
  }
}

const Container = styled.div`
  width: 100%;
  height: 100%;

  display: flex;
  flex-direction: column;
`;