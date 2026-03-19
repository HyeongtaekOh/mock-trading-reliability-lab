# Architecture Overview

```mermaid
flowchart LR
    Client --> TradeAPI[trade-api]
    TradeAPI --> RiskAPI[risk-api]
    TradeAPI --> MySQL[(MySQL)]
    TradeAPI --> Kafka[(Kafka)]
    Kafka --> Worker[execution-worker]
    Worker --> MySQL
    Worker --> Redis[(Redis)]

    TradeAPI --> Prometheus[Prometheus]
    RiskAPI --> Prometheus
    Worker --> Prometheus
    Prometheus --> Grafana[Grafana]

    TradeAPI --> Loki[Loki]
    RiskAPI --> Loki
    Worker --> Loki
```

- `trade-api` handles order creation, order lookup, and portfolio reads.
- `risk-api` provides synchronous risk checks and fault injection entry points.
- `execution-worker` consumes accepted-order events and updates execution state.
