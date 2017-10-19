<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>
<title>메인 페이지</title>
<meta charset="UTF-8">

<head>
  <link rel="stylesheet" href="index.css">
  <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:200,300" rel="stylesheet">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ"
    crossorigin="anonymous">
  <script src="https://code.jquery.com/jquery-3.1.1.slim.min.js" integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n"
    crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb"
    crossorigin="anonymous"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn"
    crossorigin="anonymous"></script>
</head>

<body>
  <script type="text/javascript">
    function cambiar_login() {
      document.querySelector('.cont_forms').className = "cont_forms cont_forms_active_login";
      document.querySelector('.cont_form_login').style.display = "block";
      document.querySelector('.cont_form_sign_up').style.opacity = "0";

      setTimeout(function () { document.querySelector('.cont_form_login').style.opacity = "1"; }, 400);

      setTimeout(function () {
        document.querySelector('.cont_form_sign_up').style.display = "none";
      }, 200);
    }

    function cambiar_sign_up(at) {
      document.querySelector('.cont_forms').className = "cont_forms cont_forms_active_sign_up";
      document.querySelector('.cont_form_sign_up').style.display = "block";
      document.querySelector('.cont_form_login').style.opacity = "0";

      setTimeout(function () {
        document.querySelector('.cont_form_sign_up').style.opacity = "1";
      }, 100);

      setTimeout(function () {
        document.querySelector('.cont_form_login').style.display = "none";
      }, 400);
    }
  </script>

  <section class="scene">
    <header>
    </header>
  </section>
  <section class="scene one">
    <div class="cotn_principal">
      <div class="cont_centrar">

        <div class="cont_login">
          <div class="cont_info_log_sign_up">
            <div class="col_md_login">
              <div class="cont_ba_opcitiy">
                <h2>로그인</h2>
                <p>로그인을 해주세요.</p>
                <button class="btn_login" onclick="cambiar_login()">LOGIN</button>
              </div>
            </div>
            <div class="col_md_sign_up">
              <div class="cont_ba_opcitiy">
                <h2>회원가입</h2>
                <p>회원가입을 해주세요.</p>
                <button class="btn_sign_up" onclick="cambiar_sign_up()">SIGN UP</button>
              </div>
            </div>
          </div>

          <div class="cont_back_info">
            <div class="cont_img_back_grey">
            </div>
          </div>

          <div class="cont_forms">
            <div class="cont_img_back_">
            </div>
            <div class="cont_form_login">
              <a href="#" onclick="ocultar_login_sign_up()"><i class="material-icons">&#xE5C4;</i></a>
              <h2>로그인</h2>
              <form action="userLoginAction.us" method="post">
	              <input type="text" placeholder="아이디" name="uid" />
	              <input type="password" placeholder="패스워드" name="upw" />
	              <button class="btn_login" type="submit">로그인</button>
			  </form>
            </div>
            <div class="cont_form_sign_up">
              <a href="signup.jsp" onclick="ocultar_login_sign_up()"><i class="material-icons">&#xE5C4;</i></a>
              <h2>회원가입</h2>
              <h5>회원가입을 하면 SmartStay의</br>서비스를 사용할 수 있습니다.</h5>
              <button class="btn_user"  onclick="location.href='signup.jsp'">회원가입</button>
            </div>
          </div>

        </div>
      </div>
    </div>
  </section>

</body>

</html>