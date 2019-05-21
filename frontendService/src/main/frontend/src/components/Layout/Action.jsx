import React from 'react'
import { ActionTile } from './ActionTile';
import { PureLink } from '../common/PureLink';
import styled from 'styled-components';
import { withRouter, matchPath } from 'react-router';

export const Action = withRouter(
  function (props) {
    const Wrapper = props.linkTo ? PureLink : React.Fragment;
    const matchExact = () => {
      const match = matchPath(props.location.pathname, props.linkTo)
      return match && match.isExact
    }
    
    const active = props.linkTo && matchExact()
    return (
      <Container>
        <Wrapper to={props.linkTo}>
          <ActionTile onClick={props.onClick} active={active}>
            <ActionText>
              {props.text || props.children}
            </ActionText>
          </ActionTile>
        </Wrapper>    
      </Container>
    )
  }
)

const Container = styled.div`
  margin: ${props => props.margin ? props.margin : '10px'};
`

const ActionText = styled.span`
  color: white;
  font-size: 14px;
  line-height: 1.5em;
  text-transform: uppercase;
`