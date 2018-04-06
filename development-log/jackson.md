# Jackson配置属性 
在Jackson中存在一些属性配置，这些配置决定了最后在解析或者编码后数据视图 

## 常用注解  
### @JsonIgnore 
排除属性。@JsonIgnore，一般标记在属性或方法上；作用于序列化与反序列化；     

类似功能的还有：@JsonIgnoreProperties。类注解，指定序列化时忽略这些属性，可以用于覆盖超类中默认输出的属性。`@JsonIgnoreProperties({"id", "created", "steps", "copy", "stepList"})` 

### @JsonProperty
属性别名。序列化/反序列化都有效；  

### @JsonFormat 
`@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")` 
指定Date类字段序列化时的格式   

### @JsonInclude(Include.NON_NULL)  
将该标记放在属性上，如果该属性为NULL则不参与序列化     
Include.NON_EMPTY 属性为 空（“”） 或者为 NULL 都不序列化 

### @JsonDeserialize @JsonSerialize  
@JsonDeserialize：此注解用于属性或者setter方法上，用于在反序列化时可以嵌入我们自定义的代码
@JsonSerialize：此注解用于属性或者getter方法上，用于在序列化时嵌入我们自定义的代码

### @JsonUnwrapped   
当实体类中成员属性是一个类的对象时候，忽略包装  

示例：
```java
public class User {  
    private String name;  
    private String password;  
    @JsonIgnore  
    private List<String> strs ;  
    @JsonUnwrapped  
    private Person p;  
       //setter,getter省略  
}  
```
```java
public class Person {    
    private String pName;    
    private int age;    
    private boolean sex;   
    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")  
    private Date birthday;    
    private String word;    
    private double salary;    
    //setter,getter省略  
}  
```

```java
@Test
public void testJsonUnWrapped() throws IOException {  
    User u = new User("enheng","123");  
    List<String> ls = new ArrayList<String>();  
    ls.add("123");  
    ls.add("234");  
    ls.add("345");  
    u.setStrs(ls);  
    Person person = new Person("nomouse", 25, true, new Date(), "程序员",  
            2500.0);  
    u.setP(person);  
    ObjectMapper mapper = new ObjectMapper();  
    String json = mapper.writeValueAsString(u);  
    System.out.println(json);  
      
    User user = mapper.readValue(json, User.class);
    System.out.println(user);  
}  
```
1. User类中Person属性不加@JsonUnwarpped：
转化后的json为:{"name":"enheng","password":"123","p":{"age":25,"sex":true,"birthday":"2014-12-22   07-15-29","word":"程序员","pname":"nomouse","salary":"2500.00"}}  
2. 加了@JsonUnwarpped注解：{"name":"guofeipeng","password":"123","age":25,"sex":true,"birthday":"2014-12-22 07-16-38","word":"程序         员","pname":"nomouse","salary":"2500.00"}



## 常用配置  
### DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES
反序列化时,遇到未知属性(那些没有对应的属性来映射的属性,并且没有任何setter或handler来处理这样的属性)时是否引起结果失败(通过抛JsonMappingException异常).此项设置只对那些已经尝试过所有的处理方法之后并且属性还是未处理(这里未处理的意思是:最终还是没有一个对应的类属性与此属性进行映射)的未知属性才有影响.
此功能默认是启用的(意味着,如果遇到未知属性时会抛一个JsonMappingException)


举个例子:
比如一段json字符串是:{"id":null,"username":"你好","passwordXXXX":"123456"},现在想反序列化为一个User对象,User类如下:
```java
public class User {  
    private Long id;  
    private String username;  
    private String password;  
    //setter和getter方法略  
}  
```
根据上面的字符串,有一个属性passwordXXXX,但User类的属性没有对应的passwordXXXX属性,如果使用这个字符串反序列化为User对象就会抛JsonMappingException异常.但是如果将DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES设为false,那么就会忽略passwordXXXX这个属性的解析.   


