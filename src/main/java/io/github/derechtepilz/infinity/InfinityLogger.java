package io.github.derechtepilz.infinity;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InfinityLogger {

    private final Logger logger = LoggerFactory.getLogger("Infinity");

    public @NotNull Logger getLogger() {
        return logger;
    }

}
