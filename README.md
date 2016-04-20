##PM module
###性能管理构件（PM）需求
- 接收应用程序的性能指标（性能名称、性能指数）
- 每分钟自动生成性能报告（对每指标求和）
- 性能报告输出到单独的性能文件，文件名包括性能报告时间

###PM
jar包位置: classes/artifacts/course_teamwork_1_jar    
构件能满足上述需求    

构件内部使用数据类型为PerformanceLogImpl（自定义接口实现）的成员变量pmlog来存储性能指标（性能名称、性能指数）    
但是考虑到性能指数的数据类型可能会复杂，同时为了增强可复用性，所以使用了泛型    

#####构造方法

```java
PerformanceManagerImpl<K, V> pManager = new PerformanceManagerImpl<K, V>( fileName );
PerformanceManagerImpl<K, V> pManager2 = new PerformanceManagerImpl<K, V>( fileName, filePath );
```
String fileName：文件名     
String filePath：输出文件位置    

K为性能名称的数据类型，或者为能区别性能名称的标识的数据类型   
V为性能指数的数据类型   
K、V也可以用户自定义类，类要实现toString方法，自定义输出格式   

比如   
如果接收应用程序的性能名称和性能指数都是String类型   
```java
PerformanceManagerImpl<String, String> pManager = new PerformanceManagerImpl<String, String>( fileName );
```

####pmlog

PerformanceLogImpl中定义了一系列方法用来更新存储的数据    

1.添加一个性能指标
```java
boolean addItem(K key, V value);
```

2.删除一个性能指标
```java
boolean deleteItem(K key);
```

3.获得该性能指标的指数
```java
V getPerformaceValue(K key);
```

4.更新某性能指标的指数
```java
boolean updatePerformanceValue(K key, V val);
```



#####方法

1.设置输出方式：

- singleFile为true：只输出单次   
- singleFile为false：定时输出    

```java
public boolean setOutputType( boolean singleFile );
public boolean setOutputType( boolean singleFile, boolean appendWrite );
public boolean setOutputType( boolean singleFile, long delay, long period );
```

2.设置参数
```java
void setAppendWrite(boolean appendWrite);
void setDelay( long delay );
void setPeriod( long period );
void setName(String name);
void setPath(String path);
```
appendWrite：当输出到已存在的文件时，是否追加到原文件内   
delay：当采用定时输出时，延迟delay毫秒后开始生成文件    
period：当采用定时输出时，每period毫秒生成一个文件   
默认情况下 delay 为0，period 为60000   

3.输出
根据pmlog和设置好的参数生成性能文件
```java
boolean outputPerformanceLog( PerformanceLogImpl<K, V> performancelog ) throws IOException;
```

4.停止输出
如果选择定时输出，可以使用以下方法停止
```java
void endPerformanceOutput();
```
 
###单元测试
测试对象：PerformanceLogImpl.java   
测试代码：PerformanceLogImplTest.java   
测试结果：   

编号 | 测试功能 | 预期结果 | 测试结果 |
--- | --- | --- | --- |
1 | 获得某性能的指数（性能指标存在） | 返回true | 正确 |
2 | 获得某性能的指数（性能指标不存在） | 返回false | 正确 |
3 | 设置某性能的指数（性能指标存在) | 返回true | 正确 |
4 | 设置某性能的指数（性能指标不存在) | 返回false | 正确 |
5 | 添加新性能指数（性能指标不存在） | 返回true | 正确 |
6 | 添加新性能指数（性能指标存在）| 返回false | 正确 |
7 | 删除性能指标（性能指标不存在） | 返回false | 正确 |
8 | 删除性能指标（性能指标存在）| 返回true | 正确 |
9 | 计算共有几个性能指标 | 返回个数 | 正确 |


测试对象：PerformanceManagerImpl.java  
测试代码：PerformanceManagerImplTest.java   
测试结果：   

编号 | 测试功能 | 预期结果 | 测试结果 |
--- | --- | --- | --- |
1 | 设置输出类型 | 返回true | 正确 |
2 | 设置输出单个文件 | 返回true | 正确 |
3 | 设置定时输出 | 返回true | 正确 |
4 | 单个文件输出 | 生成文件并写入 | 正确 |





###MessageCount
在实践项目中，在定时log服务端和客户端所接受／忽略的消息数时，可以调用Pm构件。   
可以用 id 或者用户名来识别用户端，另外构造了 MessageCount 类来记录各详细指标。  

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



