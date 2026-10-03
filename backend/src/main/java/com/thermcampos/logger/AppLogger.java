package com.thermcampos.logger;

import com.thermcampos.config.PropertiesLoadConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppLogger {

  private final Logger logger;
  private final String instance;
  private final String template = "[%s] %s";

  public AppLogger(Class<?> className, PropertiesLoadConfig props) {
    this(className.getName(), props);
  }

  public AppLogger(String className, PropertiesLoadConfig props) {
    this.logger = LoggerFactory.getLogger(className);
    this.instance = props.get("rinha.server.instance", " _one");
  }

  public void info(String msg, Object... args) {
    this.logger.info(fmt(msg), args);
  }

  public void warn(String msg, Object... args) {
    this.logger.warn(fmt(msg), args);
  }

  public void error(String msg, Object... args) {
    this.logger.error(fmt(msg), args);
  }

  private String fmt(String msg) {
    return String.format(template, instance, msg);
  }
}
