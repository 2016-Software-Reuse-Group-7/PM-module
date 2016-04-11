package TeamSeven.pm;

/**
 * Created by joshoy on 16/4/11.
 */
public interface PerformanceLog {
    /**
     * @param performanceIndexName: 添加的性能指标名称
     */
    void addPerformanceItem(String performanceIndexName);

    /**
     * @param val: 更新对应指标名称的当前性能记录
     */
    void updatePerformanceValue(String performanceIndexName, String val);


    /**
     * @param performanceIndexName: 获取记录的性能指标数值
     * @return
     */
    String getPerformaceValue(String performanceIndexName);
}
