import ch.qos.logback.classic.encoder.PatternLayoutEncoder

appender("FILE", FileAppender) {
    file = "testFileLogback.log"
    append = true
    encoder(PatternLayoutEncoder) {
        pattern = "%level %logger - %msg%n "
    }
}

appender("STDOUT",ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        pattern = "CONSOLE :  %level %logger - %msg%n"
    }
}

root(DEBUG, ["FILE"])
root(INFO, ["STDOUT"])