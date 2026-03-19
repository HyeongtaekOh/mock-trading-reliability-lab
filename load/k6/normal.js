import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
  vus: 10,
  duration: '1m',
};

const BASE_URL = __ENV.BASE_URL || 'http://localhost:8080';

export default function () {
  const response = http.post(
    `${BASE_URL}/orders`,
    JSON.stringify({
      userId: 101,
      symbol: 'AAPL',
      side: 'BUY',
      quantity: 1,
      price: 175.25,
    }),
    {
      headers: {
        'Content-Type': 'application/json',
      },
    },
  );

  check(response, {
    'normal load accepted response': (r) => r.status === 200 || r.status === 201,
  });

  sleep(1);
}
