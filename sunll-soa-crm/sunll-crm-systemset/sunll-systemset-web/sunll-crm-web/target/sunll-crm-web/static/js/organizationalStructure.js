var obj, objs, updateDirectorSkip, adjustmentDepartment;
var token = sessionStorage.getItem("accountToken");
var vm = new Vue({
    el: "#content",
    data: {
        user: [],
        departmentIds: [],
        directorIds: [],
        account: [],
        departmentIdByAdd: [],
        departmentNameByAdd: [],
        flag: false,
        department: [],
        people: "1",//全公司人数
        director: [],//主管
        typeId: '',
        departmentList: [],
        userMobile: "",
        userName: "",
        repeatDepartmentByDepartmentName: false,
        existenceUser: false,
        typeName: '',
        departmentName: [],
        DisplayMost: 10,//最多显示
        common: 50,//共多少条
        pages: 1,
        Page: 1,//第几页
        json: [],
        paging: [10, 20, 30, 40, 50],
        searchStatus: 2,//搜索标识
        checkUserId: [],
    },
    methods: {
        //下载导入模板
        downloadImportTemplate: function (url) {
            try {
                var elemIF = document.createElement("iframe");
                elemIF.src = url;
                elemIF.style.display = "none";
                document.body.appendChild(elemIF);
            } catch (e) {
                window.confirm("下载模板出错!");
            }
        },
//重置密码
        updatePassword: function (accountId, userName,mobile) {
            updatePassword(accountId, userName,mobile);
        },
        //日志
        Journal: function () {
            window.location.href = "skipJournal";
        },

        doUpload: function () {
            var file = $("#uploadFileInput").val();
            if (file == "") {
                layer.msg("请先选择要上传的文件！");
                return false;
            }
            var load = layer.load(1);
            var formData = new FormData($("#uploadForm")[0]);
            $.ajax({
                url: '/user/importUser?accountToken=' + token,
                type: 'POST',
                data: formData,
                async: false,
                cache: false,
                contentType: false,
                processData: false,
                dataType: "json",
                beforeSend: function () {
                    load = layer.load(1);
                },
                complete: function () {
                    layer.close(load);
                },
                success: function (data) {
                    if (!data.flag) {
                        $("#errorId").show();
                        $("#errorMessage").hide();
                        $("#errorMessageExcel").attr("href", "/user/userDownLoad?fileName=" + data.url);
                    } else {
                        $("#errorId").hide();
                        if (data.url == "1") {
                            $("#errorMessage").attr("style", "margin-top: 46px;color: lawngreen;")
                        } else {
                            $("#errorMessage").attr("style", "margin-top: 46px;color: #ff0000;")
                        }
                        $("#errorMessage").show();
                        $("#errorMessage").text(data.error);
                    }
                    $("#uploadFileInput").val("");
                    /*layer.close(importCluesOpen);*/
                },
                error: function (data) {
                }
            });
        },

        exportUser: function () {
            if (vm.checkUserId != [] && vm.checkUserId != "") {
                $("#accountToken").val(token);
                $("#selectUserForm").submit();
            } else {
                layer.msg("请选择需要导出的成员！")
            }
        },
        //全选
        checkAll: function () {
            this.checkUserId = [];
            if ($(".checkAll").is(':checked')) {
                $(".checkList").prop("checked", "true");
                $(".checkList").each(function (x) {
                    vm.checkUserId.push($(".checkList").eq(x).attr("id"));
                })
            } else {
                $(".checkList").removeAttr("checked");
                this.checkUserId = [];
            }
        },
        checkList: function () {
            var flog = true;
            this.checkUserId = [];
            $(".checkList").each(function (x) {
                if (!$(".checkList").eq(x).is(':checked')) {
                    $(".checkAll").removeAttr("checked");
                    flog = false;
                } else {
                    vm.checkUserId.push($(".checkList").eq(x).attr("id"));
                }
                if (flog) {
                    $(".checkAll").prop("checked", "true");
                }
            })
        },
        changePageSize: function (pageSize) {
            vm.DisplayMost = pageSize;
            reloadPage(1);
        },

        skipPageNumber: function () {
            reloadPage($("#pageNumber").val());
        },
        changeStatus: function (num, i, userId, userName) {
            updateUserTurn(i, userId, userName);

        },
        addDepartment: function (num) {//添加
            //num==1--添加         num==2--保存添加      num==3--取消添加
            if (!obj) {
                layer.msg("请选择部门");
            } else {
                if (num == 1) {
                    $(".addDepartment").css({"right": "0"});
                } else if (num == 2) {
                    if (vm.typeId != "" && vm.typeId != null) {
                        if ($("#departmentName").val() != null && $("#departmentName").val() != '') {
                            repeatDepartmentByDepartmentName();
                        } else {
                            layer.msg("部门名称为空！");
                        }
                    } else {
                        layer.msg("上级公司为空！");
                    }
                } else {
                    $(".addDepartment").css({"right": "-41%"});
                    $("#departmentName").val("");
                }
            }
        },
        editDepartment: function (num) {//编辑
            //num==1--编辑         num==2--保存编辑      num==3--取消编辑
            if (!obj) {
                layer.msg("请选择部门");
            } else {
                if (num == 1) {
                    $(".editDepartment").css({"right": "0"});
                } else if (num == 2) {
                    if (departmentName != null && departmentName != '') {
                        if ($("#depName").val() != null && $("#depName").val() != '') {
                            updateDepartment();
                        } else {
                            layer.msg("部门名称不能为空");
                        }
                    } else {
                        layer.msg("上级不能为空");
                    }
                } else {
                    $(".editDepartment").css({"right": "-41%"});
                }
            }
        },
        removeDepartment: function (num) {//删除
            //num==1--删除         num==2--保存删除      num==3--取消删除
            if (!obj) {
                layer.msg("请选择部门");
            } else {
                if (num == 1) {
                    $(".deleteDepartment").show();
                    $(".zhe").show();
                } else if (num == 2) {
                    existenceUser();
                    if (!vm.existenceUser) {
                        var status = '1';
                        existenceDepartmentChildrenDelete();
                        $(".zhe").hide();
                        $(".DepartmentCenter").hide();
                    } else {
                        $(".DepartmentCenter").hide();
                        $(".deleteDepartmentN").show();
                    }
                } else {
                    $(".DepartmentCenter").hide();
                    $(".zhe").hide();
                }
            }
        },
        mergeDepartment: function (num) {//合并
            //num==1--合并         num==2--确定合并      num==3--取消合并
            if (num == 1) {
                listDepartment();
                $(".mergeDepartment").show();
                $(".zhe").show();
            } else if (num == 2) {
                //alert($("#oldDepId").val());
                //alert($("#newDepId").val());
                if ($("#oldDepId").val() != "" && $("#oldDepId").val() != null) {
                    if ($("#newDepId").val() != "" && $("#newDepId").val() != null) {
                        existenceDepartmentChildren();
                    } else {
                        layer.msg("合并到的部门为空，请选择部门！")
                    }
                } else {
                    layer.msg("被合并的部门为空，请选择部门！")
                }
            } else {
                $(".mergeDepartment").hide();
                $(".zhe").hide();
                $("#oldDepId").val("");
                $("#newDepId").val("");
            }
        },
        PreviousPage: function () {//上一页
            if (this.Page > 1) {
                this.Page--;
                reloadPage(this.Page);
            } else {
                layer.msg("已经是第一页了");
            }
        },
        nextPage: function () {//下一页
            if (this.Page < this.pages) {
                this.Page++;
                reloadPage(this.Page);
            } else {
                layer.msg("已经是最后一页了");
            }
        },
        AddMember: function (num) {//添加成员
            //num==1--添加         num==2--保存添加      num==3--取消添加
            if (num == 1) {
                $("input[name='name']").val('');
                $("input[name='mobile']").val('');
                $("input[name='userEmail']").val('');
                $("#mobileError").text('');
                $("#departmentError").text('');
                $("#nameError").text('');
                $("#userEmailError").text('');
                $(".AddMember").css({"right": "0"})
            } else if (num == 2) {
                $("#mobileError").text('');
                $("#departmentError").text('');
                $("#nameError").text('');
                $("#userEmailError").text('');
                var reg = new RegExp("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$"); //正则表达式
                var ebg = new RegExp("^1[3|4|5|7|8][0-9]{9}$");
                var depId = vm.departmentIdByAdd;
                var userName = $("input[name='name']").val();
                var userMobile = $("input[name='mobile']").val();
                var userEmail = $("input[name='userEmail']").val();
                var flag = true;
                if (depId.length == 0) {
                    flag = false;
                    $("#departmentError").text("部门不能为空")
                } else {
                    $("#departmentError").text("")
                }
                if (userName == "") {
                    flag = false;
                    $("#nameError").text("姓名不能为空")
                } else {
                    $("#nameError").text("")
                }
                if (userMobile === "") {
                    flag = false;
                    $("#mobileError").text("手机号不能为空")
                } else {
                    if (!(ebg.test(userMobile))) {
                        flag = false;
                        $("#mobileError").text("手机号有误，请重新填写！")
                    }
                }
                if (userEmail != "") {
                    if (!reg.test(userEmail)) {
                        flag = false;
                        $("#userEmailError").text("邮箱填写有误，请重新填写！")
                    } else {
                        $("#userEmailError").text('');
                    }
                }
                if (flag) {
                    resetMobile(userMobile);
                }
            } else {
                $(".AddMember").css({"right": "-41%"})
            }
        },
        editMember: function (num, userId, accountId) {//编辑成员
            //num==1--编辑         num==2--保存编辑      num==3--取消编辑
            if (num == 1) {
                $(".editMember").css({"right": "0"});
                $("#name").val('');
                $("#mobile").val('');
                $("#userEmail").val('');
                $("#departmentUpdateError").text("");
                $("#directorUpdateError").text("");
                $("#nameUpdateError").text("");
                $("#userEmailUpdateError").text("");
                selectUser(userId, accountId);
                $("#name").val(vm.user.name);
            } else if (num == 2) {
                var flag = true;
                var reg = new RegExp("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$");
                var userName = $("#name").val();
                var userMobile = $("#mobile").val();
                var userEmail = $("#userEmail").val();
                var department = vm.user.userDepartmentList;
                var director = vm.user.directorList;
                if (department.length == 0) {
                    flag = false;
                    $("#departmentUpdateError").text("部门不能为空")
                } else {
                    $("#departmentUpdateError").text("")
                }
                if (userName === "") {
                    flag = false;
                    $("#nameUpdateError").text("姓名不能为空")
                } else {
                    $("#nameUpdateError").text("")
                }
                //if (userMobile === "") {
                //    flag = false;
                //    $("#mobileUpdateError").text("手机号不能为空")
                //} else {
                //    if (!(/^1[3|4|5|8][0-9]\d{4,8}$/.test(userMobile))) {
                //        flag = false;
                //        $("#mobileUpdateError").text("手机号有误，请重新填写！")
                //    }
                //}
                if (userEmail != "") {
                    if (!reg.test(userEmail)) {
                        flag = false;
                        $("#userEmailUpdateError").text("邮箱填写有误，请重新填写！")
                    } else {
                        $("#userEmailUpdateError").text("")
                    }
                }
                if (flag) {
                    updateUser();
                }
            } else {
                $(".editMember").css({"right": "-41%"})
            }
        },
        SearchEmployees: function (num) {//搜索员工
            //num==1--搜索         num==2--查找搜索      num==3--取消搜索
            if (num == 1) {
                $(".mainSearch").show();
                $(".headBtn").hide();
            } else if (num == 2) {
                var search = $("#search").val().length;
                if (search == 11 && /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(14[0-9]{1})|)+\d{8})$/.test($("#search").val())) {
                    vm.userMobile = $("#search").val();
                    reloadPage(1)
                } else {
                    vm.userName = $("#search").val();
                    reloadPage(1)
                }
            } else {
                $(".mainSearch").hide();
                $(".headBtn").show();
            }
        }
        ,
        exportCont: function (e) {//导入
            $(".export").show();
            $(".Import").hide();
            $(e.target).addClass("exportImportNavaction").siblings().removeClass("exportImportNavaction");
        }
        ,
        ImportCont: function (e) {//导出
            $(".export").hide();
            $(".Import").show();
            $(e.target).addClass("exportImportNavaction").siblings().removeClass("exportImportNavaction");
        }
        ,
        BatchExport: function (num) {//导入导出
            if (num == 1) {
                $(".exportImport").show();
                $(".zhe").show();
            } else {
                $(".exportImport").hide();
                $(".zhe").hide();
            }
        },
        AdjustmentDepartment: function () {//调整部门
            if (vm.checkUserId != [] && vm.checkUserId != "") {
                adjustmentDepartment = layer.open({
                    type: 2,
                    title: false,
                    closeBtn: 0,
                    area: ['70%', '85%'],
                    content: "/department/skipDepartmentMultiple",
                    anim: 1,
                    btn: ['确定', '取消'],
                    yes: function (index, layero) {
                        var iframeWin = window[layero.find('iframe')[0]['name']];
                        var departmentIds = [];
                        if (iframeWin.vm.peopleId != null && iframeWin.vm.peopleId != '') {
                            for (var i = 0; i < iframeWin.vm.peopleNum.length; i++) {
                                departmentIds.push(iframeWin.vm.peopleNum[0].id);
                            }
                            if (departmentIds != [] && departmentIds.length > 0) {
                                adjustmentDepartmentByIds(departmentIds);
                            }
                        } else {
                            layer.msg("请选择部门！");
                        }
                    },
                    btn2: function (index, layero) {

                    }
                })
            } else {
                layer.msg("请选择需要调整的成员！");
            }
        }
        ,

//添加部门：选择部门
        skipDepartmentAdd: function () {
            adjustmentDepartment = layer.open({
                type: 2,
                title: false,
                closeBtn: 0,
                area: ['70%', '82%'],
                content: "/department/skipDepartmentalRadio",
                anim: 1,
                btn: ['确定', '取消'],
                yes: function (index, layero) {
                    var iframeWin = window[layero.find('iframe')[0]['name']];
                    if (iframeWin.vm.peopleId != null && iframeWin.vm.peopleId != '') {
                        vm.departmentName = iframeWin.vm.peopleNum[0].name;
                        vm.typeId = iframeWin.vm.peopleNum[0].id;
                        layer.close(adjustmentDepartment)
                    } else {
                        layer.msg("请选择部门！");
                    }
                },
                btn2: function (index, layero) {

                }
            })
        }
        ,


//添加部门：选择上级部门
        skipDepartmentUpdate: function () {
            adjustmentDepartment = layer.open({
                type: 2,
                title: false,
                closeBtn: 0,
                area: ['70%', '82%'],
                content: "/department/skipDepartmentalRadio",
                anim: 1,
                btn: ['确定', '取消'],
                yes: function (index, layero) {
                    var iframeWin = window[layero.find('iframe')[0]['name']];
                    if (iframeWin.vm.peopleId != null && iframeWin.vm.peopleId != '') {
                        vm.department.pName = iframeWin.vm.peopleNum[0].name;
                        vm.department.pid = iframeWin.vm.peopleNum[0].id;
                        layer.close(adjustmentDepartment)
                    } else {
                        layer.msg("请选择部门！");
                    }
                },
                btn2: function (index, layero) {

                }
            })
        }
        ,

//添加部门：选择主管
        skipDirector: function () {
            adjustmentDepartment = layer.open({
                type: 2,
                title: false,
                closeBtn: 0,
                area: ['70%', '82%'],
                content: "/department/skipDirector",
                anim: 1,
                btn: ['确定', '取消'],
                yes: function (index, layero) {
                    var iframeWin = window[layero.find('iframe')[0]['name']];
                    if (iframeWin.vm.peopleId != null && iframeWin.vm.peopleId != '') {
                        vm.department.directorList = []
                        for (var i = 0; i < iframeWin.vm.peopleNum.length; i++) {
                            var director = {userId: "", name: ""};
                            director.userId = iframeWin.vm.peopleNum[i].id;
                            director.name = iframeWin.vm.peopleNum[i].name;
                            vm.department.directorList.push(director);
                        }
                        layer.close(adjustmentDepartment)
                    } else {
                        layer.msg("请选择主管！");
                    }
                },
                btn2: function (index, layero) {

                }
            })
        }
        ,

//去设置：选择主管
        skipDirectorSetting: function () {
            updateDirectorSkip = layer.open({
                type: 2,
                title: false,
                closeBtn: 0,
                area: ['70%', '82%'],
                content: "/department/skipDirector",
                anim: 1,
                btn: ['确定', '取消'],
                yes: function (index, layero) {
                    var iframeWin = window[layero.find('iframe')[0]['name']];
                    if (iframeWin.vm.peopleId != null && iframeWin.vm.peopleId != '') {
                        var directorIds = [];
                        var departmentIds = [];
                        for (var i = 0; i < iframeWin.vm.peopleNum.length; i++) {
                            if (iframeWin.vm.peopleNum[i].id[0] == "d") {
                                var length = iframeWin.vm.peopleNum[i].id.length
                                departmentIds.push(iframeWin.vm.peopleNum[i].id.substring(1, length + 1));
                            } else {
                                directorIds.push(iframeWin.vm.peopleNum[i].id)
                            }
                        }
                        if ((directorIds != [] && directorIds.length > 0) || (departmentIds != [] && departmentIds.length > 0)) {
                            updateDirector(directorIds, departmentIds)
                        }
                    } else {
                        layer.msg("请选择主管！");
                    }
                },
                btn2: function (index, layero) {

                }
            })
        }
        ,

//添加成员：部门多选
        skipDepartmentMultiple: function () {
            adjustmentDepartment = layer.open({
                type: 2,
                title: false,
                closeBtn: 0,
                area: ['70%', '82%'],
                content: "/department/skipDepartmentMultiple",
                anim: 1,
                btn: ['确定', '取消'],
                yes: function (index, layero) {
                    var iframeWin = window[layero.find('iframe')[0]['name']];
                    if (iframeWin.vm.peopleId != null && iframeWin.vm.peopleId != '') {
                        vm.departmentIdByAdd = [];
                        vm.departmentNameByAdd = [];
                        for (var i = 0; i < iframeWin.vm.peopleNum.length; i++) {
                            vm.departmentIdByAdd.push(iframeWin.vm.peopleNum[i].id);
                            vm.departmentNameByAdd.push(iframeWin.vm.peopleNum[i].name);
                        }
                        layer.close(adjustmentDepartment)
                    } else {
                        layer.msg("请选择部门！");
                    }
                },
                btn2: function (index, layero) {

                }
            })
        }
        ,

//编辑成员：部门多选
        skipDepartmentMultipleByUpdateUser: function () {
            adjustmentDepartment = layer.open({
                type: 2,
                title: false,
                closeBtn: 0,
                area: ['70%', '82%'],
                content: "/department/skipDepartmentMultiple",
                anim: 1,
                btn: ['确定', '取消'],
                yes: function (index, layero) {
                    var iframeWin = window[layero.find('iframe')[0]['name']];
                    if (iframeWin.vm.peopleId != null && iframeWin.vm.peopleId != '') {
                        vm.user.userDepartmentList = [];
                        vm.departmentName = [];
                        vm.departmentIds = [];
                        for (var i = 0; i < iframeWin.vm.peopleNum.length; i++) {
                            var department = {departmentId: "", departmentName: ""};
                            department.departmentId = iframeWin.vm.peopleNum[i].id;
                            vm.departmentIds.push(iframeWin.vm.peopleNum[i].id);
                            department.departmentName = iframeWin.vm.peopleNum[i].name;
                            vm.user.userDepartmentList.push(department);
                        }
                        layer.close(adjustmentDepartment)
                    } else {
                        layer.msg("请选择部门！");
                    }
                },
                btn2: function (index, layero) {
                }
            })
        }
        ,

        //编辑成员：部门多选（主管）
        skipDepartmentMultipleByUpdateUserDirector: function () {
            adjustmentDepartment = layer.open({
                type: 2,
                title: false,
                closeBtn: 0,
                area: ['70%', '82%'],
                content: "/department/skipDepartmentMultiple",
                anim: 1,
                btn: ['确定', '取消'],
                yes: function (index, layero) {
                    var iframeWin = window[layero.find('iframe')[0]['name']];
                    if (iframeWin.vm.peopleId != null && iframeWin.vm.peopleId != '') {
                        vm.user.directorList = [];
                        vm.directorIds = [];
                        for (var i = 0; i < iframeWin.vm.peopleNum.length; i++) {
                            var director = {departmentId: "", departmentName: ""};
                            director.departmentId = iframeWin.vm.peopleNum[i].id;
                            vm.directorIds.push(iframeWin.vm.peopleNum[i].id);
                            director.departmentName = iframeWin.vm.peopleNum[i].name;
                            vm.user.directorList.push(director);
                            layer.close(adjustmentDepartment)
                        }
                    } else {
                        layer.msg("请选择部门！");
                    }
                },
                btn2: function (index, layero) {

                }
            })
        }
        ,

        removeUpdateDirector: function (num) {
            this.user.directorList.splice(num, 1);
            vm.directorIds.splice(num, 1);
        }
        ,
        removeUpdateDepartment: function (num) {
            this.user.userDepartmentList.splice(num, 1);
        }
        ,
        removeDepByUserAdd: function (num) {
            this.departmentIdByAdd.splice(num, 1);
            this.departmentNameByAdd.splice(num, 1);
        }
        ,
        removeDep: function (num) {
            this.departmentName = "";
        }
        ,
        removeDepAndUser: function (num) {
            this.department.directorList.splice(num, 1)
        }
        ,
    }
});
$('#search').bind('input propertychange', function () {
    if ($(this).val() == "") {
        vm.searchStatus = 2;
    } else {
        vm.searchStatus = 1;
    }
});


