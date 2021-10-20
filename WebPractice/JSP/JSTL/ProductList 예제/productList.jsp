<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>ProductList 페이지</title>
  </head>
  <body>
    <h2>제품 목록 보기</h2>
    <hr>
    <form name="myForm" method="POST" action="ProductSelect.jsp">
      <jsp:useBean id="pro" scope="page" class="Product.ProductTest" />
      <%-- 주석 : 웹 브라우저에서 실행했을 때 소스보기하면 볼 수 없음 --%> 
      <%--  
      <select name="sel">
        <%
          for(String item : pro.getProductList()){
            out.println("<option>"+ item + "</option>");
          }
        %>
      </select>
      --%>
       <input type="submit" value="제품 선택"  />
    </form>
  </body>
</html>