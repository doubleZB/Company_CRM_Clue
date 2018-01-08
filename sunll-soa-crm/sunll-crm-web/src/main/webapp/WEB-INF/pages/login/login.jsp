<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="renderer" content="webkit">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <title>人脉旺CRM登录,人脉旺CRM，营销平台CRM系统</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/lgoinzzz/base.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/lgoinzzz/amazeui.css">
    <![endif]-->
    <style>
        html,
        body {
            width: 100%;
            height: 100%;
            font-family: "微软雅黑";
            min-width: 1000px;
            min-height: 600px;
            background: url(${pageContext.request.contextPath}/static/lgoinzzz/z_rmwBg.jpg) no-repeat;
            background-size: 100% 100%;
        }

        * {
            margin: 0;
            padding: 0;
        }

        .container {
            margin: 0;
            padding: 0;
            width: inherit;
            min-width: 1000px;
            height: 100%;
            position: relative;
        }

        .icon {
            width: 104px;
            height: 104px;
            background: url(${pageContext.request.contextPath}/static/lgoinzzz/logoCrm4.png) no-repeat;
            background-size: 100% 100%;
            position: absolute;
            left: 50%;
            top: 30px;
            margin-left: -48px;
        }

        .z_content {
            position: absolute;
            width: 583px;
            height: 420px;
            background: #fff;
            left: 50%;
            top: 50%;
            margin-left: -284px;
            margin-top: -169px;
            color: #fff;
            border-radius: 12px;
        }

        .z_content h3 {
            text-align: center;
            margin: 20px 0;
            font-size: 28px;
            color: #1A78E3;
            font-weight: 100;
        }

        form > div {
            width: 520px;
            height: 60px;
            border: 1px solid #ccc;
            border-radius: 3px;
            font-size: 18px;
            color: #656565;
            margin: 0 auto;
            padding: 12px 0;
        }

        form > div img {
            display: inline-block;
            width: 25px;
            height: 25px;
            margin: 0 10px;
            vertical-align: middle;
        }

        form > div > input {
            width: 80%;
            height: 24px;
            border: 0;
            background: none;
            font-size: 18px;
            text-indent: 10px;
        }

        .remember {
            margin: 10px 0;
            font-size: 14px;
            color: #999999;
        }

        .tools {
            overflow: auto;
            clear: both;
            padding: 0 30px;
            margin-top: 8px;
        }

        .forget {
            margin: 10px 0;
            text-align: right;
            color: #666;
            font-size: 14px;
        }

        .forget.am-fr {
            color: #E40000;
            text-decoration: underline;
        }

        .remember input {
            vertical-align: text-top;
            margin-right: 5px;
        }

        .btn {
            display: block;
            width: 520px;
            height: 59px;
            border: 0;
            background: #1A78E3;
            border-radius: 3px;
            color: #fff;
            font-size: 18px;
            color: #FAFAFB;
            margin: 10px auto 0 auto;
        }

        .btn:hover {
            color: #fff;
        }

        .copyRight {
            margin: 0 auto;
            color: #fff;
            font-size: 14px;
            position: absolute;
            bottom: 30px;
            left: 50%;
            margin-left: -240px;
        }

        /*修改placeholder颜色jdf*/

        ::-moz-placeholder { /* Mozilla Firefox 19+ */
            color: #9B9B9B;
        }

        input::-webkit-input-placeholder {
            color: #9B9B9B;
        }

        #msg_login {
            font-size: 14px;
            color: #E40000;
            text-indent: 34px;
            line-height: 30px;
            display: block;
        }

        /*扫码登录*/
        .loginNav {
            width: 340px;
            height: 52px;
            border-radius: 26px;
            border: 1px solid #E6E6E6;
            background: #F5F5F5;
            margin: 0 auto 32px;
        }

        .loginNav > div {
            width: calc(50% - 6px);
            height: 44px;
            margin: 3px 0 0 4px;
            border-radius: 22px;
            background: #F5F5F5;
            float: left;
            text-align: center;
            line-height: 44px;
            color: #666666;
            cursor: pointer;
        }

        .loginNav > div:nth-of-type(2) {
            color: #1A78E3;
            border: 1px solid #E6E6E6;
            background: #fff;
        }

        .contTwo {
            color: #666666;
            text-align: center;
            font-size: 16px;
            display: none;
        }

        .contTwo > div > div {
            width: 190px;
            height: 190px;
            margin: 0 auto;
            position: relative;
        }

        .contTwo > div > div > img {
            width: 100%;
            height: 100%;
        }

        .contTwo > div:nth-of-type(2) {
            display: none;
        }

        .contTwo > div:nth-of-type(2) span {
            color: #1a78e3;
        }

        .imgCont {
            position: absolute;
            width: 190px;
            height: 190px;
            left: 0;
            top: 0;
            background: rgba(255, 255, 255, .7);
        }

        .imgCont > img {
            width: 32px;
            height: 47px;
            margin: 74px auto 0;
            display: block;
        }
       .layui-layer-tips{
           left:780px!important;
           /*right: 0*/
       }
    </style>

