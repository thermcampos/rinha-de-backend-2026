package com.thermcampos;

import com.thermcampos.config.AppConfig;
import com.thermcampos.config.DbConfig;
import com.thermcampos.mapper.CustomJsonMapper;
import com.thermcampos.config.PropertiesLoadConfig;
import com.thermcampos.fraudscore.FraudScoreRoutes;
import com.thermcampos.health.HealthRoutes;
import com.thermcampos.logger.AppLogger;
import io.javalin.Javalin;

public class App {

    public static void main(String[] args) {
        var props = new PropertiesLoadConfig("application.properties");
        var logger = new AppLogger(App.class, props);

        logger.info("Starting app");
        
        var dbConfig = new DbConfig(props);
        var dataSource = dbConfig.create();
        dbConfig.migrateFlyway(dataSource);

        var app = Javalin.create(config -> {
            // Configs
            config.startup.showJavalinBanner = true;
            
            // JSON Mappers
            config.jsonMapper(new CustomJsonMapper());
            
            // Holders
            config.appData(AppConfig.PROPERTIES, props);
            config.appData(AppConfig.DATA_SOURCE, dataSource);

            // Events
            config.events.serverStopping(dataSource::close); 
            
            // Routes
            HealthRoutes.register(config);
            FraudScoreRoutes.register(config);
        });

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.info("Shutting down app");
            app.stop();
        }));

        app.start(props.getInt("rinha.server.port", 8080));
    }
}
