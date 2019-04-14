export interface HttpResponse {
  status: number;
  ok: boolean;
}

export const HttpResponses = {
  ok(): HttpResponse {
    return {
      ok: true,
      status: 200
    }
  }
}