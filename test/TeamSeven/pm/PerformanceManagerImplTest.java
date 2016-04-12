package TeamSeven.pm;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tina on 16/4/12.
 */
public class PerformanceManagerImplTest
{

    PerformanceLogImpl<String, Integer> pmLog;
    PerformanceManagerImpl<String, Integer> pmManager;

    @Before
    public void setUp() throws Exception
    {
        pmLog = new PerformanceLogImpl<String, Integer>( "testItem", 1 );
        pmManager = new PerformanceManagerImpl<String, Integer>( "test", "log" );
    }

    @Test
    public void setOutputType() throws Exception
    {
        assertEquals( true, pmManager.setOutputType( true ) );
    }

    @Test
    public void outputPerformanceLog() throws Exception
    {
        pmManager.setOutputType( true);
        pmManager.outputPerformanceLog( pmLog );
    }

}