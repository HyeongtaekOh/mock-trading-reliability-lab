# Incident Runbook

## Title

risk-api latency injection causes order latency increase

## Symptoms

- `POST /orders` p95 increases
- Error rate increases when retries amplify failure
- Circuit breaker state changes on `trade-api`

## Hypotheses

1. `risk-api` response latency is elevated.
2. Retry is amplifying downstream latency.
3. Thread or DB pool pressure is compounding the symptom.

## Checks

1. Inspect `trade-api` latency and error dashboards.
2. Inspect `risk-api` latency metrics.
3. Check `trade-api` logs for timeout or circuit breaker transitions.
4. Capture `docker stats` for local resource pressure.

## Mitigation

- Clear the injected fault profile.
- Reduce retry pressure or tighten timeout budget if needed.

## Follow-up

- Revisit timeout budget.
- Revisit alert thresholds.
