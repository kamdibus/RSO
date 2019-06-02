import React, { Component } from 'react';
import styled from 'styled-components';
import Router from './Router';
import { MuiPickersUtilsProvider } from 'material-ui-pickers';
import MomentUtils from '@date-io/moment';
import { withRouter } from 'react-router';

class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      checkingSession: true,
    }
  }

  async componentDidMount() {
    if (this.props.location.pathname === '/callback') {
      this.setState({ checkingSession:false });
      return;
    }

    // try {
    //   await auth0Client.silentAuth();
    //   this.forceUpdate();
    // }
    // catch (err) {
    //   console.log(err);
    // }
    this.setState({ checkingSession:false });
  }

  render() {
    return (
      <MuiPickersUtilsProvider utils={MomentUtils}>
        <Container>
          <Body>
            <Router checkingSession={this.state.checkingSession} />
          </Body>
        </Container>
      </MuiPickersUtilsProvider>
    );
  }
}

const Container = styled.div`
  text-align: 'center';
  display: flex;
  flex-direction: column;
  height: 100%;
`;

const Body = styled.div`
  flex: 1 0;
`;

export default withRouter(App);
