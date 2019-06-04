import React from 'react';
import styled from 'styled-components';
import { Formik } from 'formik';
import { FormField, DateField, FormTextField } from '../common/FormField';
import { SupplierService } from '../../backend/supplier/service/supplier';
import { LoadingOverlay } from '../common/Loading/Overlay';
import { PrimaryButton } from '../common/Button';
import { Snackbar } from '../common/Snackbar';
import { Select, MenuItem, FormHelperText, FormControl } from '@material-ui/core';
import { UserService } from '../../backend/user/service/user';

export class OfferEdit extends React.Component {
  constructor(props) {
    super(props)
    this.snackbar = React.createRef()

    this.state = {
      users: []
    }
  }
  componentDidMount() {
    UserService
      .getUsers()
      .then(users => this.setState({ users }))
  }
  
  render() {
    const { users } = this.state

    return (
      <Container>
        <Header>
        </Header>
        <FormContainer>
          <OfferEditForm
            onSubmitSuccess={this.onOfferSubmit}
            onSubmitError={this.onOfferSubmitError}
            users={users}
          />
        </FormContainer>
        <Snackbar ref={this.snackbar} />
      </Container>
    )
  }
  onOfferSubmitError = () => {
    this.openSnackbar("Offer submitting error")
  }
  onOfferSubmit = () => {
    this.openSnackbar("Offer submitted")
  }
  openSnackbar = text => {
    if (this.snackbar.current) {
      this.snackbar.current.open(text)
    }
  } 
}

function OfferEditForm({ onSubmitSuccess, onSubmitError, users }) {
  return (
    <Formik
        initialValues={{
          supplierId: '',
          discount: '',
          expirationDate: new Date(),
          invoiceData: '',
          amount: ''
        }}
        onSubmit={(values, { setSubmitting }) => {
          const {
            supplierId,
            discount,
            expirationDate,
            invoiceData: otherData,
            amount
          } = values
          SupplierService
            .createInvoice({ amount: +amount, otherData })
            .then(({ id: invoiceId }) => SupplierService
              .postOffer({ invoiceId, supplierId, discount })
              .then(res => {
                setSubmitting(false)
                onSubmitSuccess()
              })
              .catch(res => {
                setSubmitting(false)
                onSubmitError()
              })
            )
        }}
      >
      {props => {
        const {
          values,
          touched,
          errors,
          dirty,
          isSubmitting,
          handleChange,
          handleBlur,
          handleSubmit,
          handleReset,
          setFieldValue,
          isValid
        } = props;
        return (
          <Form onSubmit={handleSubmit}>
            {isSubmitting && <LoadingOverlay />}
            <FormControl
                style={{
                  marginLeft: 'auto',
                  marginRight: 'auto',
                  width: '45%',
                  height: '48px',
                  marginTop: '16px',
                  marginBottom: '8px'
                }}
              >
              <Select
                inputProps={{
                  id: 'supplierId'
                }}
                value={values.supplierId}
                onChange={handleChange}
                name='supplierId'
                displayEmpty
                style={{
                  marginTop: '16px'
                }}
              >
                <MenuItem value='' disabled>User</MenuItem>
                {users.map(user => (
                  <MenuItem key={user.id} value={user.id}>
                    {user.name}
                  </MenuItem>
                ))}
              </Select>
              <FormHelperText>User</FormHelperText>
            </FormControl>
            <FormTextField
              id='discount'
              label="Discount"
              value={values.ratio}
              onChange={handleChange}
              onBlur={handleBlur}
            />
            <FormTextField
              id='invoiceData'
              label="Invoice data"
              value={values.invoiceData}
              onChange={handleChange}
              onBlur={handleBlur}
            />
            <FormTextField
              id='amount'
              label="Amount"
              value={values.amount}
              onChange={handleChange}
              onBlur={handleBlur}
            />
            <DateField
              value={values.expirationDate}
              onChange={date => setFieldValue('expirationDate', date)}
              label="Expiration date"
            />
            <ActionRow>
              <PrimaryButton
                disabled={false}
                type="submit"
              >Submit</PrimaryButton>
              <PrimaryButton
                type="button"
                onClick={handleReset}
                disabled={!dirty || isSubmitting}
              >Reset</PrimaryButton>
            </ActionRow>
          </Form>
        );
      }}</Formik>
  )
}

const Form = styled.form`
  width: 50%;
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  align-items: center;
  flex-wrap: wrap;
  font-size: 12px;
  position: relative;
`

const FormContainer = styled.div`
  display: flex;
  justify-content: center;
`

const Container = styled.div`
  width: 100%;
  height: 100%;
`

const Header = styled.header`
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
`

const Title = styled.span`

`

const ActionRow = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  width: 100%;
`