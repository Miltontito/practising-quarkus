package org.milton.number;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import io.quarkus.runtime.configuration.ProfileManager;
import org.jboss.logging.Logger;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;


@ApplicationScoped
class NumberApplicationLifeCycle {
    private static final Logger LOGGER = Logger.getLogger(NumberApplicationLifeCycle.class);
    void onStart(@Observes StartupEvent ev) {
        LOGGER.info(" /$$   /$$ /$$   /$$ /$$      /$$ /$$$$$$$  /$$$$$$$$ /$$$$$$$ ");
        LOGGER.info("| $$$ | $$| $$  | $$| $$$    /$$$| $$__  $$| $$_____/| $$__  $$");
        LOGGER.info("| $$$$| $$| $$  | $$| $$$$  /$$$$| $$  \\ $$| $$      | $$  \\ $$");
        LOGGER.info("| $$ $$ $$| $$  | $$| $$ $$/$$ $$| $$$$$$$ | $$$$$   | $$$$$$$/");
        LOGGER.info("| $$  $$$$| $$  | $$| $$  $$$| $$| $$__  $$| $$__/   | $$__  $$");
        LOGGER.info("| $$\\  $$$| $$  | $$| $$\\  $ | $$| $$  \\ $$| $$      | $$  \\ $$");
        LOGGER.info("| $$ \\  $$|  $$$$$$/| $$ \\/  | $$| $$$$$$$/| $$$$$$$$| $$  | $$");
        LOGGER.info("|__/  \\__/ \\______/ |__/     |__/|_______/ |________/|__/  |__/");
        LOGGER.info("                                             Powered by Quarkus");
        LOGGER.info("The application Number is starting with profile " +
                ProfileManager.getActiveProfile());
    }

    void onStop(@Observes ShutdownEvent ev) {
        LOGGER.info("The application Number is stopping...");
    }

}