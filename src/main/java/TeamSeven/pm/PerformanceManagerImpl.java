package TeamSeven.pm;

import TeamSeven.pm.perfromanceLog.PerformanceLogImpl;
import TeamSeven.pm.recordLog.RecordLog;
import TeamSeven.pm.zip.ZipManager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by joshoy on 16/4/11.
 */
public class PerformanceManagerImpl<K, V> implements PerformanceManager<K, V>
{
    private ZipManager zipManager;
    private RecordLog recordLog;
    public PerformanceLogImpl<K, V> pmLog;

    public PerformanceManagerImpl( String fileName )
    {
        this.zipManager = new ZipManager( fileName );
        this.recordLog = new RecordLog();
        this.pmLog = new PerformanceLogImpl<K, V>( fileName );
    }


    // Performance Log
    // 设置定时输出并开始
    private Timer timer = new Timer(true);
    private TimerTask task = null;
    public boolean outputPerformanceLog() throws IOException
    {
        if ( pmLog.logFilePath != null )
        {
            File file = new File( pmLog.logFilePath );

            if( !file.exists() || !file.isDirectory() ) {
                if(!file.mkdirs()) {
                    System.out.println("创建目标文件所在目录失败！");
                    return false;
                }
            }
            if ( pmLog.singleFile )
            {
                String filePath = pmLog.logFilePath + "/" + pmLog.logFileName + ".txt";
                output( filePath, pmLog.getPerformanceMap() );

            } else
            {
                file = new File( pmLog.logFilePath + "/" + pmLog.logFileName );
                if( !file.exists() || !file.isDirectory() ) {
                    if(!file.mkdirs()) {
                        System.out.println("创建目标文件所在目录失败！");
                        return false;
                    }
                }
                setTimerTask( pmLog.getPerformanceMap() );
                timer.schedule( task, pmLog.delay, pmLog.period );
            }
            return true;
        }
        return false;
    }
    private void setTimerTask( final LinkedHashMap<K, V> performanceMap )
    {
        this.task = new TimerTask() {
            @Override
            public void run() {
                Date dt = new Date();
                DateFormat df = new SimpleDateFormat( "yyyyMMddHHmmss" );
                String time = df.format( dt );
                try {
                    String filePath = pmLog.logFilePath + "/" + pmLog.logFileName + "/" + time + ".txt";
                    performanceLog( filePath, performanceMap );

                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println( "error" );
                }
            }
        };
    }
    private boolean performanceLog( String filePath, LinkedHashMap<K, V> performanceMap ) throws IOException
    {
        String result = output( filePath, performanceMap );
        if ( result == null )
        {
            return false;
        } else
        {
            zipManager.addFileList( result );
            return true;
        }

    }
    private String output( String filePath, LinkedHashMap<K, V> performanceMap ) throws IOException
    {
        File file = new File( filePath );
        if( file.exists() || file.createNewFile() )
        {
            FileWriter writer = new FileWriter( filePath, pmLog.appendWrite );

            for ( Map.Entry<K, V> entry : performanceMap.entrySet() )
            {
                writer.write( entry.getKey().toString() + ":  " + entry.getValue().toString() );
            }
            writer.close();
            return filePath;

        } else
        {
            System.out.println( filePath + ": 记录失败" );
            return null;
        }
    }
    // 停止定时输出
    public void endPerformanceOutput()
    {
        task.cancel();
    }

    // 设置performanceLog
    public void setLogDelay( long delay ) {
        this.pmLog.delay = delay;
    }
    public void setLogPeriod( long period ) {
        this.pmLog.period = period;
    }
    public void setLogFileName(String logFileName) {
        this.pmLog.logFileName = logFileName;
    }
    public void setLogAppendWrite( boolean appendWrite ) {
        this.pmLog.appendWrite = appendWrite;
    }
    public void setLogSingleFile( boolean singleFile ) {
        if ( singleFile )
        {
            this.timer = null;
        } else
        {
            this.timer = new Timer(true);
        }
        this.pmLog.singleFile = singleFile;
    }
    public void setLogFilePath(String logFilePath) {
        this.pmLog.logFilePath = logFilePath;
    }
    public String getLogFilePath() {
        return pmLog.logFilePath;
    }


    // Record Log
    // 输出传入的content
    public boolean recordLog( String fileName, String content, boolean append ) throws IOException
    {
        String result = recordLog.record( fileName, content, append );
        if ( result == null )
        {
            return false;
        } else
        {
            zipManager.addFileList( result );
            return true;
        }
    }

    //设置recordLog
    public void setRecordFilePath( String recordFilePath )
    {
        this.recordLog.setRecordFilePath( recordFilePath );
    }


    // Zip Manager
    public void disableZip()
    {
        this.zipManager.disableTimer();
    }
    public void ableZip()
    {
        this.zipManager.setTimer();
    }
    public void doOneZip() throws Exception
    {
        this.zipManager.zipFile();
    }
    // 设置zipManager
    public void setZipFilePath( String zipFilePath )
    {
        this.zipManager.setZipFileDes( zipFilePath );
    }
    public void setZipTime( Date time )
    {
        this.zipManager.setTimerDelay( time );
    }
    public void setZipInterval( long interval )
    {
        this.zipManager.setTimerPeriod( interval );
    }


}
