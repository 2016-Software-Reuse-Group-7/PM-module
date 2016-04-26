package TeamSeven.pm.recordLog;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by tina on 16/4/26.
 */
public class RecordLog
{
    private String recordFilePath = "logFiles/records";
    private String recordInitTime = null;

    public RecordLog()
    {
        newRecordFile();
    }

    public String record( String fileName, String content, boolean append ) throws IOException
    {
        String filePath = recordFilePath + "/" + fileName + "/" + recordInitTime + ".txt";
        File file = new File( filePath );
        if( file.exists() || filePath.endsWith( File.separator ) )
        {
            //System.out.println( "" );
        } else if( file.getParentFile() != null && !file.getParentFile().exists() && !file.getParentFile().mkdirs() )
        {
            //System.out.println( "" );
        } else
        {
            FileWriter writer = new FileWriter( filePath, append );
            writer.write( content + "\n" );
            writer.close();
            return filePath;
        }
        return null;

    }

    public void newRecordFile()
    {
        Date dt = new Date();
        recordInitTime = new SimpleDateFormat( "yyyyMMddHHmmss" ).format( dt );
    }

    public void setRecordFilePath( String recordFilePath )
    {
        this.recordFilePath = recordFilePath;
    }
}
