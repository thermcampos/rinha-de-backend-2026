package com.thermcampos.fraudscore;

import com.thermcampos.config.AppConfig;
import com.thermcampos.logger.AppLogger;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import java.util.HashMap;
import java.util.Map;

public class FraudScoreHandler {
  
  public void postFraudScore(Context ctx) {
    var props = ctx.appData(AppConfig.PROPERTIES);
    var logger = new AppLogger(getClass(), props);
    try {
      // Get the body
      var body = ctx.bodyAsClass(FraudScoreRequest.class);
      logger.info("Body received: {}", body);
      
      // process
      Map<String, String> response = new HashMap<>();
      response.put("message", "fine");
      ctx.status(HttpStatus.OK).json(response);
    } catch (Exception e) {
      ctx.status(HttpStatus.INTERNAL_SERVER_ERROR).json(Map.of("message", "Failed to process fraud score", "error", e.getMessage()));
    }
  }
}
