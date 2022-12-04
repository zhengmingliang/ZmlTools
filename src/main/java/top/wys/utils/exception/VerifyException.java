package top.wys.utils.exception;

import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * 确认异常
 * @since 1.4.1
 */
public class VerifyException  extends RuntimeException {

    public VerifyException() {}


    public VerifyException(@Nullable String message) {
        super(message);
    }

    /**
     * Constructs a {@code VerifyException} with the cause {@code cause} and a message that is {@code
     * null} if {@code cause} is null, and {@code cause.toString()} otherwise.
     *
     * @since 1.4.1
     */
    public VerifyException(@Nullable Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a {@code VerifyException} with the message {@code message} and the cause {@code
     * cause}.
     *
     * @since 1.4.1
     */
    public VerifyException(@Nullable String message, @Nullable Throwable cause) {
        super(message, cause);
    }
}
