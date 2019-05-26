import { delay } from "../../common/utils/delay";
import { UploadResponse } from "../model/Upload";
import { OfferResponse, OfferPayload, OfferHistoryResponse, OfferStatus } from "../model/Offer";
import axios from 'axios';

export const SupplierService = {
  uploadInvoice: async function(fileName: string, fileData: File): Promise<UploadResponse> {
    await delay(1000);
    return {
      id: 11
    };
  },
  postOffer: async function(offer: OfferPayload): Promise<OfferResponse> {
    return axios
      .post('/offers/', offer)
      .then(r => r.data)
  },
  getOffers: async function(): Promise<OfferResponse[]> {
    return fetch('/offers/')
      .then(r => r.json())
  },
  getOffersHistory: async function(): Promise<OfferResponse[]> {
    return fetch('/offers/')
      .then(r => r.json())
  }
}