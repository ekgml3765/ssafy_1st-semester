<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인페이지</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<!-- Popper JS -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<style type="text/css">
button {
	margin-bottom: 15px;
}

input {
	margin: 10px;
}

div {
	margin: 5px;
}

a {
	margin-right: 20px;
	font-size: 20px;
}
</style>
</head>
<body>
	<div class="container">
		<div style="text-align: center; margin: 20px;">
			<h1>
				<strong>메인 페이지</strong>
			</h1>
		</div>
		<div style="border: 6px solid black; text-align: center">
			<form method="post" action="<%=request.getContextPath()%>/main.do">
				<input type="hidden" name="action" value="login">
				<div>
					<span>ID</span> <input type="text" id="usr" name="id">
				</div>
				<div>
					<span>PASSWORD</span> <input type="password" id="password"
						name="pass">
				</div>
				<button type="submit" class="btn btn-primary">LOGIN</button>
			</form>
		</div>
		<div style="text-align: center; margin-top: 35px;">
			<a href="#">상품등록</a> <a href="#">상품목록</a> <a href="#">마지막 등록한 상품</a>
		</div>
	</div>
</body>
</html>