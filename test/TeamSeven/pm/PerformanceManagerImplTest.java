package TeamSeven.pm;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tina on 16/4/12.
 */
public class PerformanceManagerImplTest
{

    PerformanceManagerImpl<String, Integer> pmManager;

    @Before
    public void setUp() throws Exception
    {
        pmManager = new PerformanceManagerImpl<String, Integer>( "test" );
    }

    @Test
    public void outputPerformanceLog() throws Exception
    {
        pmManager.pmLog.addItem( "test1", 1 );
        pmManager.pmLog.addItem( "test2", 2 );
        pmManager.pmLog.addItem( "test3", 3 );
        pmManager.outputPerformanceLog();

//        while( true )
//        {
//
//        }
    }

    @Test
    public void outputRecordLog() throws Exception
    {
        pmManager.recordLog( "recordTest", "testing...", true);
        pmManager.recordLog( "recordTest", "testing...", true);

        pmManager.setRecordFilePath( "logFiles/dic/records");
        pmManager.recordLog( "recordTest", "testing...", true);

    }

    @Test
    public void zipManagerTest() throws Exception
    {
        pmManager.pmLog.addItem( "test1", 1 );
        pmManager.pmLog.addItem( "test2", 2 );
        pmManager.pmLog.addItem( "test3", 3 );
        pmManager.outputPerformanceLog();
        pmManager.recordLog( "recordTest", "testing...", true);
        pmManager.recordLog( "recordTest", "testing...", true);

        pmManager.doOneZip();

    }

}