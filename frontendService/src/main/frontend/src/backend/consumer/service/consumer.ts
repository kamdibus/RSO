import { OfferResponse } from '../../supplier/model/Offer'
import Axios from 'axios';
import { OFFERS_URL } from '../../../environment'
export const ConsumerService = {
  getOffer: async function(id: number): Promise<OfferResponse> {
    return Axios.get(OFFERS_URL + `/offers/${id}`)
      .then(r => r.data);
  }
}