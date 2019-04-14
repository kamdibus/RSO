import React from 'react'
import { ActionTile } from './ActionTile';
import { PureLink } from '../common/PureLink';
import styled from 'styled-components';

export function Action(props) {
  const Wrapper = props.linkTo ? PureLink : React.Fragment;

  return (
    <Container>
      <Wrapper to={props.linkTo}>
        <ActionTile onClick={props.onClick}>
          <ActionText>
            {props.text || props.children}
          </ActionText>
        </ActionTile>
      </Wrapper>    
    </Container>
  )
}

const Container = styled.div`
  margin: ${props => props.margin ? props.margin : '10px'};
`

const ActionText = styled.span`
  color: white;
  font-size: 14px;
  line-height: 1.5em;
`