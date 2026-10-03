package com.thermcampos.fraudscore;

import com.thermcampos.mapper.JsonUtil;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Map;

public record FraudScoreLastTransaction(
  OffsetDateTime timestamp,
  BigDecimal km_from_current) {

  public static FraudScoreLastTransaction fromMap(Map<String, Object> map) {
    if (map == null) {
      return null;
    }
    return new FraudScoreLastTransaction(
      JsonUtil.asOffsetDateTime(map.get("timestamp")),
      JsonUtil.asBigDecimal(map.get("km_from_current")));
  }
}
