package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log {
    private Logger logger;

    private Log(Class<?> clazz) {
        this.logger = LoggerFactory.getLogger(clazz);
    }

    public static Log getLogger(Class<?> clazz) {
        return new Log(clazz);
    }

    public void info(String message) {
        logger.info(message);
    }

    public void debug(String message) {
        logger.debug(message);
    }

    public void error(String message) {
        logger.error(message);
    }

    public void error(String message, Throwable t) {
        logger.error(message, t);
    }
} 