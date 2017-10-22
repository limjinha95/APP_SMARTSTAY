<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.ArrayList"%>
<%@  page import="smartstay.model.dto.Room"%>
<%
	ArrayList<Room> roomList = (ArrayList) request.getAttribute("roomList");
	System.out.println(roomList);
%>


<!DOCTYPE html>
<html>
<title>객실 관리</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css"
	integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"
	integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn"
	crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js"
	integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"
	integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb"
	crossorigin="anonymous"></script>
<style>
html, body, h1, h2, h3, h4, h5 {
	font-family: "Raleway", sans-serif
}

body {
	background-color: #f9f6f2;
}

ul {
	list-style-type: none;
	margin: 0;
	padding: 0;
	overflow: hidden;
	background-color: #333;
}

li {
	float: left;
}

li a {
	display: block;
	color: white;
	text-align: center;
	padding: 14px 16px;
	text-decoration: none;
}

.btn {
	background-color: #0d5ca2;
	width: 100px;
}

h2 {
	color: #f7971e;
}

.mySlides {
	width: 60%;
	display: block;
	margin-left: auto;
	margin-right: auto;
	margin-bottom: -6px;
}

.title {
	width: 300px;
}

p {
	margin-top: 17px;
}

.main {
	margin-left: 300px;
	margin-top: 43px;
}
</style>

<body>
	<div class="w3-bar w3-top w3-black w3-large" style="z-index: 4">
		<button
			class="w3-bar-item w3-button w3-hide-large w3-hover-none w3-hover-text-light-grey"
			onclick="w3_open();">
			<i class="fa fa-bars"></i>  Menu
		</button>
		<ul>
			<li><a href="./officeRegisterView.do">HOME</a></li>
			<li style="float: right"><a>SmartStay</a></li>
			<li style="float: right"><a href="./userLogoutAction.us">로그아웃</a></li>
		</ul>
	</div>

	<nav class="w3-sidebar w3-collapse w3-white w3-animate-left"
		style="z-index: 3; width: 300px;" id="mySidebar">
		<br> <img class="logo" src="./static/img/logo_2.png"
			style="width: 300px; height: auto;">
		<div class="w3-container w3-row"></div>
		<hr>
		<div class="w3-container">
			<h5>MENU</h5>
		</div>
		<div class="w3-bar-block">
			<a href="#"
				class="w3-bar-item w3-button w3-padding-16 w3-hide-large w3-dark-grey w3-hover-black"
				onclick="w3_close()" title="close menu"><i
				class="fa fa-remove fa-fw"></i>  Close Menu</a> <a
				href="./officeRegisterView.do"
				class="w3-bar-item w3-button w3-padding"><i
				class="fa fa-users fa-fw"></i> 호텔 관리</a> <a href="./roomList.rc"
				class="w3-bar-item w3-button w3-padding w3-blue"><i
				class="fa fa-bell fa-fw"></i> 객실 관리</a>
		</div>
	</nav>

	<div class="w3-overlay w3-hide-large w3-animate-opacity"
		onclick="w3_close()" style="cursor: pointer" title="close side menu"
		id="myOverlay"></div>

	<div class="main">

		<div class="w3-container" style="margin-top: 50px;">
			<div class="row">
				<div class="col-sm-10">
					<h2 class="title" style="margin-top: 40px;">객실 관리</h2>
				</div>
				<div class="col-sm-2">
					<button type="button" style="margin-top: 40px;"
						class="btn btn-primary" onclick="location.href='room_edit.jsp'">객실 등록</button>
				</div>
			</div>
			<%
				if (roomList != null) {

			%>
			<div class="row">
			<%
				for (int i = 0; i < roomList.size(); i++) {
			%>

			<div class="w3-third w3-container w3-margin-bottom">
				<a href="room_edit.jsp"><img
					src="./static/img/<%=roomList.get(i).getimage()%>"
					style="width: 276.33px; height: 153.52px; border-radius: 2px;"
					class="w3-hover-opacity"></a>
				<div class="w3-container w3-white">
					<p>
						<b>객실 이름 : <%=roomList.get(i).getRoomName()%> &nbsp; <%=roomList.get(i).getRoomNo()%>
							호
						</b>
					</p>
					<p><i class="fa fa-users"></i> <b>최대 인원 : <%=roomList.get(i).getMaximumNum() %> 명</b></p>
					<p><i class="fa fa-fw fa-male"></i> <b>기준 인원 : <%=roomList.get(i).getStandardNum() %> 명</b></p>
				</div>
			</div>
			

			<%
				if (i % 3 == 0) {
			%>
			<br>
			<%
				}

			 		}%>
			</div>
			<%
				} else {
			%>
			<h2>등록된 사진이 없습니다.</h2>
			<%
				}
			%>



			<div class="row">
				<div class="col-sm-4"></div>
				<div class="col-sm-4">
					<div class="w3-bar">
						<a href="#" class="w3-bar-item w3-button w3-hover-black">«</a> <a
							href="#" class="w3-bar-item w3-black w3-button">1</a> <a href="#"
							class="w3-bar-item w3-button w3-hover-black">2</a> <a href="#"
							class="w3-bar-item w3-button w3-hover-black">3</a> <a href="#"
							class="w3-bar-item w3-button w3-hover-black">4</a> <a href="#"
							class="w3-bar-item w3-button w3-hover-black">»</a>
					</div>
				</div>
			</div>
		</div>
</body>

<script>
	var mySidebar = document.getElementById("mySidebar");
	var overlayBg = document.getElementById("myOverlay");

	function w3_open() {
		if (mySidebar.style.display === 'block') {
			mySidebar.style.display = 'none';
			overlayBg.style.display = "none";
		} else {
			mySidebar.style.display = 'block';
			overlayBg.style.display = "block";
		}
	}

	function w3_close() {
		mySidebar.style.display = "none";
		overlayBg.style.display = "none";
	}

	var slideIndex = 1;
	showDivs(slideIndex);

	function plusDivs(n) {
		showDivs(slideIndex += n);
	}

	function currentDiv(n) {
		showDivs(slideIndex = n);
	}

	function showDivs(n) {
		var i;
		var x = document.getElementsByClassName("mySlides");
		var dots = document.getElementsByClassName("demo");
		if (n > x.length) {
			slideIndex = 1
		}
		if (n < 1) {
			slideIndex = x.length
		}
		for (i = 0; i < x.length; i++) {
			x[i].style.display = "none";
		}
		for (i = 0; i < dots.length; i++) {
			dots[i].className = dots[i].className
					.replace(" w3-opacity-off", "");
		}
		x[slideIndex - 1].style.display = "block";
		dots[slideIndex - 1].className += " w3-opacity-off";
	}
</script>

</body>

</html>