</head>

<body>

<div class="container">
    <div class="icon"></div>
    <div class="z_content">
        <h3>欢迎登录人脉旺</h3>
        <div class="loginNav">
            <div onclick="loginItem(1,this)">扫码登录</div>
            <div onclick="loginItem(2,this)">密码登录</div>
        </div>
        <div class="contOne">
            <form id="reg-form" method="post" onsubmit="return false;">
                <div class="am-margin-bottom-sm inputWrap">
                    <img src="${pageContext.request.contextPath}/static/lgoinzzz/mine4.png" alt="">
                    <input name="accountNumber" type="text" id="accountNumber" placeholder="请输入帐号">
                </div>
                <div class="inputWrap">
                    <img src="${pageContext.request.contextPath}/static/lgoinzzz/mima4.png" alt="">
                    <input name="password" type="password" id="password" placeholder="请输入密码">
                </div>
                <span id="msg_login">${msg}</span>
            </form>
            <div>
                <button type="button" class="btn" id="btn">登录</button>
            </div>
            <p class="tools">
            </p>
        </div>
        <div class="contTwo">
            <div class="codeOne">
                <div>
                    <img id="loginCode" src="" alt="">
                </div>
                <p>请使用人脉旺手机端扫一扫以登录</p>
            </div>
            <div class="codeTwo">
                <div>
                    <img src="${pageContext.request.contextPath}/static/lgoinzzz/erwei.png" alt="">
                    <div class="imgCont"><img src="${pageContext.request.contextPath}/static/lgoinzzz/shua.png" alt=""
                                              onclick="reloading()">
                    </div>
                </div>
                <p>二维码已过期，请 <span onclick="reloading()">刷新重试</span></p>
            </div>
        </div>
    </div>
    <p class="copyRight">版权所有 ©北京聚通达科技股份有限公司未经许可不得复制、转载、违者必究！</p>
</div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/lgoinzzz/jquery.min.js "></script>
<script src="${pageContext.request.contextPath}/static/lgoinzzz/layer.js"></script>
<script src="${pageContext.request.contextPath}/static/lgoinzzz/common.js"></script>
<script type="text/javascript">
    $(function () {
                $("#btn").click(function () {
                    $("#msg_login").text("");
                    var username = $("#accountNumber").val();
                    var password = $("#password").val();
                    if (username == "") {
                        layer.tips("请输入用户名", "#accountNumber", 1);
                        return false;
                    }
                    if (password == "") {
                        layer.tips("请输入密码", "#password", 1);
                        return false;
                    }
                    if (username != "" && password != "") {
                        $.ajax({
                            url: "/login/getLoginAccountId",
                            type: 'POST',
                            async: false,
                            data: {"accountNumber":username,"password":password},
                            dataType: "json",
                            error: function () {
                            },
                            success: function (data) {
                                console.log(data);
                                if (data.status == 200) {
                                    sessionStorage.setItem("ACCOUNT_ID_TOKEN", data.data.id);
                                    window.location.href = "/jspSkip/chooseEnterprise";
                                } else {
                                    alert(data.msg );
                                    window.location.href = "/jspSkip/loginOut";
                                }
                            }
                        });
                    }
                });
                $("#accountNumber").focus(function () {
                    $(this).parents(".inputWrap").css("border-color", "#1A78E3")
                    $(this).css({
                        "border-left": "1px solid #1A78E3",
                        "color": "#1A78E3"
                    });
                    $(this).siblings("img").attr("src", "${pageContext.request.contextPath}/static/lgoinzzz/mine4_c.png");
                })
                $("#accountNumber").blur(function () {
                    $(this).parents(".inputWrap").css("border-color", "#ccc")
                    $(this).css({
                        "border-left": "1px solid #656565",
                        "color": "#656565"
                    });
                    $(this).siblings("img").attr("src", "${pageContext.request.contextPath}/static/lgoinzzz/mine4.png");
                });

                $("#password").focus(function () {
                    $(this).parents(".inputWrap").css("border-color", "#1A78E3")
                    $(this).css({
                        "border-left": "1px solid #1A78E3",
                        "color": "#1A78E3"
                    });
                    $(this).siblings("img").attr("src", "${pageContext.request.contextPath}/static/lgoinzzz/mima4_c.png");
                });

                $("#password").blur(function () {
                    $(this).parents(".inputWrap").css("border-color", "#ccc")
                    $(this).css({
                        "border-left": "1px solid #656565",
                        "color": "#656565"
                    });
                    $(this).siblings("img").attr("src", "${pageContext.request.contextPath}/static/lgoinzzz/mima4.png");
                });
                document.onkeydown = function mykeyDown(e) {
                    //compatible IE and firefox because there is not event in firefox
                    e = e || event;
                    if (e.keyCode == 13) {
                        $("#btn").click(); //调用登录按钮的登录事件
                    }
                }
            }
    );

</script>
</html>

