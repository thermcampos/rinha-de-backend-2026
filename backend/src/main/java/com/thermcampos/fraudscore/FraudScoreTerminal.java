package com.thermcampos.fraudscore;

import com.thermcampos.mapper.JsonUtil;
import java.math.BigDecimal;
import java.util.Map;

public record FraudScoreTerminal(
  Boolean is_online,
  Boolean card_present,
  BigDecimal km_from_home) {

  public static FraudScoreTerminal fromMap(Map<String, Object> map) {
    if (map == null) {
      return null;
    }
    return new FraudScoreTerminal(
      JsonUtil.asBoolean(map.get("is_online")),
      JsonUtil.asBoolean(map.get("card_present")),
      JsonUtil.asBigDecimal(map.get("km_from_home")));
  }
}