//获取用户列表
function reloadPage(pageNumber) {
    if (pageNumber == 0) {
        layer.msg("当前是第一页啦，没有上一页！");
        return;
    }
//        var userNameToFind = $("#userNameToFind").val();
//        var userDepartmentId = $("#userDepartmentId").val();
    var index = layer.load(0, {shade: [0.3, '#000']});
    $.ajax({
        url: "/user/ListPageUser?pageNumber=" + pageNumber + "&pageSize=" + vm.DisplayMost,
        type: 'POST',
        async: false,
        data: {"departmentId": vm.typeId, "name": vm.userName, "mobile": vm.userMobile},
        dataType: "json",
        traditional: true,
        error: function () {
            $(this).addClass("done");
        },
        success: function (data) {
            vm.common = data.total;
            vm.Page = data.pageNum;
            vm.pages = data.pages;
            vm.json = data.list;
            layer.close(index);
        }
    });
}

//获取部门主管
function director() {
    $.ajax({
        url: "/director/listDirectorName",
        type: 'POST',
        async: false,
        data: {"departmentId": vm.typeId},
        dataType: "json",
        traditional: true,
        error: function () {
            layer.msg("出错了！");
        },
        success: function (data) {
            vm.director = data;
        }
    });
}

//新建部门
function addDepartment() {
    var index = layer.load(0, {shade: [0.3, '#000']});
    $.ajax({
        url: "/department/insertDepartment",
        type: 'POST',
        async: false,
        data: {"pid": vm.typeId, "name": $("#departmentName").val()},
        dataType: "json",
        traditional: true,
        error: function () {
            layer.msg("出错了！");
        },
        success: function (data) {
            if (data) {
                layer.msg("添加部门成功")
                $("#departmentName").val("");
                listDepartmentByCompanyId();
                layer.close(index);
                $(".addDepartment").css({"right": "-41%"});
            } else {
                layer.msg("添加部门失败")
            }
        }
    });
}

