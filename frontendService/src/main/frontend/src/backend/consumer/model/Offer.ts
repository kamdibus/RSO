export interface OfferResponse {
  expirationDate: string;
  supplier: string;
  ratio: number;
  invoiceId: number;
  price: Price
}

export interface Price {
  subtotal: number;
  total: number;
  tax: number;
}