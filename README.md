

> 框架采用nacos作为注册中心搭建  
> 只是简单的项目框架搭建的记录


# SpringCloud结合SpringBoot构建分布式项目搭建


> 1、 搭建父级pom模块时使用dependencyManagement管理依赖包时注意一下几点
>> ① 所有的依赖需要加上版本号  
>> ② build标签中的spring-boot-maven-plugin也需要加上版本号,子模块不需要加。如父级模块

```xml   
  
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <version>2.5.4</version>
            </plugin>
        </plugins>
    </build>  
  
```      

>> ③ 子模块引入添加具体需要的依赖



## 1. 添加父级模块pom依赖

```xml    
     
   <properties>
        <java.version>11</java.version>
        <springBoot.varsion>2.5.4</springBoot.varsion>
        <spring-cloud.version>3.0.4</spring-cloud.version>
        <lombok.version>1.18.20</lombok.version>

        <reactive-streams.version>1.0.3</reactive-streams.version>
        <reactor-bom.version>2020.0.10</reactor-bom.version>
        <rest-assured.version>4.3.3</rest-assured.version>
        <rsocket.version>1.1.1</rsocket.version>
        <rxjava.version>1.3.8</rxjava.version>
        <rxjava-adapter.version>1.2.1</rxjava-adapter.version>
        <rxjava2.version>2.2.21</rxjava2.version>
        <saaj-impl.version>1.5.3</saaj-impl.version>

        <nacos.version>2.2.6.RELEASE</nacos.version>
        <nacos-client.version>2.0.3</nacos-client.version>
        <springcloud.version>3.0.4</springcloud.version>
    </properties>

  
     <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-web</artifactId>
                <version>${springBoot.varsion}</version>
            </dependency>
            
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-starter-gateway</artifactId>
                <exclusions>
                    <exclusion>
                        <groupId>org.springframework</groupId>
                        <artifactId>spring-webmvc</artifactId>
                    </exclusion>
                </exclusions>
                <version>${spring-cloud.version}</version>
            </dependency>

            <dependency>
                <groupId>org.projectlombok</groupId>
                <artifactId>lombok</artifactId>
                <version>${lombok.version}</version>
                <optional>true</optional>
            </dependency>
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-test</artifactId>
                <version>${springBoot.varsion}</version>
                <scope>test</scope>
            </dependency>

           <!-- SpringCloud需要的依赖包  start -->
			<dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-starter-bootstrap</artifactId>
                <version>${springcloud.version}</version>
            </dependency>

            <dependency>
                <groupId>com.alibaba.cloud</groupId>
                <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
                <version>${nacos.version}</version>
            </dependency>


            <dependency>
                <groupId>com.alibaba.cloud</groupId>
                <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
                <version>${nacos.version}</version>
            </dependency>

            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-starter-loadbalancer</artifactId>
                <version>${spring-cloud.version}</version>
            </dependency>

            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-dependencies</artifactId>
                <version>2020.0.4</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>  
  
			<!-- SpringCloud需要的依赖包  start -->

        </dependencies>
    </dependencyManagement>
    
    SPringCloud模块必须导入 spring-cloud-starter-loadbalancer
```      

## 2. 创建网关gateway模块

```java  
  
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-webflux</artifactId>
            <version>2.5.4</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-bootstrap</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-loadbalancer</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-gateway</artifactId>
            <!--  Spring Cloud Gateway使用WebFlux，和spring boot web包冲突，
               使用时一定记得pom中排除原来老WEB那一套（servlet）相关jar,否则会报错。
            -->
            <exclusions>
                <exclusion>
                    <groupId>org.springframework</groupId>
                    <artifactId>spring-webmvc</artifactId>
                </exclusion>
            </exclusions>
        </dependency>

        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
        </dependency>

        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
        </dependency>


        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
        </dependency>

    </dependencies>
   
  
```     

# 整合spring-cloud-starter-openfeign实现模块之间的调用

### 创建user-server项目

> 1、 添加依赖

```xml  
    
	   <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-starter-openfeign</artifactId>  
				<!-- SpringCloud版本 -->
                <version>3.0.4</version>  
            </dependency>   
  
```  
> 2、 在user-server中编写业务

```java  
  
	@RestController
	public class UserController {
	    @Autowired
	    private UserService userService;
	    /**
	     *   <p>
	     *       feign接口调用测试
	     *   </P>
	     * @param: userId
	     * @author:  zhang.rongjun
	     * @DateTime: 2021/12/3 16:17
	     * */
	    @GetMapping("/server/getUserById/{userId}")
	    public User getUserById(@PathVariable Long userId){
	        return userService.getUserById(userId);
	    }
	}      

	@Service
	public class UserServiceImpl implements UserService {
	    @Override
	    public User getUserById(Long userId) {
	        return User.builder().userId(userId).userName("张三").build();
	    }
	}
 
```  

> 3、 在member模块启动类上使用注解```@EnableFeignClients```    
> 4、 在member模块中创建报feign并创建FeignServer类

```java  
    
	@FeignClient(name="user-service")
	public interface FeignServer {
	    /**
	     *   <p>
	     *       feign接口调用测试
	     *   </P>
	     * @param: userId
	     * @author:  zhang.rongjun
	     * @DateTime: 2021/12/3 16:17
	     * */
	    @GetMapping("/user-service/server/getUserById/{userId}")
	    User getUserById(@PathVariable Long userId);
	}  
  
```  
> @FeignClient(name="user-service")中的name是user-service模块spring.application.name设置的值

### feign的创建准寻在需要调用模块的位置创建即可

## 具体配置和详细注意点可以参考这篇博客
<a href="https://zhuanlan.zhihu.com/p/244835339" target="_blank">spring-cloud-starter-openfeign使用详解</a>  

