import { OfferResponse } from './../model/Offer';
import { delay } from '../../common/utils/delay';

export const ConsumerService = {
  getOffer: async function(id: number): Promise<OfferResponse> {
    await delay(1000);
    
    return {
      expirationDate: (new Date()).toDateString(),
      supplier: 'X Company',
      ratio: 0.995,
      invoiceId: 1,
      price: {
        subtotal: 100,
        total: 123,
        tax: 23
      }
    }
  }
}