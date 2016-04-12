package TeamSeven.pm;

import java.io.IOException;
import java.util.LinkedHashMap;

/**
 * Created by joshoy on 16/4/11.
 */
public interface PerformanceManager<K, V> {


    boolean setOutputType( boolean singleFile );
    boolean setOutputType( boolean singleFile, boolean appendWrite );
    boolean setOutputType( boolean singleFile, long delay, long period );

    boolean outputPerformanceLog( PerformanceLogImpl<K, V> performancelog ) throws IOException;

    void setTimerTask( final LinkedHashMap<K, V> performanceMap );

    /**
     * @param filePath: 日志文件路径
     * @param performanceMap: 性能指标
     */
    void output( String filePath, LinkedHashMap<K, V> performanceMap ) throws IOException;


    void endPerformanceOutput();

    void setDelay( long delay );

    void setPeriod( long period );

    String getName();

    void setName(String name);

    String getPath();

    void setPath(String path);


}

