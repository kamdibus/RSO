import React from 'react';
import styled from 'styled-components';
import { Formik } from 'formik';
import { FormTextField } from '../common/FormField';
import { LoadingOverlay } from '../common/Loading/Overlay';
import { PrimaryButton } from '../common/Button';
import { Snackbar } from '../common/Snackbar';
import { UserService } from '../../backend/user/service/user';

export class AccountEdit extends React.Component {
  constructor(props) {
    super(props)
    this.snackbar = React.createRef()

    this.state = {
      user: null
    }
  }
  componentDidMount() {
    const { userId } = this.props
    const getUser = userId != null ?
      () => UserService.getUser(userId) :
      () => UserService.getCurrentUser()

    getUser()
      .then(user => {
        this.setState({ user })
      })
  }

  render() {
    const { user } = this.state
    return (
      <Container>
        <Header>
        </Header>
        {!user && <LoadingOverlay />}
        {user && <FormContainer>
          <AccountEditForm onSubmitSuccess={this.onSubmit} initialValues={user} />
        </FormContainer>}
        <Snackbar ref={this.snackbar} />
      </Container>
    )
  }
  onSubmit = () => {
    this.openSnackbar("Account saved")
  }
  openSnackbar = text => {
    if (this.snackbar.current) {
      this.snackbar.current.open(text)
    }
  } 
}

function AccountEditForm({ onSubmitSuccess, initialValues }) {
  return (
    <Formik
        initialValues={initialValues}
        onSubmit={(values, { setSubmitting }) => {
          const {
            name,
            nip
          } = values
          UserService
            .editUser({ name, nip })
            .then(({ ok, status }) => {
              setSubmitting(false);
              onSubmitSuccess();
            })
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
              id='name'
              label="Name"
              value={values.name}
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