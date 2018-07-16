package cn.orangepoet.omq.api.consumer;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Target(ElementType.METHOD)
public @interface OmqConsumer {
    /**
     * Omq Subject
     *
     * @return
     */
    String value();

    /**
     * Omq consumer name;
     */
    String consumer();
}
