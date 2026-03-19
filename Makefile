infra-up:
	docker compose -f infra/compose/docker-compose.yml up -d

infra-down:
	docker compose -f infra/compose/docker-compose.yml down

infra-reset:
	docker compose -f infra/compose/docker-compose.yml down -v

load-smoke:
	k6 run load/k6/smoke.js

load-normal:
	k6 run load/k6/normal.js

load-burst:
	k6 run load/k6/burst.js

fault-risk-latency:
	curl -X POST http://localhost:8081/admin/faults/profile \
	  -H "Content-Type: application/json" \
	  -d '{"fixedLatencyMs":500,"errorRatePercent":0,"cpuBurnMs":0}'

fault-risk-reset:
	curl -X DELETE http://localhost:8081/admin/faults/profile

triage-local:
	bash scripts/triage/collect_local_snapshot.sh
