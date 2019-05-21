import React from 'react'
import { Details } from '../common/Details';
import styled from 'styled-components';
import { LoadingOverlay } from '../common/Loading/Overlay';
import { Snackbar } from '../common/Snackbar'
import { flattenObject } from '../../utils/object';
import { UserService } from '../../backend/user/service/user';

export class UserDetails extends React.Component {
  constructor(props) {
    super(props)
    this.snackbar = React.createRef()
    this.state = {
      user: null,
      loading: true
    }
  }
  componentDidMount() {
    const { id } = this.props
    UserService
      .getUser(id)
      .then(user => this.setState({ user, loading: false }))
  }

  render() {
    const { user, loading } = this.state
    const { id } = this.props
    
    const fields = Object
      .entries(flattenObject(user || {}))
      .map(([key, value]) => ({
        name: key,
        label: key,
        value
      }))


    return (
      <Container >
        {loading && <LoadingOverlay />}
        <DetailsSection
          fields={fields.concat(offerIdField)}
        />
        <Snackbar ref={this.snackbar} />
      </Container>
    )
  }

  openSnackbar = (text) => {
    if (this.snackbar.current) {
      this.snackbar.current.open(text)
    }
  } 
}

const DetailsSection = styled(Details)`
  width: 80%;
`

const Container = styled.section`
  width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: center;
  position: relative;
  flex-grow: 1;
  padding-top: 24px;
`