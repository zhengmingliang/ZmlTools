package top.wys.utils.log;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

import java.util.regex.Pattern;

/**
 * 与 Log4j2 RegexFilter 语义一致的 logback 过滤器：
 * 消息匹配 regex 时返回 onMatch（默认 ACCEPT），不匹配时返回 onMismatch（默认 DENY）。
 * 用于移植 log4j2.xml 中的过滤规则。
 *
 * @author zhengmingliang
 */
public class RegexFilter extends Filter<ILoggingEvent> {

    private String regex;
    private Pattern pattern;
    private FilterReply onMatch = FilterReply.ACCEPT;
    private FilterReply onMismatch = FilterReply.DENY;

    public void setRegex(String regex) {
        this.regex = regex;
    }

    public void setOnMatch(String onMatch) {
        this.onMatch = FilterReply.valueOf(onMatch);
    }

    public void setOnMismatch(String onMismatch) {
        this.onMismatch = FilterReply.valueOf(onMismatch);
    }

    @Override
    public void start() {
        if (regex == null) {
            addError("regex is required");
            return;
        }
        pattern = Pattern.compile(regex);
        super.start();
    }

    @Override
    public FilterReply decide(ILoggingEvent event) {
        if (!isStarted()) {
            return FilterReply.NEUTRAL;
        }
        String message = event.getFormattedMessage();
        boolean matches = message != null && pattern.matcher(message).matches();
        return matches ? onMatch : onMismatch;
    }
}
