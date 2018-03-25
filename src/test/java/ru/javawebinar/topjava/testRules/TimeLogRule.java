package ru.javawebinar.topjava.testRules;

import org.junit.rules.MethodRule;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;
import org.slf4j.Logger;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;

public class TimeLogRule implements MethodRule {

    private static final Logger log = getLogger(TimeLogRule.class);

    private static Map<String, Long> testLogTable = new HashMap<>();

    public Map<String, Long> getTestLogTable() {
        return testLogTable;
    }

    @Override
    public Statement apply(Statement base, FrameworkMethod method, Object target) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                Instant start;
                long duration;
                start = Instant.now();
                base.evaluate();
                duration = ChronoUnit.MILLIS.between(start, Instant.now());
                testLogTable.put(method.getName(), duration);
                log.info("Interval: " + duration + " ms " + "Method name: "+ method.getName());
            }
        };
    }
}
