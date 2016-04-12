package TeamSeven;

import TeamSeven.entity.MessageCount;
import TeamSeven.pm.PerformanceLogImpl;
import TeamSeven.pm.PerformanceManagerImpl;
import TeamSeven.pm.Pm;

import java.io.IOException;

/**
 * Created by joshoy on 16/4/11.
 */
public class Entry {
    PerformanceLogImpl<String, MessageCount> pLog;
    PerformanceManagerImpl<String, MessageCount> pManager;

    // 服务端开启时
    public void initPm( String fileName, String filePath )
    {
        pLog = new PerformanceLogImpl<String, MessageCount>( "server", new MessageCount( true ));
        pManager = new PerformanceManagerImpl<String, MessageCount>( fileName, filePath );
        pManager.setOutputType( false );

    }

    // 用户通过验证
    public void addClient( String clientName )
    {
        pLog.addItem( clientName, new MessageCount( false ) );
    }

    // 用户断开连接
    public void removeClient( String clientName )
    {
        pLog.deleteItem( clientName );
    }

    // 客户端发送一条消息
    public void addMessage( String clientName )
    {
        MessageCount newMC = pLog.getPerformaceValue( clientName );
        newMC.addSendMessageCount( 1 );
        pLog.updatePerformanceValue( clientName, newMC );

    }

    // 客户端的消息被服务端接收
    public void addReceivedMessage( String clientName )
    {
        MessageCount newMC = pLog.getPerformaceValue( clientName );
        newMC.addReceivedMessageCount( 1 );
        pLog.updatePerformanceValue( clientName, newMC );

        newMC = pLog.getPerformaceValue( "server" );
        newMC.addReceivedMessageCount( 1 );
        pLog.updatePerformanceValue( "server", newMC );
    }

    // 客户端的消息被服务端忽略
    public void addIgnoredMessage( String clientName )
    {
        MessageCount newMC = pLog.getPerformaceValue( clientName );
        newMC.addIgnoredMessageCount( 1 );
        pLog.updatePerformanceValue( clientName, newMC );

        newMC = pLog.getPerformaceValue( "server" );
        newMC.addIgnoredMessageCount( 1 );
        pLog.updatePerformanceValue( "server", newMC );
    }

    // 开始生成文件
    public void startOutput() throws IOException {
        pManager.outputPerformanceLog( pLog );
    }

    // 停止生成文件
    public void endOutput() throws IOException {
        pManager.endPerformanceOutput();
    }

}
