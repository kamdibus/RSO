import React from 'react';
import { Snackbar as MUISnackbar, IconButton } from '@material-ui/core';
import CloseIcon from '@material-ui/icons/Close';

export class Snackbar extends React.Component {
  state = {
    open: false,
    text: ""
  }
  render() {  
    return (
      <MUISnackbar
          anchorOrigin={{
            vertical: 'bottom',
            horizontal: 'left',
          }}
          open={this.state.open}
          autoHideDuration={6000}
          onClose={this.handleClose}
          ContentProps={{
            'aria-describedby': 'message-id',
          }}
          message={<span id="message-id">{this.state.text}</span>}
          action={[
            <IconButton
              key="close"
              aria-label="Close"
              color="inherit"
              onClick={this.handleClose}
              style={{ padding: '5px' }}
            >
              <CloseIcon color='secondary' />
            </IconButton>,
          ]}
        />
    )
  }
  open = (text) => {
    this.setState({ open: true, text })
  }
  handleClose = () => {
    this.setState({ open: false })
  }
}