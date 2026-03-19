#!/usr/bin/env bash

set -euo pipefail

timestamp="$(date -u +%Y%m%dT%H%M%SZ)"
output_dir="artifacts/triage/${timestamp}"

mkdir -p "${output_dir}"

{
  printf 'snapshot_time=%s\n' "${timestamp}"
  printf '\n[docker ps]\n'
  docker ps || true
  printf '\n[docker stats --no-stream]\n'
  docker stats --no-stream || true
} > "${output_dir}/docker.txt"

for service in trade-api:8080 risk-api:8081 execution-worker:8082; do
  name="${service%%:*}"
  port="${service##*:}"
  curl -s "http://localhost:${port}/actuator/health" > "${output_dir}/${name}-health.json" || true
  curl -s "http://localhost:${port}/actuator/metrics/http.server.requests" > "${output_dir}/${name}-http-metrics.json" || true
done

docker logs --tail 200 lab-mysql > "${output_dir}/mysql.log" 2>&1 || true
docker exec lab-mysql mysql -uapp -papp -e "SHOW PROCESSLIST;" > "${output_dir}/mysql-processlist.txt" 2>&1 || true

printf 'Wrote triage bundle to %s\n' "${output_dir}"
