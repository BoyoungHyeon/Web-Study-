# Mybatis

## Mybati 개요
![image](https://user-images.githubusercontent.com/49936027/138280478-4af9e0ba-894d-4003-95f6-019b128b9d73.png)

 - SQL 질의문을 통해 결과물을 개발자가 지정한 클래스에 매핑하여 내보내주는 아주 우용한 프레임워크이다. 즉 기존 JDBC 방법은 SQL 질의문 실행 후 결과값을 모델에 담는 로직으로 소스가 길어졌지만, Mybatis는 매핑된 모델에 자동으로 변수와 컬럼의 값이 매핑되어 아주 편해졌다. 
 - 개발자가 지정한 SQL, 저장프로시저 그리고 몇가지 고급 매핑을 지원하는 Persistence 프레임워크이다.
 - JDBC 코드와 수동으로 셋팅하는 파라미터와 결과 매핑을 제거한다. 즉 기존에 직접 jdbc connection, sql statement, parameter, result record set 등을 개발하던 것을 Mybatis 프레임워크가 대신해준다. 
 - 데이터베이스 레코드에 원시타입과 Map 인터페이스 그리고 자바 POJO를 설정하고 매핑하기 위해 XML과 애노테이션을 사용할 수 있다.

 


## Mybatis 3.0의 주요 구성물
<img width="807" alt="스크린샷 2021-10-21 오후 9 48 40" src="https://user-images.githubusercontent.com/49936027/138280589-5dee27f9-187f-4675-b11c-bd52cf382eeb.png">


 
<img width="596" alt="스크린샷 2021-10-21 오후 9 49 09" src="https://user-images.githubusercontent.com/49936027/138280668-03fe749c-6ee7-44e8-958b-91f463c1d13b.png">

 ✔️ MyBatis 사용 목적 중 하나는 DAO로부터 SQL문을 분리하는 것이다. 분리된 SQL문은 SQL mapper 파일에 작성하며 DAO에서는 SqlSession 객체가 SQL mapper 파일을 참조하게 된다.
 ✔️ XML에서 SqlSessionFactory 빌드하기 모든 마이바티스 애플리케이션은 SqlSessionFactory 인스턴스를 사용한다.
   -SqlSessionFactory인스턴스는 SqlSessionFactoryBuilder를 사용하여 만들수 있다.
   - SqlSessionFactoryBuilder는 XML설정파일에서 SqlSessionFactory인스턴스를 빌드할 수 있다.
 
 ✔️ XML설정파일에서 지정하는 마이바티스의 핵심이 되는 설정은
   - 트랜잭션을 제어하기 위한 TransactionManager과 함께
   - 데이터베이스 Connection인스턴스를 가져오기 위한 DataSource 를 포함한다.

