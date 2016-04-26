package TeamSeven.pm.perfromanceLog;


import java.util.LinkedHashMap;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by joshoy on 16/4/11.
 */
public interface PerformanceLog<K, V> {

    /**
     * @param key: 添加的性能指标名称
     */
    boolean addItem(K key, V value);
    boolean deleteItem(K key);

    /**
     * @param val: 更新对应指标名称的当前性能记录
     */
    boolean updatePerformanceValue(K key, V val);

    /**
     * @param key: 获取记录的性能指标数值
     * @return
     */
    V getPerformaceValue(K key);
}
