export interface OfferPayload {
  discount: number;
  invoiceId: number;
  supplierId: number;
  id?: number;
}

export interface OfferResponse {
  id: number;
  discount: number;
  status: string;
  creationDate: string;
  expirationDate: string;
  invoiceId: number;
  userId: number;
  payments: [{
    id: number;
    status: string;
    offerId: number;
  }]
}

export interface OfferResponse {
  id: number;
}

export interface OfferHistoryResponse {
  expirationDate: string;
  supplier: string;
  ratio: number;
  invoiceId: number;
  id: number;
  status: OfferStatus;
  priceGross: number;
}

export enum OfferStatus {
  ACCEPTED = 'accepted',
  PENDING = 'pending',
  REJECTED = 'rejected'
}

export interface InvoicePayload {
  amount: number;
  otherData: string;
  id?: number;
}