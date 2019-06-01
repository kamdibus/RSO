import { delay } from "../../common/utils/delay";
import { UploadResponse } from "../model/Upload";
import { OfferResponse, OfferPayload, OfferHistoryResponse, OfferStatus } from "../model/Offer";
import Axios from 'axios';
import { OFFERS_URL } from "../../../environment";

export const SupplierService = {
  uploadInvoice: async function(fileName: string, fileData: File): Promise<UploadResponse> {
    await delay(1000);
    return {
      id: 11
    };
  },
  postOffer: async function(offer: OfferPayload): Promise<OfferResponse> {
    return Axios
      .post(OFFERS_URL + '/', offer)
      .then(r => r.data)
  },
  getOffers: async function(): Promise<OfferResponse[]> {
    return Axios
      .get(OFFERS_URL + '/')
      .then(r => r.data)
  },
  getOffersHistory: async function(): Promise<OfferResponse[]> {
    return Axios
      .get(OFFERS_URL + '/')
      .then(r => r.data)
  }
}