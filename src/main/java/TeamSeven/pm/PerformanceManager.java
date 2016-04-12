package TeamSeven.pm;

import java.io.IOException;
import java.util.LinkedHashMap;

/**
 * Created by joshoy on 16/4/11.
 */
public interface PerformanceManager<K, V> {


    boolean setOutputType( boolean singleFile );

    boolean outputPerformanceLog( PerformanceLogImpl<K, V> performancelog ) throws IOException;

    void endPerformanceOutput();

    void setDelay( long delay );

    void setPeriod( long period );

    String getName();

    void setName(String name);

    String getPath();

    void setPath(String path);

    void setAppendWrite(boolean appendWrite);

}

