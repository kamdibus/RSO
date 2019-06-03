import { OfferResponse } from '../../supplier/model/Offer'
import Axios from 'axios';
import { OFFERS_URL } from '../../../environment'
import auth0Client from '../../auth/auth0';
export const ConsumerService = {
  getOffer: async function(id: number): Promise<OfferResponse> {
    const headers = auth0Client.getAuthHeaders();
    return Axios.get(OFFERS_URL + `/${id}`, { headers })
      .then(r => r.data);
  }
}