//查询部门下是否存在用户
function existenceUser() {
    $.ajax({
        url: "/user/existenceUser",
        type: 'POST',
        async: false,
        data: {"departmentId": vm.typeId},
        dataType: "json",
        traditional: true,
        error: function () {
            layer.msg("出错了！");
        },
        success: function (data) {
            vm.existenceUser = data;
        }
    });
}

//删除部门
function deleteDepartment() {
    $.ajax({
        url: "/department/deleteDepartment",
        type: 'POST',
        async: false,
        data: {"id": vm.typeId, "name": vm.typeName, "accountToken": token},
        dataType: "json",
        traditional: true,
        error: function () {
            layer.msg("出错了！");
        },
        success: function (data) {
            if (data) {
                layer.msg("删除部门成功")
                listDepartmentByCompanyId();
                obj = false;
            } else {
                layer.msg("删除部门失败")
            }
        }
    });
}

//查询部门名称是否重复
function repeatDepartmentByDepartmentName() {
    $.ajax({
        url: "/department/repeatDepartmentByDepartmentName",
        type: 'POST',
        async: false,
        data: {"departmentName": $("#departmentName").val()},
        dataType: "json",
        traditional: true,
        error: function () {
            layer.msg("出错了！");
        },
        success: function (data) {
            if (data) {
                layer.msg("部门名称重复，请重新输入！")
            } else {
                addDepartment();
            }
        }
    });
}
//查询部门
function listDepartment() {
    $.ajax({
        url: "/department/listDepartment",
        type: 'POST',
        async: false,
        data: {"accountToken": token},
        dataType: "json",
        traditional: true,
        error: function () {
            layer.msg("出错了！");
        },
        success: function (data) {
            vm.departmentList = data;
        }
    });
}

