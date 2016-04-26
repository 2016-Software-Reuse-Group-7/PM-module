package TeamSeven.pm.zip;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class ZipTimer
{
	private long PERIOD_DAY = 24 * 60 * 60 * 1000;
	private Timer timer = null;
	private Date zipDate = null;

	public ZipTimer()
	{
		Calendar calendar = Calendar.getInstance();

		calendar.set( Calendar.HOUR_OF_DAY, 20 );
		calendar.set( Calendar.MINUTE, 00 );
		calendar.set( Calendar.SECOND, 00 );

		zipDate = calendar.getTime();
		System.out.println( zipDate );

		if ( zipDate.before( new Date() ) ) {
			this.addDay( 1 ) ;
		}

		Timer timer = new Timer();
	}


	public void setTimerTask( TimerTask task )
	{
		timer.schedule( task, zipDate, PERIOD_DAY );
	}


	public void addDay( int num )
	{
		Calendar startDT = Calendar.getInstance();
		startDT.setTime( zipDate );
		startDT.add( Calendar.DAY_OF_MONTH, num );
		zipDate = startDT.getTime();
	}

	public void setDelay( Date zipDate )
	{
		this.zipDate = zipDate;
	}

	public void setPeriod( long PERIOD_DAY )
	{
		this.PERIOD_DAY = PERIOD_DAY;
	}

	public void disableTimer()
	{
		timer.cancel();
	}
}



	