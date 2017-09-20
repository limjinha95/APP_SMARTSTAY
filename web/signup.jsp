<!doctype html>
<html>
<title>회원가입</title>
<meta charset="UTF-8">

<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style>
        body {
            padding-bottom: 30px;
            background: linear-gradient(135deg, #f7971e 50%, #ffd200 100%, #f5f6f7 100%);
        }

        .header {
            padding-right: 15px;
            padding-left: 15px;
        }

        .header {
            border-bottom: 1px solid #f5f6f7;
        }

        .header h3 {
            padding-bottom: 19px;
            margin-top: 0;
            margin-bottom: 0;
            line-height: 40px;
        }

        @media (min-width: 768px) {
            .container {
                max-width: 730px;
            }
        }

        .well {
            background-image: linear-gradient(to bottom, #f5f6f7 0, #f5f6f7 100%);
        }

        .btn_form {
            text-align: center;
            padding: 5px;
            margin-top: 5px;
        }
    </style>
</head>

<body>
    <form>
    <div class="container">
        <h1 class="well">회원가입</h1>
        <div class="col-lg-12 well">
            <div class="row">
                <div class="col-sm-12">
                    <h2>사이트 이용 사항</h2>
                    <div class="row">
                        <div class="col-sm-3 form-group">
                            <label>아이디</label>
                            <input type="text" placeholder="아이디" class="form-control">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-4 form-group">
                            <label>패스워드</label>
                            <input type="password" placeholder="패스워드" class="form-control">
                        </div>
                        <div class="col-sm-4 form-group">
                            <label>패스워드 확인</label>
                            <input type="password" placeholder="패스워드 재확인" class="form-control">
                        </div>
                    </div>
                    <hr size="5" color="black">
                    <h2> 개인 및 사업장 관련 정보</h2>
                    <div class="row">
                        <div class="col-sm-3 form-group">
                            <label>성함</label>
                            <input type="text" placeholder="성함" class="form-control">
                        </div>
                        <div class="col-sm-3 form-group">
                            <label>전화번호</label>
                            <input type="text" placeholder="전화번호" class="form-control">
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-sm-6 form-group">
                            <label>사업자 등록번호</label>
                            <input type="text" placeholder="사업자 등록번호" class="form-control">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-3 form-group">
                            <label>시,도</label>
                            <select class="form-control">
                                                <optgroup label="시 / 도">
                                                  <option>서울특별시</option>
                                                  <option>인천광역시</option>
                                                  <option>부산광역시</option>                                          
                                                  <option>광주광역시</option>
                                                  <option>대구광역시</option>
                                                  <option>울산광역시</option>                                         산
                                                  <option>경기도</option>
                                                  <option>강원도</option>                                         도
                                                  <option>충청북도</option>
                                                  <option>충청남도</option>
                                                  <option>전라북도</option>                                         도
                                                  <option>전라남도</option>
                                                  <option>경상북도</option>
                                                  <option>경상남도</option>                                          
                                                </optgroup>
                                        </select>
                        </div>
                        <div class="col-sm-6 form-group">
                            <label>상세주소</label>
                            <input type="text" placeholder="상세주소" class="form-control">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </form>

    <form>
    <div class="container">
        <div class="col-lg-12 well">
            <div class="row">
                <div class="col-sm-12">
                    <h4>이용약관, 개인정보 수집 및 이용, 위치정보 이용약관(선택), 프로모션 안내 메일 수신(선택)에 모두 동의합니다.</h4>
                    <div class="row">
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10" style="background:navajowhite">
                            <label>정보통신망법 규정에 따라 스마트 스테이 회원가입을 신청하시는 분께 수집하는 개인정보의 항목, 개인정보의 수집 및 이용목적, 개인정보의 보유 및 이용기간을 안내 드리오니 자세히 읽은 후 동의하여 주시기 바랍니다.</br></br>
                            [ 수집하는 개인정보 ]</br></br>
                            스마트 스테이에 가입하게 되면 이용자는 스마트스테이의 호텔 등록 서비스를 이용할 수 있습니다. 회원제 서비스를 이용하기 위해 회원가입을 할 경우, 스마트 스테이는 서비스 이용을 위해 필요한 최소한의 개인정보를 수집합니다.
                            </label>
                        </div>
                        <div class="col-sm-1" style="background:#f5f6f7"></div>
                    </div>
                    <div class="row btn_form">
                        <button type="button" class="btn btn-lg btn-info">회원가입</button>
                        <button type="button" class="btn btn-lg btn-success" onclick="location.href='index.html'">취소</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>
</body>

</html>