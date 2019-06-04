import { delay } from "../../common/utils/delay";
import { UploadResponse } from "../model/Upload";
import { OfferResponse, OfferPayload, OfferHistoryResponse, OfferStatus, InvoicePayload } from "../model/Offer";
import Axios from 'axios';
import { OFFERS_URL } from "../../../environment";
import auth0Client from "../../auth/auth0";

export const SupplierService = {
  createInvoice: async function(invoice: InvoicePayload): Promise<InvoicePayload> {
    const headers = auth0Client.getAuthHeaders()
    return Axios
      .post('/api/invoices/', invoice, { headers })
      .then(r => r.data)
  },
  acceptOffer: async function(offerId: string) {
    return this.updateOffer(offerId, OfferStatus.ACCEPTED)
  },
  rejectOffer: async function(offerId: string) {
    return this.updateOffer(offerId, OfferStatus.REJECTED)
  },
  updateOffer: async function(offerId: string, status: OfferStatus) {
    const headers = auth0Client.getAuthHeaders();
    return Axios
      .patch(OFFERS_URL + '/' + offerId, { status }, { headers })
      .then(r => r.data)
  },
  postOffer: async function(offer: OfferPayload): Promise<OfferResponse> {
    const headers = auth0Client.getAuthHeaders();
    return Axios
      .post(OFFERS_URL + '/', offer, { headers })
      .then(r => r.data)
  },
  getOffers: async function(): Promise<OfferResponse[]> {
    const headers = auth0Client.getAuthHeaders();
    return Axios
      .get(OFFERS_URL + '/', { headers })
      .then(r => r.data)
  },
  getOffersHistory: async function(): Promise<OfferResponse[]> {
    const headers = auth0Client.getAuthHeaders();
    return Axios
      .get(OFFERS_URL + '/', { headers })
      .then(r => r.data)
  }
}