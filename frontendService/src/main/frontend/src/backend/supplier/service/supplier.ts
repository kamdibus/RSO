import { delay } from "../../common/utils/delay";
import { UploadResponse } from "../model/Upload";
import { OfferResponse, OfferPayload } from "../model/Offer";


export const SupplierService = {
  uploadInvoice: async function(fileName: string, fileData: File): Promise<UploadResponse> {
    await delay(1000);
    return {
      id: 11
    };
  },
  postOffer: async function(offer: OfferPayload): Promise<OfferResponse> {
    console.log(`Posting offer `, offer);
    await delay(1000);
    return {
      id: 12312313
    }
  },
  getOffers: async function(): Promise<OfferPayload[]> {
    await delay(1500);
    return [
      { supplier: "X Company", ratio: 0.98, expirationDate: (new Date()).toISOString(), invoiceId: 1, id: 1 },
      { supplier: "Y Company", ratio: 0.995, expirationDate: (new Date()).toISOString(), invoiceId: 2, id: 420 }
    ]
  }
}