//合并部门
function mergeDepartment() {
    $.ajax({
        url: "/department/mergeDepartment",
        type: 'POST',
        async: false,
        data: {"oldDepartmentId": $("#oldDepId").val(), "newDepartmentId": $("#newDepId").val(), "accountToken": token},
        dataType: "json",
        traditional: true,
        error: function () {
            layer.msg("出错了！");
        },
        success: function (data) {
            if (data) {
                layer.msg("合并部门成功！");
                $(".mergeDepartment").hide();
                $(".zhe").hide();
                $("#oldDepId").val("");
                $("#newDepId").val("");
                listDepartmentByCompanyId();
            } else {
                layer.msg("合并部门失败！")
            }
        }
    });
}
//修改部门
function updateDepartment() {
    var directorList = vm.department.directorList;
    var departmentIds = [];
    var userIds = [];
    if (directorList != '' && directorList.length > 0) {
        for (var i = 0; i < directorList.length; i++) {
            if (directorList[i].userId[0] == "d") {
                var length = directorList[i].userId.length
                departmentIds.push(directorList[i].userId.substring(1, length + 1));
            } else {
                userIds.push(directorList[i].userId);
            }
        }
    }

    $.ajax({
        url: "/department/updateDepartment",
        type: 'POST',
        async: false,
        data: {
            "pid": vm.department.pid, "id": vm.typeId, "name": $("#depName").val(),
            "departmentIds": departmentIds, "userIds": userIds, "flag": vm.flag, "accountToken": token
        },
        dataType: "json",
        traditional: true,
        error: function () {
            layer.msg("出错了！");
        },
        success: function (data) {
            if (data) {
                $(".editDepartment").css({"right": "-41%"})
                listDepartmentByCompanyId();
                layer.msg("修改部门成功！")
            } else {
                layer.msg("修改部门失败！")
            }
        }
    });
}

