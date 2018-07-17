package cn.orangepoet.omq.broker.model;

import com.google.common.base.Preconditions;
import lombok.Getter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author chengz
 * @since 2018/7/17
 */
@Getter
public class CustomerIndex {
    private final String customerName;
    private final Map<String, Integer> subjectIndexes = new ConcurrentHashMap<>();

    public CustomerIndex(String customerName) {
        Preconditions.checkNotNull(customerName, "customername is null");
        this.customerName = customerName;
    }

    public Integer getIndex(String subject) {
        return subjectIndexes.getOrDefault(subject, -1);
    }

    public void updateIndex(String subject, Integer index) {
        subjectIndexes.put(subject, index);
    }
}
