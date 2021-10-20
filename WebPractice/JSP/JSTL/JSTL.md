# JSTL

## JSTL
- JSTL(JSP Standard Tag Library)은 커스텀 태그 라이브러리 기술을 이용해 일반적으로 필요한 기능들을 표준화한 것으로 크게 코어(CORE), XML, I18N(국제화), 데이터베이스(SQL), 함수(Function) 라이브러리로
나눠진다
- jsp 파일에 JSTL을 적용하여면 jstl.jar와 standard.jar 파일을 해당
 프로젝트의 ‘WEB-INF\lib’ 폴더로 복사한다
 
 
 
 ## JSTL 라이브러리별 uri 및 prefix
![image](https://user-images.githubusercontent.com/49936027/138029655-c9f87c14-634e-47b6-b1b7-55dbd5cdb5d3.png)

- JSTL은 표현언어에서 사용할 수 있는 함수를 제공한다
- fn:replace(string, before, after)
 ► string 내에 있는 before 문자열을 after 문자열로 모두 변경해서 반환하는 함수이다
 (예) ${fn:replace(list.gb_contents, CRLF, ””)}
 
  
 ## JSTL 라이브러리
 ### 코어
 ![image](https://user-images.githubusercontent.com/49936027/138029690-4d4216e3-1619-483c-98f1-0946283dc0cc.png)

[참고] 코어 라이브러리는 표현식, 반복처리, URL 관리 기능 등을 제공하므로 웹 애플리케이션을 개발할 때 유용하게 사용할 수 있다


### 국제화
![image](https://user-images.githubusercontent.com/49936027/138029724-f8dd877d-f7ab-4c0d-a0d7-229172e2b443.png)



 ## 자주 쓰이는 태그들
 ### <c:if> 태그
 해당 조건에 맞으면 태그의 body 내용을 처리한다.  자바의 if문과 유사하지만, else는 지원하지 않는다.
![image](https://user-images.githubusercontent.com/49936027/138029790-10d3d65e-c4ba-42c7-84e8-2640051a737b.png)

[문법] 해당 조건에 맞으면 태그의 body 내용을 처리한다.  자바의 if문과 유사하지만, else는 지원하지 않는다.


### <c:choose>, <c:when>, <c:otherwise> 태그
이들 태그는 함께 사용되며 자바의 switch문과 유사한 기능을 한다  태그 내에는 태그 여러 개가 올 수 있다
 ![image](https://user-images.githubusercontent.com/49936027/138029838-ddd5e749-3a85-4ec3-ad50-cb05536fd209.png)


### <c:forEach> 태그
반복문과 관련된 태그로 forEach태그는 자바 for문과 유사하다. 가장 유용한 JSTL태그중 하나이다.
![image](https://user-images.githubusercontent.com/49936027/138029886-87e4fd47-8e57-4aef-839b-522f926617ba.png)

 
 
