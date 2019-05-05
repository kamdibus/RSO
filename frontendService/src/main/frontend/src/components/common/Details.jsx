import React from 'react'
import { TextField as MUITextField } from '@material-ui/core';
import styled from 'styled-components';

export function Details({ fields = [], className }) {

  return (
    <Container className={className}>
      {
        fields.map((field, fieldIndex) => (
          <TextField
            label={field.label || ''}
            defaultValue={field.value || ''}
            key={field.name || fieldIndex}
            margin="normal"
            InputProps={{
              readOnly: true,
            }}
            variant="outlined"
          />    
        ))
      }
    </Container>
  )
}

const Container = styled.div`
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  justify-content: space-between;
`

const TextField = styled(MUITextField)`
  width: 45%;
`