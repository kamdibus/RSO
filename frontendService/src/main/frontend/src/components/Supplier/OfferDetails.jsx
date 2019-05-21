import React from 'react'
import { ConsumerService } from '../../backend/consumer/service/consumer';
import { Details } from '../common/Details';
import styled from 'styled-components';
import { LoadingOverlay } from '../common/Loading/Overlay';
import { ActionSection } from '../Consumer/ActionSection';
import { Snackbar } from '../common/Snackbar'

export class OfferDetails extends React.Component {
  constructor(props) {
    super(props)
    this.snackbar = React.createRef()
    this.state = {
      offer: null,
      loading: true
    }
  }
  componentDidMount() {
    const { offerId } = this.props
    ConsumerService
      .getOffer(offerId)
      .then(offer => this.setState({ offer, loading: false }))
  }

  render() {
    const { offer, loading } = this.state
    const { offerId } = this.props
    
    const fields = Object
      .entries(flattenObject(offer || {}))
      .map(([key, value]) => ({
        name: key,
        label: key,
        value
      }))

    const offerIdField = {
      name: 'offerId',
      label: "offer id",
      value: offerId
    }

    return (
      <Container >
        {loading && <LoadingOverlay />}
        <OfferDetailsSection
          fields={fields.concat(offerIdField)}
        />
        <ActionSection onAccept={this.onOfferAccept} onReject={this.onOfferReject} />
        <Snackbar ref={this.snackbar} />
      </Container>
    )
  }

  onOfferAccept = () => {
    this.openSnackbar("Offer accepted")
  }

  onOfferReject = () => {
    this.openSnackbar("Offer rejected")
  }

  openSnackbar = (text) => {
    if (this.snackbar.current) {
      this.snackbar.current.open(text)
    }
  } 
}

function flattenObject(obj) {
  return Object.entries(obj)
    .reduce((r, [key, value]) => {
      if (typeof value === 'object') {
        return { ...r, ...flattenObject(value) }
      }
      return { ...r, [key]: value }
    }, {})
}

const OfferDetailsSection = styled(Details)`
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