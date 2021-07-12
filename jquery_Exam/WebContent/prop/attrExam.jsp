<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script>
$(function(){
	$("#a").on("click", function(){
		$("#testForm").attr("action","/jquery_Exam/prop/a.jsp");
// 		$("#testForm").prop("action","/jquery_Exam/prop/a.jsp");	
		$("#testForm").submit();
	});
	$("#b").on("click", function(){
		$("#testForm").prop("action","/jquery_Exam/prop/b.jsp");
		$("#testForm").submit();
	});
	$("#c").on("click", function(){
		$("#testForm").prop("action","/jquery_Exam/prop/c.jsp");
		$("#testForm").submit();
	});
	$("#textTest").on("click", function(){
// 		$("#human").text("안녕하세요<br /> 안녕하세요");
// 		$("#human").html("안녕하세요<BR /> 안녕하세요");
	});
	$("#textTest").on("click", function(){
// 		$("#a").appendTo($("#human"));
// 		$("#a").appendTo("body");
		$("#a").insertAfter($("#c"));
	});
	
});
</script>
</head>
<body>
	<form method="post" id="testForm">
		<input type="text" name="test"><br />		
		<input type="button" id="a" value="1번"><br />
		<input type="button" id="b" value="2번"><br />
		<input type="button" id="c" value="3번"><br />
		<input type="button" id="textTest" value="attr,prop 실습"><br />
		<div id="human"></div><br />
		111111111111111111111
	</form>

</body>
</html>