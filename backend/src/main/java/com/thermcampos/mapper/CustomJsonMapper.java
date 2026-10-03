package com.thermcampos.mapper;

import com.thermcampos.fraudscore.FraudScoreRequest;
import io.javalin.json.JsonMapper;
import java.lang.reflect.Type;
import java.util.Map;

public class CustomJsonMapper implements JsonMapper {
  
  @Override
  public String toJsonString(Object obj, Type type) {
    if (obj instanceof JsonBaseDto dto) {
      return dto.toJson();
    }
    if (obj instanceof Map<?, ?> map) {
      @SuppressWarnings("unchecked")
      var casted = (Map<String, Object>) map;
      return JsonUtil.toJson(casted);
    }
    throw new UnsupportedOperationException("No JSON serialization defined for: " + obj.getClass() + " - implement JsonBaseDto or Map<String, Object>");
  }

  @Override
  @SuppressWarnings("unchecked")
  public <T> T fromJsonString(String json, Type targetType) {
    if (targetType == FraudScoreRequest.class) {
      return (T) FraudScoreRequest.fromJson(json);
    }
    if (targetType == Map.class) {
      return (T) JsonUtil.fromJson(json);
    }
    throw new UnsupportedOperationException("No JSON deserialization defined for: " + targetType + " - add a fromMap factory and register it here");
  }
}

