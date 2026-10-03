package com.thermcampos.fraudscore;

import com.thermcampos.mapper.JsonUtil;
import java.util.Map;

public record FraudScoreRequest(
  String id,
  FraudScoreTransaction transaction,
  FraudScoreCustomer customer,
  FraudScoreMerchant merchant,
  FraudScoreTerminal terminal,
  FraudScoreLastTransaction last_transaction) {

  public static FraudScoreRequest fromMap(Map<String, Object> map) {
    if (map == null) {
      return null;
    }
    return new FraudScoreRequest(
      JsonUtil.asString(map.get("id")),
      FraudScoreTransaction.fromMap(JsonUtil.asMap(map.get("transaction"))),
      FraudScoreCustomer.fromMap(JsonUtil.asMap(map.get("customer"))),
      FraudScoreMerchant.fromMap(JsonUtil.asMap(map.get("merchant"))),
      FraudScoreTerminal.fromMap(JsonUtil.asMap(map.get("terminal"))),
      FraudScoreLastTransaction.fromMap(JsonUtil.asMap(map.get("last_transaction"))));
  }

  public static FraudScoreRequest fromJson(String json) {
    return fromMap(JsonUtil.fromJson(json));
  }
}
