import styled from "styled-components";
import { CommonStyles } from "../constants/CommonStyles";

export const ActionTile = styled.button`
  width: 92px;
  height: 92px;
  background-color: #282c34;
  border: 0;
  box-shadow: inset 0 0 40px rgba(255, 255, 255, 0.1);
  outline: none;
  cursor: pointer;
  display: flex;
  justify-content: center;
  align-items: center;
  border-radius: ${CommonStyles.borderRadius};
  &:hover {
    background-color: #181c14;
  }
  &:active {
    background-color: #080c04;  
  }
`