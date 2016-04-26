#PM module
##性能管理构件（PM）需求
- 接收应用程序的性能指标（性能名称、性能指数）
- 每分钟自动生成性能报告（对每指标求和）
- 性能报告输出到单独的性能文件，文件名包括性能报告时间

#####新增需求
- 写内容至指定文件
- 每天打包输出的文件

##PM
jar包: cource-teamwork-1.jar    
构件能满足上述需求    

模块内部使用数据类型为PerformanceLogImpl（自定义接口实现）的成员变量pmlog来存储性能指标（性能名称、性能指数）
但是考虑到性能指数的数据类型可能会复杂，同时为了增强可复用性，所以使用了泛型


###构造方法
```java
PerformanceManagerImpl<K, V> pManager = new PerformanceManagerImpl<K, V>( fileName );
```
String fileName：文件名

K为性能名称的数据类型，或者为能区别性能名称的标识的数据类型   
V为性能指数的数据类型   
K、V也可以用户自定义类，类要实现toString方法来自定义输出格式   

比如   
如果接收应用程序的性能名称和性能指数都是String类型   
```java
PerformanceManagerImpl<String, String> pManager = new PerformanceManagerImpl<String, String>( fileName );
```

###输出性能文件   
####pmlog   
PerformanceLogImpl中定义了一系列方法用来更新存储的数据   
1.添加一个性能指标    
```java
pManager.pmlog.addItem( K key, V value );
```

2.删除一个性能指标   
```java
pManager.pmlog.deleteItem( K key );
```

3.获得该性能指标的指数    
```java
pManager.pmlog.getPerformaceValue( K key );
```

4.更新某性能指标的指数
```java
pManager.pmlog.updatePerformanceValue( K key, V val);
```

####方法   
1.设置参数      

```java
 pManager.setLogDelay( delay );
 pManager.setLogPeriod( period );
 pManager.setLogAppendWrite( appendWrite );
 pManager.setLogSingleFile( singleFile );
 pManager.setLogFilePath( logFilePath  );
 pManager.setLogFileName( logFileName );
```
 **logFilePath：** 默认为 "logFiles/performanceLog"   
 **logFileName：** 默认为实例化pManager时传入的fileName    
 **singleFile：** 类型为boolean，为true时，文件输出到logFilePath/logFileName.txt;为false时，定时生成文件，文件名为logFilePath/logFileName/报告生成时间.txt，默认为false      
 **appendWrite：** 类型为boolean，为true时，当输出到已存在的文件时，追加到原文件内，默认为true    
 **delay：** 默认为0   
 **period：** 默认为60000     
    
2.输出  
根据pmlog和设置好的参数生成性能文件    

```java
 pManager.outputPerformanceLog();
```

3.停止输出
如果选择定时输出，可以使用以下方法停止    

```java
 pManager.endPerformanceOutput();
```

###直接输出   
文件最终输出 recordFilePath/fileName/文件生成时间.txt     
1.输出   

```java
pManager.recordLog( fileName, content, append )
```
**content：** 写入的内容   
**append：** boolean型，是否为追加写入   

2.设置文件路径   

```java
pManager.setRecordFilePath( recordFilePath )
```
**recordFilePath：** 默认为“logFiles/records”   

###定时打包文件   
1.设置   

```java
pManager.setZipFilePath( zipFilePath );
pManager.setZipTime( time )
pManager.setZipInterval( interval )
```

**zipFilePath：** 默认为“logFiles/zipFiles”    
**time：** Date类型，表示下一次压缩的时间，重置后会按照interval的间隔计算后续的压缩时间，默认为下一个20点   
**interval：** long类型，表示每两次压缩的间隔时间，默认为一天（24 * 60 * 60 * 1000）   

2.停止、重新开始打包   

```java
pManager.disableZip();
pManager.ableZip();
```

##单元测试   

测试代码：PerformanceLogImplTest.java   
测试结果：   

| 测试功能 | 预期结果 | 测试结果 |
| --- | --- | --- |
| 获得某性能的指数（性能指标存在） | 返回true | 正确 |
| 获得某性能的指数（性能指标不存在） | 返回false | 正确 |
| 设置某性能的指数（性能指标存在) | 返回true | 正确 |
| 设置某性能的指数（性能指标不存在) | 返回false | 正确 |
| 添加新性能指数（性能指标不存在） | 返回true | 正确 |
| 添加新性能指数（性能指标存在）| 返回false | 正确 |
| 删除性能指标（性能指标不存在） | 返回false | 正确 |
| 删除性能指标（性能指标存在）| 返回true | 正确 |
| 计算共有几个性能指标 | 返回个数 | 正确 |


测试代码：zipManagerTest.java
测试结果：

| 测试功能  | 预期结果　|   测试结果   |
| :---|:---| :---: |
| 添加打包文件|添加成功| 通过 |
| 更改打包路径 |路径更新| 通过 |


测试代码：recordLogTest.java
测试结果：

| 测试功能  | 预期结果　|   测试结果   |
| :---|:---| :---: |
| 写入文件|写入成功且路径正确| 通过 |


测试代码：PerformanceManagerImplTest.java
测试结果：

| 测试功能  | 预期结果　|   测试结果   |
| :---|:---| :---: |
| 性能文件输出 | 输出正确 | 通过 |
| 直接输出 | 输出正确 | 通过 |
| 定时打包 |打包成功且路径正确| 通过 |





