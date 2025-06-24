package com.example.orders;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.net.URI;
import java.sql.Connection;

@SpringBootApplication
public class SpringOrdersDemoApplication {

    public static void main(String[] args) {
        configureDataSourceFromEnv();
        SpringApplication.run(SpringOrdersDemoApplication.class, args);
    }

    /**
     * Parses DATABASE_URL and sets system properties for Spring Boot datasource.
     */
    private static void configureDataSourceFromEnv() {
        String dbUrlEnv = System.getenv("DATABASE_URL");
        if (dbUrlEnv == null || dbUrlEnv.isEmpty()) {
            System.err.println("❌ DATABASE_URL is not set in environment variables.");
            return;
        }

        try {
            URI uri = new URI(dbUrlEnv);

            String username = uri.getUserInfo().split(":")[0];
            String password = uri.getUserInfo().split(":")[1];
            String jdbcUrl = "jdbc:postgresql://" + uri.getHost() + ":" + uri.getPort() + uri.getPath();

            System.setProperty("spring.datasource.url", jdbcUrl);
            System.setProperty("spring.datasource.username", username);
            System.setProperty("spring.datasource.password", password);

            System.out.println("✅ DATABASE_URL parsed and applied:");
            System.out.println("🔗 JDBC URL: " + jdbcUrl);
            System.out.println("👤 Username: " + username);
        } catch (Exception e) {
            System.err.println("❌ Failed to parse DATABASE_URL: " + e.getMessage());
        }
    }

    /**
     * Verifies PostgreSQL connection at startup.
     */
    @Bean
    public CommandLineRunner testPostgresConnection(DataSource dataSource) {
        return args -> {
            try (Connection conn = dataSource.getConnection()) {
                System.out.println("✅ Connected to PostgreSQL: " + conn.getMetaData().getURL());
                System.out.println("📦 Driver Name: " + conn.getMetaData().getDriverName());
                System.out.println("🗃️  Product Name: " + conn.getMetaData().getDatabaseProductName());
            } catch (Exception e) {
                System.err.println("❌ Failed to connect to PostgreSQL: " + e.getMessage());
            }
        };
    }
}
