<!DOCTYPE html>
<html>
<title>객실 관리</title>
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

  .mySlides {
    display: none
  }

  .btn {
    background-color: #ffd200;
    width: 180px;
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
    <img class="logo" src="pic/logo_2.png" style="width:300px; height:auto;">
    <div class="w3-container w3-row">
    </div>
    <hr>
    <div class="w3-container">
      <h5>MENU</h5>
    </div>
    <div class="w3-bar-block">
      <a href="#" class="w3-bar-item w3-button w3-padding-16 w3-hide-large w3-dark-grey w3-hover-black" onclick="w3_close()" title="close menu"><i class="fa fa-remove fa-fw"></i>  Close Menu</a>
      <a href="hotel_register.jsp" class="w3-bar-item w3-button w3-padding"><i class="fa fa-users fa-fw"></i> 호텔 관리</a>
      <a href="room_manage.jsp" class="w3-bar-item w3-button w3-padding w3-blue"><i class="fa fa-bell fa-fw"></i> 객실 관리</a>
    </div>
  </nav>

  <div class="w3-overlay w3-hide-large w3-animate-opacity" onclick="w3_close()" style="cursor:pointer" title="close side menu"
    id="myOverlay"></div>

  <div class="main">

    <form>
    <div class="w3-container" style="margin-top : 50px;">
      <h2 class="tittle" style="margin-top : 20px;">객실 이름</h2>
      <div class="w3-display-container mySlides">
        <img src="pic/room.jpg" style="width:500px;height:300px;display:block;margin-left:auto;margin-right:auto;margin-bottom:-6px">
        <div class="w3-display-bottomleft w3-container w3-black">
        </div>
      </div>
      <div class="w3-display-container mySlides">
        <img src="pic/room2.jpg" style="width:500px;height:300px;display:block;margin-left:auto;margin-right:auto;margin-bottom:-6px">
        <div class="w3-display-bottomleft w3-container w3-black">
        </div>
      </div>
      <div class="w3-display-container mySlides">
        <img src="pic/room.jpg" style="width:500px;height:300px;display:block;margin-left:auto;margin-right:auto;margin-bottom:-6px">
        <div class="w3-display-bottomleft w3-container w3-black">
        </div>
      </div>
      <div class="w3-display-container mySlides">
        <img src="pic/room.jpg" style="width:500px;height:300px;display:block;margin-left:auto;margin-right:auto;margin-bottom:-6px">
        <div class="w3-display-bottomleft w3-container w3-black">
        </div>
      </div>
    </div>
    
    <div class="w3-row-padding w3-section">
      <div class="w3-col s4">
        <img class="demo w3-opacity w3-hover-opacity-off" src="pic/room.jpg" style="width:290px;height:160px;cursor:pointer" onclick="currentDiv(1)">
      </div>
      <div class="w3-col s4">
        <img class="demo w3-opacity w3-hover-opacity-off" src="pic/room2.jpg" style="width:290px;height:160px;cursor:pointer" onclick="currentDiv(2)">
      </div>
      <div class="w3-col s4">
        <img class="demo w3-opacity w3-hover-opacity-off" src="pic/room.jpg" style="width:290px;height:160px;cursor:pointer" onclick="currentDiv(3)">
      </div>
    </div>
    </form>

    <form>
    <div class="container">
      <hr>
      <h4><strong>객실 정보</strong></h4>

      <div class="w3-row w3-large">
        <div class="w3-col s6">
          <h5><i class="fa fa-fw fa-bed"></i> 객실이름 : </h5>
        </div>
        <div class="w3-col s6">
          <p><i class="fa fa-key"></i> 객실종류 : </p>
        </div>
      </div>
      <div class="w3-row w3-large">
        <div class="w3-col s6">
          <h5><i class="fa fa-credit-card"></i> 1박 가격 : </h5>
        </div>
        <div class="w3-col s6">
          <p><i class="fa fa-fw fa-male"></i> 최대 인원수 : n 명</p>
        </div>
      </div>
    </form>

      <hr>

      <h4><strong>수정</strong></h4>
      <p><button class="btn" onclick="">수정</button></p>
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
    if (n > x.length) { slideIndex = 1 }
    if (n < 1) { slideIndex = x.length }
    for (i = 0; i < x.length; i++) {
      x[i].style.display = "none";
    }
    for (i = 0; i < dots.length; i++) {
      dots[i].className = dots[i].className.replace(" w3-opacity-off", "");
    }
    x[slideIndex - 1].style.display = "block";
    dots[slideIndex - 1].className += " w3-opacity-off";
  }

</script>

</body>

</html>