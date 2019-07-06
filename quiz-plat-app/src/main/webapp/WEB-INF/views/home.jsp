<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Home</title>
</head>
<body>
<h1>Hello world!</h1>
 
<table>          
	<thead>
    	<tr>
            <th>첫번쨰내용</th>
            <th>두번쨰내용</th>
            <th>첫번째 투표수</th>
            <th>두번째 투표수</th>
            <th>총투표수</th>
        </tr>
        <c:forEach items="${writingDtlDtoList}" var="writingDtlDto">
        	<tr>
            	<td>${writingDtlDto.fir_writ_content}</td>
            	<td>${writingDtlDto.sec_writ_content}</td>
            	<td>${writingDtlDto.fir_vote_no}</td>
            	<td>${writingDtlDto.sec_vote_no}</td>
            	<td>${writingDtlDto.sum_vote}</td>
        	</tr>
    	</c:forEach>
	</tbody>
</table>
</body>
</html>