//查询当前部门下是否存在子部门
function existenceDepartmentChildren(status) {
    $.ajax({
        url: "/department/existenceDepartmentChildren",
        type: 'POST',
        async: false,
        data: {"departmentId": $("#oldDepId").val()},
        dataType: "json",
        traditional: true,
        error: function () {
            layer.msg("出错了！");
        },
        success: function (data) {
            if (!data) {
                mergeDepartment();
            } else {
                layer.msg("当前部门下存在子部门，请选择不含有子部门的部门！")
            }
        }
    });
}


//查询当前部门下是否存在子部门
function existenceDepartmentChildrenDelete() {
    $.ajax({
        url: "/department/existenceDepartmentChildren",
        type: 'POST',
        async: false,
        data: {"departmentId": vm.typeId},
        dataType: "json",
        traditional: true,
        error: function () {
            layer.msg("出错了！");
        },
        success: function (data) {
            if (!data) {
                deleteDepartment();
            } else {
                layer.msg("当前部门下存在子部门，请选择不含有子部门的部门！")
            }
        }
    });
}


//添加用户
function addUser() {
    $.ajax({
        url: "/user/insertUser?departmentIds=" + vm.departmentIdByAdd,
        type: 'POST',
        async: false,
        data: $("#userForm").serialize(),
        dataType: "json",
        traditional: true,
        error: function () {
            layer.msg("出错了！");
        },
        success: function (data) {
            if (data) {
                layer.msg("添加用户成功");
                $(".AddMember").css({"right": "-41%"});
                listDepartmentByCompanyId();
            } else {
                layer.msg("议案家用户失败");
            }
        }
    });
}

