package com.thermcampos.fraudscore;

import com.thermcampos.config.AppConfig;
import com.thermcampos.logger.AppLogger;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import java.math.BigDecimal;
import java.util.Map;

public class FraudScoreHandler {
  
  public void postFraudScore(Context ctx) {
    var props = ctx.appData(AppConfig.PROPERTIES);
    var logger = new AppLogger(getClass(), props);
    try {
      // 1. received the request
      var body = ctx.bodyAsClass(FraudScoreRequest.class);
      logger.info("Body received: {}", body);
      
      // 2. vectorizes and normalizes (14 dimensions)
      /* .. code here .. */

      // 3. searches for the 5 nearest neighbors (e.g. Euclidean distance)
      /* .. code here .. */

      // 4. computes the score (threshold 0.6)
      /* .. code here .. */
      BigDecimal threshold = new BigDecimal("0.2");
      
      // 5. response
      FraudScoreResponse response = new FraudScoreResponse(
          new BigDecimal("0.6").compareTo(threshold) < 1,
          threshold);
      ctx.status(HttpStatus.OK).json(response);
    } catch (Exception e) {
      ctx.status(HttpStatus.INTERNAL_SERVER_ERROR).json(Map.of("message", "Failed to process fraud score", "error", e.getMessage()));
    }
  }
}
