package com.thermcampos.fraudscore;

import com.thermcampos.mapper.JsonBaseDto;
import com.thermcampos.mapper.JsonUtil;
import java.math.BigDecimal;
import java.util.Map;

public class FraudScoreResponse implements JsonBaseDto {
  
  private final boolean approved;
  private final BigDecimal fraud_score;

  public FraudScoreResponse(boolean approved, BigDecimal fraud_score) {
    this.approved = approved;
    this.fraud_score = fraud_score;
  }
  
  public boolean isApproved() {
    return approved;
  }

  public BigDecimal getFraud_score() {
    return fraud_score;
  }

  public String toJson() {
    return "{"
        + "\"approved\":" + approved
        + ",\"fraud_score\":" + fraud_score
        + "}";
  }
}
