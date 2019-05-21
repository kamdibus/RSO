import styled from "styled-components";

const rotate = keyframes`
  from {
    transform: rotate(0deg);
  }

  to {
    transform: rotate(360deg);
  }
`;

export const Rotated = styled.div`
  animation: ${rotate} infinite 20s linear;
  pointer-events: none;
`