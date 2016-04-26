package TeamSeven.pm;

import TeamSeven.pm.perfromanceLog.PerformanceLogImpl;

import java.io.IOException;

/**
 * Created by joshoy on 16/4/11.
 */
public interface PerformanceManager<K, V>
{
    boolean outputPerformanceLog() throws IOException;
    void endPerformanceOutput();

    boolean recordLog( String fileName, String content, boolean append ) throws IOException;


}

