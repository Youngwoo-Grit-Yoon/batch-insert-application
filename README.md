# Batch Insert Application
Contributed to Ju In-Seon
## 참고 자료
JPA 참고 링크  
https://spring.io/guides/gs/accessing-data-jpa/

JPA Batch Insert & Update  
https://www.baeldung.com/jpa-hibernate-batch-insert-update

Spring Boot 설정 파일  
https://docs.spring.io/spring-boot/docs/1.0.1.RELEASE/reference/html/boot-features-external-config.html

Spring Boot Singleton Bean  
https://www.baeldung.com/spring-boot-singleton-vs-beans

Spring Boot Bean Scopes  
https://www.baeldung.com/spring-bean-scopes

## 제어의 역전(Inversion of Control)
제어의 역전이란, 간단히 말해서 객체가 본인 자체를 생성하지 않고서 종속성을 정의하는 과정을 의미한다. 해당 객체는 IoC 컨테이너에게 종속성 같은
것들을 생성하는 작업을 위임한다. 보통 객체를 만들 때 생성자를 이용해서 하기와 같이 생성한다.
```text
Address address = new Address("High Street", 1000);
Company company = new Company(address);
```
만약 수십, 수백 개의 클래스가 애플리케이션 내부에 존재할 때, 종종 애플리케이션 전반에 걸쳐 싱글 인스턴스를 공유하거나, 각각의 사용 용도에 따른
분리된 객체가 필요할 수도 있다. 따라서 이러한 상황을 위해 제어의 역전이 등장하였다. 의존성 자체를 생성하는 대신, IoC 컨테이너로부터 의존성을
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
Config 클래스는 Address 타입의 bean을 생성한다. 또한, Config 클래스는 @ComponentScan 애노테이션도 갖고 있는데, 컨테이너한테 Company
클래스를 포함하는 패키지에서 bean을 찾으라는 것을 지시한다. Spring IoC 컨테이너가 이러한 타입의 객체들을 생성할 때, Spring bean이라고
불리는 모든 객체는 IoC 컨테이너에 의해서 관리된다.

Config 클래스에 bean을 정의하였으므로, 이제 컨테이너를 빌드하기 위해 AnnotationConfigApplicationContext 클래스의 인스턴스가 필요하다.
```text
ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
Company company = context.getBean("company", Company.class);
assertEquals("High Street", company.getAddress().getStreet());
assertEquals(1000, company.getAddress().getNumber());
```

## 애플리케이션 개발 목적
콜센터 고도화 프로젝트를 수행할 때 상담원 내선을 저장하고 있는 소스 데이터베이스를 타겟 데이터베이스로 마이그레이션을 수행해야 할 수도 있다. 이때 기존에 내선 관리를
DB가 아닌 텍스트 파일로 관리하던 사이트는 DB로 내선 정보를 일괄 등록해야 한다. 해당 프로그램은 텍스트 파일로부터 내선 정보를 읽어 들여 DB에 배치 삽입을 수행한다.

## PostgreSQL 테이블 정보
### Sequence
```text
CREATE SEQUENCE seq_vdn START 1;
```
### Table Schema
```text
CREATE TABLE public.tb_bm_mn_vdn (
	vdn_sq_id varchar(20) NOT NULL DEFAULT nextval('seq_vdn'::regclass),
	vdn_no varchar(20) NOT NULL,
	center_id varchar(20) NOT NULL,
	server_id varchar(20) NOT NULL,
	monitoring_yn varchar(1) NULL,
	vdn_type varchar(20) NULL,
	split varchar(20) NULL,
	check_link varchar(4) NULL,
	"comment" varchar(128) NULL,
	"result" varchar(20) NULL,
	regi_user_id varchar(20) NULL,
	regi_dttm timestamp NULL DEFAULT now(),
	updt_user_id varchar(20) NULL,
	updt_dttm timestamp NULL DEFAULT now(),
	modify_flag varchar(1) NULL,
	CONSTRAINT tb_bm_mn_vdn_pk PRIMARY KEY (vdn_sq_id)
);
```