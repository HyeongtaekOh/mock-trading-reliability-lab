plugins {
    `java-library`
}

dependencies {
    api(platform("org.testcontainers:testcontainers-bom:1.20.4"))
    api("org.testcontainers:junit-jupiter")
    api("org.testcontainers:mysql")
    api("org.testcontainers:kafka")
    api("org.testcontainers:testcontainers")
    api("org.awaitility:awaitility:4.2.2")
    api("com.fasterxml.jackson.core:jackson-databind:2.18.2")
}
