package TeamSeven.pm;

import com.sun.org.apache.xpath.internal.operations.Bool;

/**
 * Created by joshoy on 16/4/11.
 */
public abstract class PerformanceManagerImpl implements PerformanceManager {

    private static String logPath = null;
    private static Boolean appendWrite = true;

    public void setOutputPath(String path, boolean append) {
        if (this.logPath == null) {
            logPath = path;
        }
        this.appendWrite = new Boolean(append);
    }

    public void outputPerformanceLog(PerformanceLog log) {

    }

}
