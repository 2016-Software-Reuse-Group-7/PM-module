package TeamSeven.pm.zip;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tina on 16/4/26.
 */
public class ZipManagerTest
{
    ZipManager zipManager = null;
    @Before
    public void setUp() throws Exception
    {
        zipManager = new ZipManager();

    }

    @Test
    public void zipFile() throws Exception
    {
        zipManager.addFileList( "README.md" );
        zipManager.setZipFileDes( "log/a.zip" );
        zipManager.zipFile();
    }


    @Test
    public void FileList() throws Exception
    {
        zipManager.addFileList( "oneFilePath" );
        zipManager.addFileList( "twoFilePath" );
        zipManager.addFileList( "threeFilePath" );
        assertEquals( 3, zipManager.zipFileList.size() );

        zipManager.addFileList( "oneFilePath" );
        assertEquals( 3, zipManager.zipFileList.size() );

        zipManager.clearFileList();
        assertEquals( 0, zipManager.zipFileList.size() );

    }

}