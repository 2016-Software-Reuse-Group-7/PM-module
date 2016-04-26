package TeamSeven.pm.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.TimerTask;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipManager
{
	private String zipFileDes = "logFiles/zipFiles";
	private String zipFileName = null;

	private ZipTimer zTimer = null;

	public ArrayList<String> zipFileList = null;


	public ZipManager()
	{
		zTimer = new ZipTimer();
		zipFileList = new ArrayList<String>();
	}
	public ZipManager( String fileName )
	{
		this.zipFileName = fileName;
		zTimer = new ZipTimer();
		zipFileList = new ArrayList<String>();
	}

	public boolean zipFile() throws Exception
	{
		Date dt = new Date();
		String time = new SimpleDateFormat( "yyyyMMddHHmmss" ).format( dt );
		String fileWholePath = zipFileDes + "/" + zipFileName + "/" + time + ".zip";
		File file = new File( fileWholePath );
		if( file.exists() || fileWholePath.endsWith( File.separator ) )
		{
			return false;
		} else if( file.getParentFile() != null && !file.getParentFile().exists() && !file.getParentFile().mkdirs() )
		{
			return false;
		} else {
			InputStream input = null;
			ZipOutputStream zipOut = new ZipOutputStream( new FileOutputStream( file ) );

			Iterator iterator = zipFileList.iterator();

			while (iterator.hasNext()) {
				String filename = (String) iterator.next();
				File tarfile = new File(filename);
				ZipEntry zipEntry = new ZipEntry(filename);
				zipOut.putNextEntry(zipEntry);

				input = new FileInputStream(tarfile);

				int temp = 0;
				while ((temp = input.read()) != -1) {
					zipOut.write(temp);
				}
				zipOut.closeEntry();
				input.close();

			}

			zipOut.close();
			return true;
		}
	}

	public void setTimer( Date delay, long period )
	{
		zTimer.setDelay( delay );
		zTimer.setPeriod( period );
		setTimer();
	}

	public void setTimer()
	{
		TimerTask task = new TimerTask()
		{
			@Override
			public void run()
			{
				try {
					zipFile();
				} catch (Exception e) {
					e.printStackTrace();
				}
				clearFileList();
				zTimer.addDay( 1 );
			}
		};

		zTimer.setTimerTask( task );
	}

	public void disableTimer()
	{
		this.zTimer.disableTimer();
	}

	public ArrayList<String> addFileList( String fileName )
	{
		if ( !zipFileList.contains( fileName ) )
		{
			zipFileList.add( fileName );
		}
		return zipFileList;
	}

	public ArrayList<String> clearFileList()
	{
		zipFileList.clear();
		return zipFileList;
	}

	public void setZipFileDes( String zipFileDes )
	{
		this.zipFileDes = zipFileDes;
	}

	public void setTimerDelay( Date date )
	{
		this.zTimer.setDelay( date );
	}

	public void setTimerPeriod( long period )
	{
		this.zTimer.setPeriod( period );
	}

}
