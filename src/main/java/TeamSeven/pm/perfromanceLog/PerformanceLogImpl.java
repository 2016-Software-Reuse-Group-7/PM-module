package TeamSeven.pm.perfromanceLog;

import java.util.LinkedHashMap;

/**
 * Created by joshoy on 16/4/11.
 */
public class PerformanceLogImpl<K, V> implements PerformanceLog<K, V> {

    private LinkedHashMap<K, V> performanceMap = null;

    public String logFileName;
    public String logFilePath = "logfiles/performanceLog";
    public boolean singleFile = false;   // true: 输出到一个文件 文件名: logFilePath/logFileName.txt; false: 定时生成文件 文件: logFilePath/logFileName/报告生成时间.txt
    public boolean appendWrite = true;
    public long delay= 0, period= 60000;


    public PerformanceLogImpl( String fileName )
    {
        performanceMap = new LinkedHashMap<K, V>();
        this.logFileName = fileName;
    }
    public PerformanceLogImpl( K key, V value )
    {
        performanceMap = new LinkedHashMap<K, V>();
        performanceMap.put( key, value );
    }

    // 获得某用例的性能指数
    public V getPerformaceValue( K key )
    {
        return performanceMap.get(key);
    }

    // 更新某用例的性能指数
    public boolean updatePerformanceValue( K key, V values )
    {
        if( performanceMap.get(key) == null )
        {
            return false;
        }
        performanceMap.remove( key );
        performanceMap.put( key, values );
        return true;
    }

    // 添加一个用例
    public boolean addItem( K key, V value )
    {
        if( performanceMap.get(key) != null )
        {
            return false;
        }
        performanceMap.put( key, value );
        return true;
    }

    // 删除一个用例
    public boolean deleteItem( K key )
    {
        if( performanceMap.get(key) == null )
        {
            return false;
        }
        performanceMap.remove( key );
        return true;
    }

    public int count()
    {
        return performanceMap.size();
    }

    public LinkedHashMap<K, V> getPerformanceMap() {
        return performanceMap;
    }
}
