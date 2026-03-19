# Incident 0001 - risk-api Latency Injection

## Summary

Initial RCA placeholder for the first local reliability exercise.

## Trigger

Injected `fixedLatencyMs` on `risk-api` through the admin fault profile.

## Expected Evidence

- `trade-api` request latency increase
- Retry/circuit-breaker metrics movement
- Correlated logs across `trade-api` and `risk-api`

## Notes

Fill this document with timeline, rejected hypotheses, evidence, and remediation after the first end-to-end run.
