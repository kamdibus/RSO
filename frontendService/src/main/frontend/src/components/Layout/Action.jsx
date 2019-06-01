import React from 'react'
import { ActionTile } from './ActionTile';
import { PureLink } from '../common/PureLink';
import styled, { css } from 'styled-components';
import { withRouter, matchPath } from 'react-router';

export const Action = withRouter(
  function (props) {
    const { matchExact } = props
    const Wrapper = props.linkTo ? PureLink : React.Fragment;
    const matchLocation = () => {
      const match = matchPath(props.location.pathname, props.linkTo)
      
      if (matchExact) {
        return match && match.isExact
      }
      return match
    }
    
    const active = props.linkTo && matchLocation()
    return (
      <Container active={active}>
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

const propMarginActive = ({ active }) => active && css`
  margin-top: 8px;
`

const Container = styled.div`
  margin: ${props => props.margin ? props.margin : '10px'};
  ${propMarginActive};
  &:first-child {
    margin-left: 0;
  }
`

const ActionText = styled.span`
  color: white;
  font-size: 14px;
  line-height: 1.5em;
  text-transform: uppercase;
`