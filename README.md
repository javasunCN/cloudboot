
# SpringCloud2 + SpringBoot2

# Eureka集群原理：
```text
1、两两相互注册
2、当服务启动注册时，只有一台注册中心可以看到服务，当该台服务挂掉后，会自动同步服务列表到备份注册中心Eureka服务上

```

# Consul
> Consul是一套开源的分布式服务发现和配置管理系统，使用Go语言开发
> Consul下载地址: https://www.consul.io/download.html
> Consul与Spring Cloud整合地址: https://www.springcloud.cc/spring-cloud-consul.html

```text
1、启动命令：
    1.1 开发模式启动：consul agent -dev
    
2、访问地址：http://127.0.0.1:8500/ui/#/dc1/services

3、开启Consul集群
    24.200.110.90启动consul
    consul agent -server -bootstrap-expect=3 -data-dir=/tmp/consul -node=24.200.110.90 -bind=24.200.110.90 -client=0.0.0.0 -datacenter=shenzhen -ui
    24.200.110.91启动consul
    consul agent -server -bootstrap-expect 3 -data-dir /tmp/consul -node 24.200.110.91 -bind=24.200.110.91 -client=0.0.0.0 -datacenter shenzhen -ui
    24.200.110.93启动consul
    consul agent -server -bootstrap-expect 3 -data-dir /tmp/consul -node 24.200.110.93 -bind=24.200.110.93 -client=0.0.0.0 -datacenter shenzhen -ui
    
    3.1、名词解释:
      server： 以server身份启动。默认是client
      bootstrap-expect：集群要求的最少server数量，当低于这个数量，集群即失效。
      data-dir：data存放的目录，更多信息请参阅consul数据同步机制
      node：节点id，集群中的每个node必须有一个唯一的名称。默认情况下，Consul使用机器的hostname
      bind：监听的ip地址。默认绑定0.0.0.0，可以不指定。表示Consul监听的地址,而且它必须能够被集群中的其他节点访问。Consul默认会监听第一个private IP,但最好还是提供一个。生产设备上的服务器通常有好几个网卡，所以指定一个不会出错
      client: 客户端的ip地址，0.0.0.0是指谁都可以访问（不加这个，下面的ui :8500无法访问）
      ui: 可以访问UI界面
      -config-dir指定配置文件夹，Consul会加载其中的所有文件
      -datacenter 指定数据中心名称，默认是dc1
   3.2 三台机器 join 创建集群
        consul集群:当一个consul agent启动后，它不知道任何其他节点,要学习到集群中的其他节点，agent必须加入一个已经存在的集群(cluster)。
        要加入这样的集群，它只需要知道这个集群中的一个节点即可。它加入后，将会和这个member gossip（交谈）并迅速发现集群中的其他节点。
        一个consul agent可以加入任何类型的其他agent，而不只是那些运行于server mode的agent。
        让consul加入集群：
        24.200.110.91加入10.200.110.90
        91上执行命令：consul join 24.200.110.90
        24.200.110.93加入10.200.110.90
        93上执行命令：consul join 24.200.110.90
        
```
