package com.thermcampos.fraudscore;

import io.javalin.config.JavalinConfig;

public class FraudScoreRoutes {
  
  public static void register(JavalinConfig c) {
    c.routes.post("/fraud-score", ctx -> new FraudScoreHandler().postFraudScore(ctx));
  }
}
