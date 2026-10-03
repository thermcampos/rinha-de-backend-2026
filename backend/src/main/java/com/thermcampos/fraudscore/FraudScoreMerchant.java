package com.thermcampos.fraudscore;

import com.thermcampos.mapper.JsonUtil;
import java.math.BigDecimal;
import java.util.Map;

public record FraudScoreMerchant(
  String id,
  String mcc,
  BigDecimal avg_amount) {

  public static FraudScoreMerchant fromMap(Map<String, Object> map) {
    if (map == null) {
      return null;
    }
    return new FraudScoreMerchant(
      JsonUtil.asString(map.get("id")),
      JsonUtil.asString(map.get("mcc")),
      JsonUtil.asBigDecimal(map.get("avg_amount")));
  }
}
