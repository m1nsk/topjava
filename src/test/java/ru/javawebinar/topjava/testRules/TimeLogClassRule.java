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

public class TimeLogClassRule implements TestRule {

    private static final Logger log = getLogger(TimeLogClassRule.class);

    Map<String, Long> testLogTable;

    public TimeLogClassRule(Map<String, Long> testLogTable) {
        this.testLogTable = testLogTable;
    }

    @Override
    public Statement apply(Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                base.evaluate();
                testLogTable.entrySet().forEach(entry -> {
                    log.info("name: " + entry.getKey() + " time: " + entry.getValue());
                });
                testLogTable.clear();
            }
        };
    }
}
