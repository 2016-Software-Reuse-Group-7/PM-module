package TeamSeven;

import TeamSeven.pm.Pm;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tina on 16/4/12.
 */
public class PmTest {

    Pm<Integer, String> pmTool;
    @Before
    public void setUp() throws Exception {
        pmTool = new Pm<Integer, String>( "test" );
        pmTool.setPath( "log");
        pmTool.addInstance( 0, "key: 0" );
        pmTool.addInstance( 1, "key: 1" );
        pmTool.addInstance( 2, "key: 2" );
    }

    @Test
    public void getParameters() throws Exception {
        assertEquals( "key: 0", pmTool.getParameters( 0 ) );

        assertEquals( null, pmTool.getParameters( 5 ) );
    }

    @Test
    public void setParameters() throws Exception {
        pmTool.setParameters( 0, "new_key: 0");
        assertEquals( "new_key: 0", pmTool.getParameters( 0 ) );

        assertEquals( false, pmTool.setParameters( 6, "key: 6" ) );
    }

    @Test
    public void addInstance() throws Exception {
        assertEquals( true, pmTool.addInstance( 3, "key: 3" ) );
        assertEquals( 4, pmTool.count() );

        assertEquals( false, pmTool.addInstance( 0, "new" ) );
    }

    @Test
    public void deleteInstance() throws Exception {
        assertEquals( true, pmTool.deleteInstance( 2 ));
        assertEquals( 2, pmTool.count() );

        assertEquals( false, pmTool.deleteInstance( 6 ) );
    }

    @Test
    public void startOutput() throws Exception {

        pmTool.setPath( null );
        assertEquals( false, pmTool.startOutput() );

        /*
        pmTool.setPath( "log" )
        Timer timer = new Timer(true);
        TimerTask task1 = new TimerTask() {
            @Override
            public void run() {
                Date dt = new Date();
                DateFormat df = new SimpleDateFormat( "yyyyMMdd_HH:mm:ss" );
                String time = df.format( dt );
                pmTool.setParameters( 0, time );
            }
        };
        timer.schedule( task1, 0, 60000);

        BufferedReader sysin = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String in = sysin.readLine();
            if ( in.equals( "exit" ) )
            {
                break;
            } else if ( in.equals( "stop" ) )
            {
                pmTool.endOutput();
            } else if ( in.equals( "restart" ) )
            {
                pmTool.startOutput();
            }
        }
        */

    }
}