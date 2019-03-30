package example.micronaut.job;


import io.micronaut.scheduling.annotation.Scheduled;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HelloWorldJob {

    private static final Logger LOG = LoggerFactory.getLogger(HelloWorldJob.class);

    @Scheduled(fixedDelay = "10s")
    void executeEveryTenSeconds() {
        LOG.info("simple job every 10 seconds: {}", new SimpleDateFormat("dd/M/yyyy hh:mm:ss").format(new Date()));
    }

    @Scheduled(fixedDelay = "45s", initialDelay = "5s")
    void executeEveryFortyFiveSeconds() {
        LOG.info("simple job every 45 seconds: {}", new SimpleDateFormat("dd/M/yyyy hh:mm:ss").format(new Date()));
    }
}
