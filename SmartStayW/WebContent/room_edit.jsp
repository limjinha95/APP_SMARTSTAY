<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<title>객실 정보 수정</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ"
    crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn"
    crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js" integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n"
    crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb"
    crossorigin="anonymous"></script>
<style>
    html,
    body,
    h1,
    h2,
    h3,
    h4,
    h5 {
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
        background-color: #ffd200;
        width: 180px;
    }

    h2 {
        color: #f7971e;
    }

    .container {
        margin-top: 55px;
        margin-left: 10px;
    }

    h4 {
        margin-bottom: 20px;
    }

    .main {
        margin-left: 300px;
        margin-top: 43px;
    }
</style>

<body>
    <div class="w3-bar w3-top w3-black w3-large" style="z-index:4">
        <button class="w3-bar-item w3-button w3-hide-large w3-hover-none w3-hover-text-light-grey" onclick="w3_open();"><i class="fa fa-bars"></i>  Menu</button>
        <ul>
            <li><a href="#hotel_register.jsp">HOME</a></li>
            <li style="float:right"><a>SmartStay</a></li>
            <li style="float:right"><a href="#index.jsp">로그아웃</a></li>
        </ul>
    </div>

    <nav class="w3-sidebar w3-collapse w3-white w3-animate-left" style="z-index:3;width:300px;" id="mySidebar"><br>
        <img class="logo" src="./static/img/logo_2.png" style="width:300px; height:auto;">
        <div class="w3-container w3-row">
        </div>
        <hr>
        <div class="w3-container">
            <h5>MENU</h5>
        </div>
        <div class="w3-bar-block">
            <a href="#" class="w3-bar-item w3-button w3-padding-16 w3-hide-large w3-dark-grey w3-hover-black" onclick="w3_close()" title="close menu"><i class="fa fa-remove fa-fw"></i>  Close Menu</a>
            <a href="hotel_register.jsp" class="w3-bar-item w3-button w3-padding w3-blue"><i class="fa fa-users fa-fw"></i> 호텔 관리</a>
            <a href="room_manage.jsp" class="w3-bar-item w3-button w3-padding"><i class="fa fa-bell fa-fw"></i> 객실 관리</a>
        </div>
    </nav>

    <div class="main">

        <div class="w3-container" style="margin-top : 50px;">
            <h2 class="tittle" style="margin-top : 20px;">객실 정보 수정</h2>
        </div>

        <form>
            <div class="container">
                <h4><strong>객실 사진</strong></h4>
                <input type="file" name="m_file">
                <hr>
                <h4><strong>객실 정보</strong></h4>
                <div class="w3-row w3-large">
                    <div class="w3-col s3">
                        <p><i class="fa fa-fw fa-bed"></i> 객실 이름 </p>
                    </div>
                    <div class="w3-col s4">
                        <input type="text" class="form-control" id="usr">
                    </div>
                </div>
                <div class="w3-row w3-large">
                    <div class="w3-col s3">
                        <p><i class="fa fa-key"></i> 객실 종류 </p>
                    </div>
                    <div class="w3-col s4">
                        <input type="text" class="form-control" id="usr">
                    </div>
                </div>
                <div class="w3-row w3-large">
                    <div class="w3-col s3">
                        <p><i class="fa fa-credit-card"></i> 1박 가격 </p>
                    </div>
                    <div class="w3-col s4">
                        <input type="text" class="form-control" id="usr">
                    </div>
                </div>

                <div class="w3-row w3-large">
                    <div class="w3-col s3">
                        <p><i class="fa fa-fw fa-male"></i> 최대인원 수 </p>
                    </div>
                    <div class="w3-col s4">
                        <input type="text" class="form-control" id="usr">
                    </div>
                </div>
        </form>

        <hr>

        <h4><strong>수정완료</strong></h4>
        <p><button class="btn" onclick="location.href='hotel_register.jsp'">저장</button></p>
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

</script>

</body>

</html>