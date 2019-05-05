import React from 'react'
import styled, { keyframes, css } from 'styled-components'

export const DoubleBounce = ({
  size = 64,
  color = '#fff',
}) => {
  return (
    <StyledDoubleBounce size={size} color={color}>
      <Child />
      <Child isDelay />
    </StyledDoubleBounce>
  )
}

const debounce = keyframes`
  0%,
  100% {
    transform: scale(0);
  }
  50% {
    transform: scale(1);
  }
`

const size = (width, height = width) => css`
  width: ${width};
  height: ${height};
`
const sizePx = n => size(`${n}px`)
const propSize = ({ size }) => sizePx(size)

const Child = styled.div`
  width: 100%;
  height: 100%;
  border-radius: 50%;
  position: absolute;
  opacity: 0.6;
  top: 0;
  left: 0;
  animation: ${debounce} 2s infinite ease-in-out;
  ${p => p.isDelay && { 'animation-delay': -1 }};
`
const propMargin = ({ size = 0 }) => css`
  margin: ${size}px auto;
`
const propBgColor = ({ color }) => css`
  background-color: ${color};
`

const StyledDoubleBounce = styled.div`
  ${propSize};
  position: relative;
  ${propMargin};
  > ${Child} {
    ${propBgColor};
  }
`

export default DoubleBounce