//查询部门
function selectDepartment() {
    $.ajax({
        url: "/department/selectDepartmentById",
        type: 'POST',
        async: false,
        data: {"departmentId": vm.typeId},
        dataType: "json",
        traditional: true,
        error: function () {
            layer.msg("出错了！");
        },
        success: function (data) {
            vm.department = data;
            $("#depName").val(vm.department.name);
            if (data.directorList != null && data.directorList.length > 0) {
                vm.flag = true;
            }
        }
    });
}

//查询用户根据用户id和账户id
function selectUser(userId, accountId) {
    $.ajax({
        url: "/user/selectUser",
        type: 'POST',
        async: false,
        data: {"userId": userId, "accountId": accountId},
        dataType: "json",
        traditional: true,
        error: function () {
            layer.msg("出错了！");
        },
        success: function (data) {
            vm.user = [];
            vm.user = data.user;
            vm.departmentIds = [];
            vm.directorIds = [];
            for (var i = 0; i < data.user.userDepartmentList.length; i++) {
                vm.departmentIds.push(data.user.userDepartmentList[i].departmentId)
            }
            for (var i = 0; i < data.user.directorList.length; i++) {
                vm.directorIds.push(data.user.directorList[i].departmentId)
            }

            vm.account = data.account;
        }
    });
}


