package lab.trading.trade.cache;

import java.time.Duration;
import java.util.Optional;
import lab.trading.trade.domain.PortfolioView;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisPortfolioCacheRepository implements PortfolioCacheRepository {

    private final RedisTemplate<String, PortfolioView> redisTemplate;

    public RedisPortfolioCacheRepository(RedisTemplate<String, PortfolioView> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Optional<PortfolioView> get(Long userId) {
        return Optional.ofNullable(redisTemplate.opsForValue().get(key(userId)));
    }

    @Override
    public void put(Long userId, PortfolioView view, Duration ttl) {
        redisTemplate.opsForValue().set(key(userId), view, ttl);
    }

    @Override
    public void evict(Long userId) {
        redisTemplate.delete(key(userId));
    }

    private String key(Long userId) {
        return "portfolio:" + userId;
    }
}
