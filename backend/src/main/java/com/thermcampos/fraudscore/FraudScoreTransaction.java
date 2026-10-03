package com.thermcampos.fraudscore;

import com.thermcampos.mapper.JsonUtil;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Map;

public record FraudScoreTransaction(
  BigDecimal amount,
  Integer installments,
  OffsetDateTime requested_at) {

  public static FraudScoreTransaction fromMap(Map<String, Object> map) {
    if (map == null) {
      return null;
    }
    return new FraudScoreTransaction(
      JsonUtil.asBigDecimal(map.get("amount")),
      JsonUtil.asInteger(map.get("installments")),
      JsonUtil.asOffsetDateTime(map.get("requested_at")));
  }
}
