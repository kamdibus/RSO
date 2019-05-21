import React from 'react'
import { Action } from '../Layout/Action';
import { ActionRow } from '../Layout/ActionRow';
import styled from 'styled-components';

function SupplierNavigation() {
  return (
    <Container>
      <ActionRow>
        <Action linkTo='/supplier/offers' text="Offers" />
        <Action linkTo='/supplier/offers-history' text="History" />
        <Action linkTo='/supplier/account' text="Account settings" />
      </ActionRow>
    </Container>
  )
}

export default SupplierNavigation

const Container = styled.section`
`