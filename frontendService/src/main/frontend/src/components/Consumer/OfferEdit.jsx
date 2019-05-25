import React from 'react';
import styled from 'styled-components';
import { Formik } from 'formik';
import { FormField, DateField, FormTextField } from '../common/FormField';
import { SupplierService } from '../../backend/supplier/service/supplier';
import { LoadingOverlay } from '../common/Loading/Overlay';
import { PrimaryButton } from '../common/Button';
import { Snackbar } from '../common/Snackbar';

export class OfferEdit extends React.Component {
  constructor(props) {
    super(props)
    this.snackbar = React.createRef()
  }
  
  render() {
    return (
      <Container>
        <Header>
        </Header>
        <FormContainer>
          <OfferEditForm onSubmitSuccess={this.onOfferSubmit} onSubmitError={this.onOfferSubmitError} />
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

function OfferEditForm({ onSubmitSuccess, onSubmitError }) {
  return (
    <Formik
        initialValues={{
          userId: '',
          discount: '',
          expirationDate: new Date(),
          invoiceFilename: '',
          invoiceFileData: null,
          invoiceId: ''
        }}
        onSubmit={(values, { setSubmitting }) => {
          const {
            userId,
            discount,
            expirationDate,
            invoiceFilename,
            invoiceFileData,
            invoiceId
          } = values
          SupplierService
            .uploadInvoice(invoiceFilename, invoiceFileData)
            .then(({ id: invoiceId }) => SupplierService
              .postOffer({ invoiceId, userId, discount })
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
            <FormTextField
              id='userId'
              label="User id"
              value={values.supplier}
              onChange={handleChange}
              onBlur={handleBlur}
            />
            <FormTextField
              id='discount'
              label="Discount"
              value={values.ratio}
              onChange={handleChange}
              onBlur={handleBlur}
            />
            <FormTextField
              id='invoiceId'
              label="Invoice id"
              value={values.nip}
              onChange={handleChange}
              onBlur={handleBlur}
            />
            <DateField
              value={values.expirationDate}
              onChange={date => setFieldValue('expirationDate', date)}
              label="Expiration date"
            />
            <FormField
              type='file'
              id='invoice'
              label="Invoice [.pdf]"
              onChange={e => {
                  var file = e.target.files[0];
                  var reader = new FileReader();
                  setFieldValue("invoiceFilename", file.name);
                  reader.onload = function(item) {
                      setFieldValue("invoiceFileData",  item.target.result);
                  };

                  reader.readAsDataURL(file);
              }}
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