//编辑用户
function updateUser() {
    $.ajax({
        url: "/user/updateUser?departmentIds=" + vm.departmentIds + "&directorDepartmentIds=" + vm.directorIds,
        type: 'POST',
        async: false,
        data: $("#updateUserForm").serialize(),
        dataType: "json",
        traditional: true,
        error: function () {
            layer.msg("出错了！");
        },
        success: function (data) {
            if (data) {
                layer.msg("编辑用户成功");
                listDepartmentByCompanyId();
                $(".editMember").css({"right": "-41%"})
            } else {
                layer.msg("编辑用户失败");
            }
        }
    });
}


//去设置：编辑主管
function updateDirector(directorIds, departmentIds) {
    $.ajax({
        url: "/director/updateDirector",
        type: 'POST',
        async: false,
        data: {
            "departmentId": vm.typeId,
            "directorIds": directorIds,
            "departmentIds": departmentIds,
            "accountToken": token
        },
        dataType: "json",
        traditional: true,
        error: function () {
            layer.msg("出错了！");
        },
        success: function (data) {
            if (data) {
                layer.close(updateDirectorSkip);
                listDepartmentByCompanyId();
                layer.msg("主管设置成功")
            } else {
                layer.msg("主管设置失败")
            }
        }
    });
}

//查询用户根据用户id和账户id
function updateUserTurn(status, userId, userName) {
    $.ajax({
        url: "/user/updateUserTurn",
        type: 'POST',
        async: false,
        data: {"id": userId, "name": userName, "activation": status, "accountToken": token},
        dataType: "json",
        traditional: true,
        error: function () {
            layer.msg("出错了！");
        },
        success: function (data) {
            if (data) {
                layer.msg("操作成功");
                reloadPage(1);
            } else {
                layer.msg("操作失败");
            }
        }
    });
}


function adjustmentDepartmentByIds(departmentIds) {
    $.ajax({
        url: "/department/adjustmentDepartment",
        type: 'POST',
        async: false,
        data: {"userIds": vm.checkUserId, "departmentIds": departmentIds, "accountToken": token},
        dataType: "json",
        traditional: true,
        error: function () {
            layer.msg("出错了！");
        },
        success: function (data) {
            if (data) {
                layer.msg("操作成功");
                layer.close(adjustmentDepartment);
                reloadPage(1);
            } else {
                layer.msg("操作失败");
            }
        }
    });
}

function exportUser() {
    $.ajax({
        url: "/user/exportUser?accountToken=" + token,
        type: 'POST',
        async: false,
        data: $("#userForm").serialize(),
        dataType: "json",
        traditional: true,
        error: function () {
            layer.msg("出错了！");
        },
        success: function (data) {

        }
    });
}

