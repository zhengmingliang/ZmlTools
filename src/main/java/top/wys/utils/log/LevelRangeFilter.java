package top.wys.utils.log;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

/**
 * 按日志级别区间过滤（含边界）。
 * <p>
 * 用于替代 Log4j2 中“ThresholdFilter(level=maxLevel+1, onMatch=DENY) +
 * ThresholdFilter(level=minLevel, onMatch=ACCEPT)”的两段式写法。
 * 级别在 [minLevel, maxLevel] 区间内返回 NEUTRAL 交由后续过滤器继续判断，
 * 区间外返回 DENY 拒绝。
 *
 * @author zhengmingliang
 */
public class LevelRangeFilter extends Filter<ILoggingEvent> {

    private Level minLevel;
    private Level maxLevel;

    public void setMinLevel(String minLevel) {
        this.minLevel = Level.toLevel(minLevel);
    }

    public void setMaxLevel(String maxLevel) {
        this.maxLevel = Level.toLevel(maxLevel);
    }

    @Override
    public void start() {
        if (minLevel == null || maxLevel == null) {
            addError("minLevel and maxLevel must be set");
            return;
        }
        if (minLevel.toInt() > maxLevel.toInt()) {
            addError("minLevel must not be greater than maxLevel");
            return;
        }
        super.start();
    }

    @Override
    public FilterReply decide(ILoggingEvent event) {
        if (!isStarted()) {
            return FilterReply.NEUTRAL;
        }
        Level level = event.getLevel();
        if (level.isGreaterOrEqual(minLevel) && level.toInt() <= maxLevel.toInt()) {
            return FilterReply.NEUTRAL;
        }
        return FilterReply.DENY;
    }
}
