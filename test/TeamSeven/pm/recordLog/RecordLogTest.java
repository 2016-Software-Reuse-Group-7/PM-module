package TeamSeven.pm.recordLog;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tina on 16/4/26.
 */
public class RecordLogTest {

    @Test
    public void record() throws Exception {
        RecordLog recordLog = new RecordLog();
        recordLog.record( "test", "testing...", true );
    }
}