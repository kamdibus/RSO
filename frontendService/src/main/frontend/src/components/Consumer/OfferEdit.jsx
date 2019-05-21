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
          {/* <Title>Edit offer</Title> */}
        </Header>
        <FormContainer>
          <OfferEditForm onSubmitSuccess={this.onOfferSubmit} />
        </FormContainer>
        <Snackbar ref={this.snackbar} />
      </Container>
    )
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

function OfferEditForm({ onSubmitSuccess }) {
  return (
    <Formik
        initialValues={{
          supplier: '',
          ratio: '',
          expirationDate: new Date(),
          invoiceFilename: '',
          invoiceFileData: null,
          nip: ''
        }}
        onSubmit={(values, { setSubmitting }) => {
          const {
            supplier,
            ratio,
            expirationDate,
            invoiceFilename,
            invoiceFileData,
            nip
          } = values
          SupplierService
            .uploadInvoice(invoiceFilename, invoiceFileData)
            .then(({ id: invoiceId }) => SupplierService
              .postOffer({ invoiceId, expirationDate, ratio, supplier })
              .then(res => {
                setSubmitting(false);
                onSubmitSuccess();
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
              id='supplier'
              label="Supplier name"
              value={values.supplier}
              onChange={handleChange}
              onBlur={handleBlur}
            />
            <FormTextField
              id='ratio'
              label="Ratio"
              value={values.ratio}
              onChange={handleChange}
              onBlur={handleBlur}
            />
            <FormTextField
              id='nip'
              label="NIP"
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