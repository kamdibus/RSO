import React from 'react'
import styled from 'styled-components';

export function BreadCrumb({ text, children, Container = DefaultContainer, first }) {
  return (
    <Container>
      {
        !first &&
        <Separator>
          /
        </Separator>
      }
      <Text>
        {text || children}
      </Text>
    </Container>
  )
}

const DefaultContainer = styled.span``

const Text = styled.span`
  margin-left: 5px;
`
const Separator = styled.span`
  margin-left: 5px;
`