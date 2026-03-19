import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
  vus: 1,
  iterations: 5,
};

const BASE_URL = __ENV.BASE_URL || 'http://localhost:8080';

export default function () {
  const response = http.post(
    `${BASE_URL}/orders`,
    JSON.stringify({
      userId: 101,
      symbol: 'AAPL',
      side: 'BUY',
      quantity: 3,
      price: 180.5,
    }),
    {
      headers: {
        'Content-Type': 'application/json',
      },
    },
  );

  check(response, {
    'smoke status is 200 or 201': (r) => r.status === 200 || r.status === 201,
  });

  sleep(1);
}
