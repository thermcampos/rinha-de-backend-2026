package com.thermcampos.fraudscore;

import com.thermcampos.mapper.JsonUtil;
import java.math.BigDecimal;
import java.util.Map;

public record FraudScoreCustomer(
  BigDecimal avg_amount,
  Integer tx_count_24h,
  String[] known_merchants) {

  public static FraudScoreCustomer fromMap(Map<String, Object> map) {
    if (map == null) {
      return null;
    }
    return new FraudScoreCustomer(
      JsonUtil.asBigDecimal(map.get("avg_amount")),
      JsonUtil.asInteger(map.get("tx_count_24h")),
      JsonUtil.asStringArray(map.get("known_merchants")));
  }
}
