<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="scripts/jquery-1.8.3.min.js"></script>
<title>秒杀页面</title>
<script type="text/javascript">
	
	$(function() {
		var action = $("#msform").attr("action");
		$("#msformSubmit").click(function() {
			$.post(action, $("#msform").serialize(), function(data) {
				if (data == "false") {
					$("#message").html("抢购失败！");
				} else{
					$("#message").html("抢购成功！");
				}
			})
			return false;
		})
	})
	
	
</script>
</head>
<body>
	
	<h1>苹果x秒杀!</h1>
	<form id="msform" action="secondKill" method="post" >
		<input type="hidden" name="prdId" value="1001">
		<input type="button" value="秒杀!" id="msformSubmit">
	</form>
	<span><font color="red" id="message"></font></span>
</body>
</html>