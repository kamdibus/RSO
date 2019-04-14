import styled from "styled-components";
import { Colors } from "../constants/Colors";
import { CommonStyles } from "../constants/CommonStyles";

export const PrimaryButton = styled.button`
  border: none;
  outline: none;
  cursor: ${props => props.disabled ? 'auto' : 'pointer'};
  margin: 20px;
  background-color: ${Colors.primaryTool};
  color: ${props => props.disabled ? Colors.fontLightDisabled : Colors.fontLight};
  padding-left: 10px;
  padding-right: 10px;
  padding-top: 5px;
  padding-bottom: 5px;
  border-radius: ${CommonStyles.borderRadius};
`