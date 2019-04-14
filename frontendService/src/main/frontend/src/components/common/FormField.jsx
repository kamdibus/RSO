import React, { useState } from 'react';
import styled from 'styled-components';
import { DateTimePicker } from 'material-ui-pickers';
import { TextField as MUITextField, Input as MUIInput, InputLabel } from '@material-ui/core';

export function FormField(props) {
  const { children, label, id, ...propz} = props
  const [idOrRandom] = useState(
    id != null ?
    id :
    '' + Math.random()  
  )
  return (
    <Field>
      <InputLabel shrink={true} htmlFor={idOrRandom} style={{ height: '12px', fontSize: '12px' }}>
        {label || children}
      </InputLabel>
      <Input
        id={idOrRandom}
        {...propz}
      />
    </Field>
  );
}

export function FormTextField({ label = '', value, onChange, onBlur, id }) {
  return (
    <TextField 
      id={id}
      label={label}
      margin="normal"
      variant="standard"
      value={value}
      onChange={onChange}
      onBlur={onBlur}
      style={{ marginLeft: 'auto', marginRight: 'auto' }}
    />
  )
}

export function DateField({ value, onChange, label }) {
  return (
    <DatePicker
      ampm={false}
      disablePast
      value={value}
      onChange={onChange}
      label={label}
      style={{ marginLeft: 'auto', marginRight: 'auto' }}
    />
  )
}

const DatePicker = styled(DateTimePicker)`
  margin-top: 10px;
  margin-bottom: 10px;
  width: 45%;
  margin-left: auto;
  margin-right: auto;
`

const Input = styled(MUIInput)`
  width: 100%;
  border: none;
  /* border: 1px solid #eee; */
  border-radius: 5px;
  outline: none;
  /* padding: 5px; */
  box-sizing: border-box;
`

const Field = styled.div`
  margin-top: 10px;
  margin-bottom: 10px;
  width: 45%;
  margin-left: auto;
  margin-right: auto;
`

const TextField = styled(MUITextField)`
  margin-top: 10px;
  margin-bottom: 10px;
  width: 45%;
  margin-left: auto;
  margin-right: auto;
`