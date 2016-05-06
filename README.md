Spring Boot Starter OkHttp3
===========================
在Spring Boot下整合OkHttp3,方便客户端使用.

### 为何要使用spring-boot-starter-httpclient
OkHttp3已经非常简单啦,为何还需要创建一个starter,其主要的目的如下:

* 让使用更简单,如全局proxy,timeout等
* 结合dropwizard metrics,添加对metrics的管理
* 全局控制okhttpclient

### 如何使用

* 在Spring Boot项目的pom.xml中添加以下依赖:

          <dependency>
                 <groupId>org.mvnsearch.spring.boot</groupId>
                 <artifactId>spring-boot-starter-okhttp3</artifactId>
                 <version>1.0.0-SNAPSHOT</version>
          </dependency>

* 接下来在你的代码中直接应用cacheManager，然后就可以啦。
        
            @Autowired
            private OkHttpClient httpClient;

### spring-boot-start-httpclient提供的服务

* okhttp3.OkHttpClient: Http Client

### 典型的例子:

* http client: 
```
           String url = "http://ip.mvnsearch.org";
           Request request = new Request.Builder().url(url).build();
           Response response = client.newCall(request).execute();
           System.out.println(response.body().string());
```

### FAQ

* OkHttpClient线程安全吗? 绝对线程安全, 而且JVM全局共享一个OkHttpClient, 性能更好


### 参考文档

* OkHttp : http://square.github.io/okhttp/