import DoubleBounce from "./DoubleBounce";
import React from 'react';
import styled from "styled-components";

export function LoadingOverlay(props) {
  return (
    <Container>
      <DoubleBounce />
    </Container>
  )
}

const Container = styled.div`
  position: absolute;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  background-color: rgba(0,0,0,0.15);

  display: flex;
  justify-content: center;
  align-items: center;
`