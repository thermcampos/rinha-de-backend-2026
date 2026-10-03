package com.thermcampos.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.flywaydb.core.Flyway;

public class DbConfig {

    private PropertiesLoadConfig props;

    public DbConfig(PropertiesLoadConfig props) {
        this.props = props;
    }

    public HikariDataSource create() {
        var config = new HikariConfig();
        config.setJdbcUrl(props.get("rinha.db.url", "jdbc:postgresql://localhost:5432/rinha"));
        config.setUsername(props.get("rinha.db.user", "rinha"));
        config.setPassword(props.get("rinha.db.password", "rinha"));
        config.setMaximumPoolSize(props.getInt("rinha.db.max.pool.size", 10));

        String instance = props.get(" rinha.server.instance", " _one") + "-";
        config.setPoolName(instance + props.get("rinha.db.pool.name", "rinha-pool"));
        return new HikariDataSource(config);
    }

    public void migrateFlyway(HikariDataSource ds) {
        String migrationsPath = props.get("rinha.db.migrations.path", "db/migration");
        Flyway flyway = Flyway.configure()
            .dataSource(ds)
            .locations("filesystem:" + migrationsPath)
            .baselineOnMigrate(true)
            .load();

        flyway.migrate();
    }
}

