export interface OfferPayload {
  expirationDate: string;
  supplier: string;
  ratio: number;
  invoiceId: number;
  id?: number;
}

export interface OfferResponse {
  id: number;
}