function updatePassword(accountId, userName,mobile) {
    alert(mobile)
    $.ajax({
        url: "/accountSet/updatePassword",
        type: 'POST',
        async: false,
        data: {"id": accountId, "userName": userName,"mobile":mobile, "accountToken": token},
        dataType: "json",
        traditional: true,
        error: function () {
            $(this).addClass("done");
        },
        success: function (data) {
            if (data) {
                layer.msg("重置密码成功！");
                $(".editMember").css({"right": "-41%"});
            } else {
                layer.msg("重置密码失败！");
            }
        }
    });
}

function resetMobile(mobile) {
    $.ajax({
        url: "/accountSet/resetMobile",
        type: 'POST',
        async: false,
        data: {"mobile": mobile},
        dataType: "json",
        traditional: true,
        error: function () {
            $(this).addClass("done");
        },
        success: function (data) {
            if (data) {
                $("#mobileError").text("手机号重复，请重新填写！");
            } else {
                $("#mobileError").text("");
                addUser();
            }
        }
    });
}


var curMenu = null, zTree_Menu = null;
var setting = {
    view: {
        showLine: false,
        showIcon: false,
        selectedMulti: false,
        dblClickExpand: false,
        addDiyDom: addDiyDom
    },
    data: {
        simpleData: {
            enable: true,
            idKey: "id", // id编号命名,
            pIdKey: "pid", // 父id编号命名
            rootId: 0,
        }
    },
    callback: {
        beforeClick: beforeClick,
        onClick: onClick
    }
};


var zNodes = [];
listDepartmentByCompanyId();
function listDepartmentByCompanyId() {
    $.ajax({
        url: "/department/selectByCompanyId",
        type: 'POST',
        async: false,
        data: {"accountToken": token},
        dataType: "json",
        traditional: true,
        error: function () {
            layer.msg("出错了！");
        },
        success: function (data) {
            zNodes = data;
            ready();
        }
    });
}

function addDiyDom(treeId, treeNode) {
    var spaceWidth = 5;
    var switchObj = $("#" + treeNode.tId + "_switch"),
        icoObj = $("#" + treeNode.tId + "_ico");
    switchObj.remove();
    icoObj.before(switchObj);

    if (treeNode.level > 1) {
        var spaceStr = "<span style='display: inline-block;width:" + (spaceWidth * treeNode.level) + "px'></span>";
        switchObj.before(spaceStr);
    }
}

function beforeClick(treeId, treeNode) {
    obj = treeNode;
    objs = treeId;
    //if (treeNode.level == 0 ) {
    //	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
    //	zTree.expandNode(treeNode);
    //	return false;
    //}
    return true;
}

function onClick(event, treeId, treeNode, clickFlag) {
    $("#search").val();
    vm.userName = '';
    vm.userMobile = '';
    vm.typeId = treeNode.id;
    vm.typeName = treeNode.name;
    var w = treeNode.name.indexOf("(");
    vm.departmentName = treeNode.name.substring(0, w);
    vm.departmentNameByAdd = [];
    vm.departmentIdByAdd = [];
    vm.departmentNameByAdd.push(vm.departmentName);
    vm.departmentIdByAdd.push(treeNode.id);
    director();
    reloadPage(1);
    selectDepartment();
}

function ready(){
    $(document).ready(function () {
        var treeObj = $("#treeDemo");
        $.fn.zTree.init(treeObj, setting, zNodes);
        zTree_Menu = $.fn.zTree.getZTreeObj("treeDemo");
        zTree_Menu.expandAll(true);
        //curMenu = zTree_Menu.getNodes()[0].children[0].children[0];
        //zTree_Menu.selectNode(curMenu);
        treeObj.addClass("showIcon");
        //var node = zTree_Menu.getNodesByFilter(function (node) {
        //    return node.level == 0
        //}, true);
        //vm.typeId = node.id;
        //vm.typeName = node.name;
        //var w = node.name.indexOf("(");
        //vm.departmentName = node.name.substring(0, w);
        reloadPage(1);
        director();
    });
}

$(document).ready(function () {
    var treeObj = $("#treeDemo");
    $.fn.zTree.init(treeObj, setting, zNodes);
    zTree_Menu = $.fn.zTree.getZTreeObj("treeDemo");
    zTree_Menu.expandAll(true);
    //curMenu = zTree_Menu.getNodes()[0].children[0].children[0];
    //zTree_Menu.selectNode(curMenu);
    treeObj.addClass("showIcon");
    var node = zTree_Menu.getNodesByFilter(function (node) {
        return node.level == 0
    }, true);
    vm.typeId = node.id;
    vm.typeName = node.name;
    var w = node.name.indexOf("(");
    vm.departmentName = node.name.substring(0, w);
    reloadPage(1);
    director();
    //treeObj.hover(function () {
    //    if (!treeObj.hasClass("showIcon")) {
    //        treeObj.addClass("showIcon");
    //    }
    //}, function () {
    //    treeObj.removeClass("showIcon");
    //});
});





















