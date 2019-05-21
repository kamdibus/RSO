export interface OfferPayload {
  expirationDate: string;
  supplier: string;
  ratio: number;
  invoiceId: number;
  id?: number;
}

export interface OfferListResponse {
  expirationDate: string;
  supplier: string;
  ratio: number;
  invoiceId: number;
  id: number;
  priceGross: number;
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
  ACCEPTED = 'ACCEPTED',
  CREATED = 'CREATED',
  REJECTED = 'REJECTED'
}