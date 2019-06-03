export const OFFERS_URL = process.env.NODE_ENV === 'production' ?
  process.env.REACT_APP_PROD_OFFERS_URL :
  process.env.REACT_APP_DEV_OFFERS_URL;

export const PAYMENTS_URL = process.env.NODE_ENV === 'production' ?
  process.env.REACT_APP_PROD_PAYMENTS_URL :
  process.env.REACT_APP_DEV_PAYMENTS_URL

export const USERS_URL = process.env.NODE_ENV === 'production' ?
    process.env.REACT_APP_PROD_USERS_URL :
    process.env.REACT_APP_DEV_USERS_URL