package TeamSeven.pm;

/**
 * Created by tina on 16/4/12.
 */

import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by tina on 16/4/10.
 */
public class Pm<K, V> {
    private String name;
    private String path = null;

    private Timer timer = null;
    private TimerTask task = null;
    private LinkedHashMap<K, V> accountMessagesNumber = null;
    private long delay= 0, period= 60000;

    public Pm( String name ) {
        this.timer = new Timer(true);
        accountMessagesNumber = new LinkedHashMap<K, V>();

        setTask();
    }

    public Pm( String name, long delay, long period ) {
        this.timer = new Timer(true);
        this.delay = delay;
        this.period = period;
        accountMessagesNumber = new LinkedHashMap<K, V>();

        setTask();
    }

    // 开始生成用例
    public boolean startOutput()
    {
        if( path == null )
        {
            System.out.println( "缺少输出路径" );
            return false;
        }
        timer.schedule(task, delay, period);
        return true;
    }

    // 结束生成用例
    public void endOutput()
    {
        timer.cancel();
    }

    private void setTask()
    {
        this.task = new TimerTask() {
            @Override
            public void run() {
                Date dt = new Date();
                DateFormat df = new SimpleDateFormat( "yyyyMMdd_HH:mm:ss" );
                String time = df.format( dt );
                try {

                    String fileName = path + "/" + name + "_" + time + ".txt";
                    FileWriter writer = new FileWriter( fileName, true );

                    for ( V mc : accountMessagesNumber.values() )
                    {
                        if( mc != null) writer.write( mc.toString() );
                    }
                    writer.close();

                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println( "error" );
                }
            }
        };
    }

    public void systemOut()
    {
        for ( V mc : accountMessagesNumber.values() )
        {
            if( mc != null)
                System.out.println( mc.toString() );
            else
                System.out.println( "null" );
        }
    }

    // 更新某用例的性能指数
    public V getParameters( K key )
    {
        return accountMessagesNumber.get(key);
    }

    // 获得该用例的性能指数
    public boolean setParameters( K key, V values )
    {
        if( accountMessagesNumber.get(key) == null )
        {
            return false;
        }
        accountMessagesNumber.remove( key );
        accountMessagesNumber.put( key, values );
        return true;
    }

    // 添加一个用例
    public boolean addInstance( K key, V value )
    {
        if( accountMessagesNumber.get(key) != null )
        {
            return false;
        }
        accountMessagesNumber.put( key, value );
        return true;
    }

    // 删除一个用例
    public boolean deleteInstance( K key )
    {
        if( accountMessagesNumber.get(key) == null )
        {
            return false;
        }
        accountMessagesNumber.remove( key );
        return true;
    }

    // 设置参数 delay
    public void setDelay( long delay ) {
        this.delay = delay;
    }

    // 设置参数 period
    public void setPeriod( long period ) {
        this.period = period;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int count()
    {
        return accountMessagesNumber.size();
    }

}
