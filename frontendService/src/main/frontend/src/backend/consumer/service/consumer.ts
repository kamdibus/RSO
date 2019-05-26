import { delay } from '../../common/utils/delay';
import { OfferResponse } from '../../supplier/model/Offer'
export const ConsumerService = {
  getOffer: async function(id: number): Promise<OfferResponse> {
    return fetch(`/offers/${id}`)
      .then(r => r.json());
  }
}