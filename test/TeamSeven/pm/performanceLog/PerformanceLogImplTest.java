package TeamSeven.pm.performanceLog;

import TeamSeven.pm.perfromanceLog.PerformanceLogImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tina on 16/4/12.
 */
public class PerformanceLogImplTest
{

    PerformanceLogImpl<String, Integer> pmTool;
    @Before
    public void setUp() throws Exception
    {
        pmTool = new PerformanceLogImpl<String, Integer>( "test", 0 );
    }

    @Test
    public void getPerformaceValue() throws Exception
    {
        assertEquals( (Integer) 0, pmTool.getPerformaceValue( "test" ) );
        assertEquals( null, pmTool.getPerformaceValue( "test_invalid" ) );
    }

    @Test
    public void updatePerformanceValue() throws Exception
    {
        assertEquals( true, pmTool.updatePerformanceValue( "test", 1) );
        assertEquals( false, pmTool.updatePerformanceValue( "test_invalid", 2));
    }

    @Test
    public void addItem() throws Exception {
        assertEquals( true, pmTool.addItem( "test_new", 0 ) );
        assertEquals( false, pmTool.addItem( "test", 0 ) );
    }

    @Test
    public void deleteItem() throws Exception {
        assertEquals( true, pmTool.deleteItem( "test" ) );
        assertEquals( false, pmTool.deleteItem( "test_invalid" ) );
    }

    @Test
    public void count() throws Exception {
        assertEquals( 1, pmTool.getPerformanceMap().size() );

    }
}