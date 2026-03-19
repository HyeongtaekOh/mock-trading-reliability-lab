import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
  scenarios: {
    burst_orders: {
      executor: 'ramping-arrival-rate',
      startRate: 1,
      timeUnit: '1s',
      preAllocatedVUs: 20,
      maxVUs: 100,
      stages: [
        { target: 5, duration: '15s' },
        { target: 25, duration: '15s' },
        { target: 50, duration: '30s' },
        { target: 0, duration: '10s' },
      ],
    },
  },
};

const BASE_URL = __ENV.BASE_URL || 'http://localhost:8080';

export default function () {
  const response = http.post(
    `${BASE_URL}/orders`,
    JSON.stringify({
      userId: 101,
      symbol: 'AAPL',
      side: 'BUY',
      quantity: 2,
      price: 182.0,
    }),
    {
      headers: {
        'Content-Type': 'application/json',
      },
    },
  );

  check(response, {
    'burst request responded': (r) => r.status >= 200 && r.status < 600,
  });

  sleep(0.2);
}
