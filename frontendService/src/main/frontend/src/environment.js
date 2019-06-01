export const PAYMENT_URL = process.env.NODE_ENV === 'production' ?
  process.env.REACT_APP_PROD_PAYMENT_URL :
  process.env.REACT_APP_DEV_PAYMENT_URL;