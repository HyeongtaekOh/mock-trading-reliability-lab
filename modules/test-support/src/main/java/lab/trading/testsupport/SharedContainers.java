package lab.trading.testsupport;

import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.kafka.ConfluentKafkaContainer;
import org.testcontainers.utility.DockerImageName;

public final class SharedContainers {

    private SharedContainers() {
    }

    public static MySQLContainer<?> mysql() {
        return new MySQLContainer<>("mysql:8.4")
                .withDatabaseName("tradinglab")
                .withUsername("app")
                .withPassword("app");
    }

    public static ConfluentKafkaContainer kafka() {
        return new ConfluentKafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:7.6.1"));
    }

    public static GenericContainer<?> redis() {
        return new GenericContainer<>(DockerImageName.parse("redis:7.2"))
                .withExposedPorts(6379);
    }
}
