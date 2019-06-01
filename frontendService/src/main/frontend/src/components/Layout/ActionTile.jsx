import styled from "styled-components";
import { CommonStyles } from "../constants/CommonStyles";

export const ActionTile = styled.button`
  width: 92px;
  height: 92px;
  background-color: #282c34;
  border: 0;
  box-shadow: 0 3px 5px -1px rgba(0,0,0,.2), 0 6px 10px 0 rgba(0,0,0,.14), 0 1px 18px 0 rgba(0,0,0,.12);
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
    box-shadow: none;
  }
  ${({ active }) => active && `
    background-color: #080c04;
    box-shadow: none;
    box-shadow: 0 5px 5px -1px rgba(0,0,0,.2), 0 8px 10px 0 rgba(0,0,0,.14), 0 3px 18px 0 rgba(0,0,0,.12);
  `}
`