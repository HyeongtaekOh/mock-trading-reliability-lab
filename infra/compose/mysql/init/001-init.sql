CREATE TABLE IF NOT EXISTS orders (
  id VARCHAR(36) PRIMARY KEY,
  user_id BIGINT NOT NULL,
  symbol VARCHAR(16) NOT NULL,
  side VARCHAR(8) NOT NULL,
  quantity INT NOT NULL,
  price DECIMAL(18,2) NOT NULL,
  status VARCHAR(32) NOT NULL,
  rejection_reason VARCHAR(255),
  created_at TIMESTAMP NOT NULL,
  updated_at TIMESTAMP NOT NULL,
  version BIGINT NOT NULL DEFAULT 0,
  INDEX idx_orders_user_id_created_at (user_id, created_at),
  INDEX idx_orders_status_created_at (status, created_at)
);

CREATE TABLE IF NOT EXISTS executions (
  id VARCHAR(36) PRIMARY KEY,
  order_id VARCHAR(36) NOT NULL,
  result VARCHAR(32) NOT NULL,
  fill_price DECIMAL(18,2) NOT NULL,
  processed_at TIMESTAMP NOT NULL,
  UNIQUE KEY uk_executions_order_id (order_id)
);

CREATE TABLE IF NOT EXISTS positions (
  user_id BIGINT NOT NULL,
  symbol VARCHAR(16) NOT NULL,
  quantity INT NOT NULL,
  avg_price DECIMAL(18,2) NOT NULL,
  updated_at TIMESTAMP NOT NULL,
  version BIGINT NOT NULL DEFAULT 0,
  PRIMARY KEY (user_id, symbol)
);
