# spring-rpc
不使用XML配置，全部使用java config进行配置。


* 使用了rmi发布服务
* 使用了JMS发布服务
* 对RMI和JMS进行了性能对比


## 发布服务
很简单，通过下面的语句即可发布服务
设置服务名，服务bean和端口号即可。
`
    @Bean
    public AccountService accountService(){
        return new AccountServiceImpl();
    }

    @Bean
    public RmiServiceExporter rmiServiceExporter(){
        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();

        rmiServiceExporter.setServiceName("AccountService");
        rmiServiceExporter.setService(accountService());
        rmiServiceExporter.setServiceInterface(AccountService.class);
        rmiServiceExporter.setRegistryPort(1199);

        return rmiServiceExporter;
    }
`

## 引用服务
通过XML配置引用服务也很简单，但通过JAVA CONFIG容易出错。
`
    @Bean
    public AccountService accountService(){
        RmiProxyFactoryBean proxy = new RmiProxyFactoryBean();

        proxy.setServiceUrl("rmi://localhost:1199/AccountService");
        proxy.setServiceInterface(AccountService.class);
        proxy.afterPropertiesSet();  // 这句话不能少

        if(proxy.getObject() instanceof AccountService){
            System.out.println("yes");
        }else{
            System.out.println("No");
        }

        return (AccountService) proxy.getObject();
    }
`
引用服务的代码，在另外一个工程上[https://github.com/njkfei/spring-rpc-client](https://github.com/njkfei/spring-rpc-client)。


## 坑如下
### serialVersionUID
serialVersionUID必须设置，否则会导致错误
### afterPropertiesSet
客户端必须配置afterPropertiesSet，否则引用的bean会返回null

## 关于JMS发布服务
类似于RMI，可看代码，略。

## 关于性能
由于JMS需要经过ACTIVEMQ中转，单次调用约为8MS，性能远低于RMI。
RMI是短连接，会有大量TIME_WAIT现像，高峰期达到了近2W
TIME_WAIT统计
`
netstat -anp tcp |grep "TIME_WAIT" | wc -l
18051
`
性能统计
`
JMS RPC elaps time : 827962 repead 100000 avg 8 
RMI RPC elaps time : 25427 repead 100000 avg 0 
`

## 参考
[http://stackoverflow.com/questions/11138207/spring-rmi-remoting-annotation-configuration](http://stackoverflow.com/questions/11138207/spring-rmi-remoting-annotation-configuration)
