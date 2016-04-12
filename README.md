##PM module
###性能管理构件（PM）需求
- 接收应用程序的性能指标（性能名称、性能指数）
- 每分钟自动生成性能报告（对每指标求和）
- 性能报告输出到单独的性能文件，文件名包括性能报告时间

###Pm
#####构造方法：
> Pm<K, V> pm = new Pm<K, V>( name );
> Pm<K, V> pm = new Pm<K, V>( name, path );
> Pm<K, V> pm = new Pm<K, V>( name, delay, period );

name 指性能名称，最后的文件输出会包含性能名称
path 指性能报告的输出路径
构件使用 Timer 和 TimerTask 来定时生成报告，构造时可以设置生成性能报告的 delay 和 period
默认情况下 delay 为0，period 为60000

性能指标可能还分不同用例，和各种细节指标，比如在实践项目中，对于消息统计还得分不同用户，消息统计还得分接收的消息和忽略的消息。
K为能区别用例的变量的数据类型
V为用户定义类，类中包含各种细节指标，一定要实现toString方法，自定义输出格式，也可以使用简单数据类型

#####方法：
设置 name、path、delay 、 period 参数
> Pm.setName( String name );
> Pm.setPath( String path );
> Pm.setDelay( long delay );
> Pm.setPeriod( long period );

开始生成报告
> Pm.startOutput();

结束生成报告
> Pm.endOutput();

添加一个用例
> Pm.addInstance( K instanceKey );

删除一个用例
> Pm.deleteInstance( K instanceKey );

获得该用例的性能指数
> Pm.getParameters( K instanceKey );

更新某用例的性能指数
> Pm.setParameters( K instanceKey, V instanceValues );

###单元测试
PmTest.java
| 测试功能              |    测试结果   |
| :------------------          | :----------- |
| 获得某用例的性能指数（用例存在）  | 通过 |
| 获得某用例的性能指数（用例不存在） | 通过  |
| 设置某用例的性能指数（用例存在） | 通过  |
| 设置某用例的性能指数（用例不存在）| 通过  |
| 设置某用例的性能指数（用例存在）| 通过  |
| 添加新用例（用例不存在）| 通过  |
| 添加新用例（用例存在）| 通过  |
| 删除用例（用例不存在）| 通过 |
| 删除用例（用例存在）| 通过 |
| 开始输出性能报告（路径缺失情况下）| 通过 |


###MessageCount
基于实践项目，可以用 id 或者用户名来识别用户端，所以 K 可以使用 Int 或是 String ，另外又构造了 MessageCount 类。

```
public class MessageCount
{

    private String userName;
    private int sendMessageCount;
    private int receivedMessageCount;
    private int ignoredMessageCount;

	public MessageCount( String name )
    {
        userName = name;
        sendMessageCount = 0;
        receivedMessageCount = 0;
        ignoredMessageCount = 0;
    }

    public String toString();
    public void addSendMessageCount(int number);
    public void addReceivedMessageCount(int number);
    public void addIgnoredMessageCount(int number);

}

```

