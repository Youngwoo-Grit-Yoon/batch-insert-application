# Batch Insert Application
## 참고 자료
JPA 참고 링크  
https://spring.io/guides/gs/accessing-data-jpa/

Spring Boot 설정 파일  
https://docs.spring.io/spring-boot/docs/1.0.1.RELEASE/reference/html/boot-features-external-config.html

Spring Boot Singleton Bean  
https://www.baeldung.com/spring-boot-singleton-vs-beans

Spring Boot Bean Scopes  
https://www.baeldung.com/spring-bean-scopes

## 제어의 역전(Inversion of Control)
제어의 역전이란, 간단히 말해서 객체가 본인 자체를 생성하지 않고서 종속성을 정의하는 과정을 의미한다. 해당 객체는 IoC 컨테이너에게 종속성 같은
것들을 구성하는 작업을 위임한다. 보통 객체를 만들 때 생성자를 이용해서 하기와 같이 생성한다.
```text
Address address = new Address("High Street", 1000);
Company company = new Company(address);
```
만약 수십, 수백 개의 클래스가 애플리케이션 내부에 존재할 때, 종종 애플리케이션 전반에 걸쳐 싱글 인스턴스를 공유하거나, 각각의 사용 용도에 따른
분리된 객체가 필요할 수도 있다. 따라서 이러한 상황을 위해 제어의 역전이 등장하였다. 의존성 자체를 구성하는 대신, IoC 컨테이너로부터 의존성을
추출하는 것이다. 따라서 우리가 할 것은 컨테이너에게 적절한 설정 메타데이터를 제공하는 것이다.

먼저 Company 클래스에 @Component 애노테이션을 추가하자.
```text
@Component
public class Company {
    // this body is the same as before
}
```
하기 Config 클래스는 IoC 컨테이너에 bean 메타데이터를 제공한다.
```text
@Configuration
@ComponentScan(basePackageClasses = Company.class)
public class Config {
    @Bean
    public Address getAddress() {
        return new Address("High Street", 1000);
    }
}
```
