#표현 언어(EL)

## 표현 언어(EL)
 - 표현 언어(Expression Language)는 처음 JSTL(JSP Standard Tag Library)이 소 개되었을 때 나온 것으로, SPEL(Simplest Possible Expression Language)에 기 본을 두고 있다
 - 따라서 처음에는 JSTL의 부분처럼 사용되었으나 지금은 JSP 스펙에 포함됨으 로써 JSTL과 상관없이 JSP 페이지 내에서 표현식 등을 대체하는 용도로 사용될 수 있다
 - 표현 언어는 자바 빈즈 속성 값을 보다 쉽고 제약을 덜 받는 방법으로 사용하 기 위해 나온 것으로 JSP 파일이 useBean 액션 태그나 표현식 등으로 복잡해 지는 것을 막고 일종의 템플릿 코드처럼 사용할 수 있도록 해준다


## 표현 언어 기본 문법
- 표현 언어는 ‘$’로 시작한다
- 모든 내용은 ‘{표현식}’ 과 같이 구성된다
- 표현식에는 기본적으로 변수명, 혹은 ‘속성명.메소드명’ 구조로 이루어진다
- 표현식에는 부가적으로 숫자, 문자열, boolean, null과 같은 상수 값도 올 수 있다
- 표현식에는 기본적인 연산이 가능하다


## 내장 객체
- 표현 언어에서는 효율적으로 JSP와 상호작용하도록 다음과 같은 내장 객체를 지원한다

|내장 객체|기능|
|------|---|
|pageScope|page 범위에 포함된 속성 값에 접근할 수 있는 객체|
|requestScope|request 범위에 포함된 속성 값에 접근할 수 있는 객체|
|sessionScope|session 범위에 포함된 속성 값에 접근할 수 있는 객체|
|applicationScope|application 범위에 포함된 속성 값에 접근할 수 있는 객체|
|✏ param|request.getParameter(“xxx”)로 얻을 수 있는 값들 ${param.xxx} 처럼 사용 |
|✏ paramValues|request.getParameterValues(“xxx”) 와 동일 기능 수행 ${paramValues.xxx} 처럼 사용|

## 예제

### 예제 1
#### ELJstl_form.jsp
![image](https://user-images.githubusercontent.com/49936027/138054669-7e775cce-a69e-423a-b07d-9d9e2ecf418e.png)

https://github.com/BoyoungHyeon/Web-Study-/blob/main/WebPractice/JSP/EL/source/ELJstl_form.jsp


#### ELJstl_view.jsp
![image](https://user-images.githubusercontent.com/49936027/138054690-42d128cd-928e-48b7-a96e-2994080be6d1.png)

https://github.com/BoyoungHyeon/Web-Study-/blob/main/WebPractice/JSP/EL/source/ELJstl_view.jsp



### 예제 2
#### fileSelect.jsp
![image](https://user-images.githubusercontent.com/49936027/138054961-ae5d51be-98a7-4754-9ace-98346aef4c70.png)

https://github.com/BoyoungHyeon/Web-Study-/blob/main/WebPractice/JSP/EL/source/fileSelect.jsp


#### 파일 20mb 초과 시 뜨는 alert 창
![image](https://user-images.githubusercontent.com/49936027/138054973-f4d98330-4a3d-4662-a735-04171b933368.png)

#### viePage.jsp
![image](https://user-images.githubusercontent.com/49936027/138054988-471ee89d-d521-4c18-80fe-0b33821de85b.png)

https://github.com/BoyoungHyeon/Web-Study-/blob/main/WebPractice/JSP/EL/source/viewPage.jsp****

