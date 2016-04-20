package TeamSeven.pm;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by joshoy on 16/4/11.
 */
public class PerformanceManagerImpl<K, V> implements PerformanceManager<K, V> {

    private String name;
    private String path = null;

    private Timer timer = null;
    private TimerTask task = null;
    private long delay= 0, period= 6000;

    private boolean singleFile = false;   // true: 输出到一个文件 文件名: name; false: 定时生成文件 文件名: name+报告生成时间
    private boolean appendWrite = true;

    public PerformanceLogImpl<K, V> pmlog;

    public PerformanceManagerImpl( String name )
    {
        this.name = name;
        this.pmlog = new PerformanceLogImpl<K, V>();
    }

    public PerformanceManagerImpl( String name, String path )
    {
        this.name = name;
        this.path = path;
        this.pmlog = new PerformanceLogImpl<K, V>();
    }

    public boolean setOutputType( boolean singleFile )
    {
        this.singleFile = singleFile;
        if ( singleFile )
        {
            this.timer = null;
        } else
        {
            this.timer = new Timer(true);
        }
        return true;
    }

    public boolean outputPerformanceLog( PerformanceLogImpl<K, V> performancelog ) throws IOException {
        final LinkedHashMap<K, V> performanceMap = performancelog.getPerformanceMap();
        if ( path != null )
        {

            File file = new File( path );

            if( !file.exists() || !file.isDirectory() ) {
                if(!file.mkdirs()) {
                    System.out.println("创建目标文件所在目录失败！");
                    return false;
                }
            }
            if ( singleFile )
            {
                String filePath = path + "/" + name + ".txt";
                output( filePath, performanceMap );

            } else
            {
                setTimerTask( performanceMap );

                timer.schedule( task, delay, period);
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
                DateFormat df = new SimpleDateFormat( "yyyyMMdd_HH:mm:ss" );
                String time = df.format( dt );
                try {

                    String filePath = path + "/" + name + "_" + time + ".txt";
                    output( filePath, performanceMap );

                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println( "error" );
                }
            }
        };
    }

    private void output( String filePath, LinkedHashMap<K, V> performanceMap ) throws IOException
    {

        FileWriter writer = new FileWriter( filePath, appendWrite );

        for ( Map.Entry<K, V> entry : performanceMap.entrySet() )
        {
            writer.write( entry.getKey().toString() + ":  " + entry.getValue().toString() );
            //System.out.println( entry.getKey().toString() + ":  " + entry.getValue().toString() );
        }
        writer.close();

    }

    public void endPerformanceOutput()
    {
        task.cancel();
    }

    public void setDelay( long delay ) {
        this.delay = delay;
    }

    public void setPeriod( long period ) {
        this.period = period;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAppendWrite(boolean appendWrite) {
        this.appendWrite = appendWrite;
    }

    public void setSingleFile(boolean singleFile) {
        this.singleFile = singleFile;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}
