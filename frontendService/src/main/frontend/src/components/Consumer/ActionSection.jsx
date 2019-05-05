import styled from "styled-components";
import React from 'react';
import { ActionRow } from "../Layout/ActionRow";
import { PrimaryButton } from "../common/Button";

export function ActionSection({ onAccept, onReject }) {
  return (
    <Container>
      <ActionRow>
        <PrimaryButton onClick={onAccept}>Accept & Pay</PrimaryButton>
        <PrimaryButton onClick={onReject}>Reject</PrimaryButton>
      </ActionRow>
    </Container>
  )
}

const Container = styled.section`
`

