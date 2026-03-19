rootProject.name = "mock-trading-reliability-lab"

include(
    "apps:trade-api",
    "apps:risk-api",
    "apps:execution-worker",
    "modules:contracts",
    "modules:test-support",
)
