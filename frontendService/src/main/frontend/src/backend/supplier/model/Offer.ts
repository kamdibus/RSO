export interface OfferPayload {
  expirationDate: string;
  supplier: string;
  ratio: number;
  invoiceId: number;
}

export interface OfferResponse {
  id: number;
}