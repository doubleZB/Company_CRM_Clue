var token = sessionStorage.getItem("accountToken");
var vm = new Vue({
    el: "#content",
    data: {
        departmentStatus: '1',
        department: [],
        departmentName: "",
        departmentId: "",
        json: [1, 1, 1],
        peopleNum: [],
        peopleName: [],
        pid: 1,
        peopleId: sessionStorage.getItem("departmentIds").split(","),
        defaultJson: {
            department: []
        }
    },
    methods: {
        searchCont: function (e, id, txt) {
            $(".selectAll").hide();
            var Txt = txt;
            var id = id;
            var status = 1;
            var obj = {name: Txt, id: id, status: status};
            var objAll = document.querySelectorAll(".z_container [type=checkbox]");
            for (var i = 0; i < objAll.length; i++) {
                objAll[i].checked = false;
            }
            if (document.getElementById(id)) {
                document.getElementById(id).checked = true;
            }
            var flag = true;
            if (vm.peopleId.length > 0) {
                for (var i = 0; i < vm.peopleId.length; i++) {
                    if (vm.peopleId[i] == id) {
                        flag = false;
                        break;
                    }
                }
            } else {
                flag = true;
            }
            if (flag) {
                vm.peopleId.push(id);
                vm.peopleName.push(Txt);
                vm.peopleNum.push(obj);
            }
        },
        cancel: function () {
            parent.layer.closeAll();
        },
        Determine: function () {
            alert("确定");
        },
        removeList: function (num, id) {
            this.peopleNum.splice(num, 1);
            this.peopleName.splice(num, 1);
            this.peopleId.splice(num, 1);
            var obj = document.getElementById(id)
            obj.checked = false;
        }
    }
});
window.onload = function () {
    if (document.readyState == "complete") {
        var arrColor = ["#fe641c", "#1cb1fe", "#1cfe66", "#fe1c71"];
        $("body").show();
        var sum = $(".am-breadcrumb li").eq(0).width();
        var _w = 0;
        //默认数组
        //默认数据
        getData(vm.defaultJson);


        function getData(arr) {
            $(".department").html("");
            $(".person").html("");
            if (arr.department == "" && arr.person == "") {
                $(".remark").show();
            } else {
                $(".remark").hide();
                for (var i = 0; i < arr.department.length; i++) {
                    var _html = '<li class="parentNode addCont"><input type="checkbox" class="checkboxBtn checkboxBtnParent fr" id="' + arr.department[i].id + '" status="1"/><a href="javascript:;" class="fr" id="' + arr.department[i].id + '"><span class="z_fl lineHeight100 contTxt">' + arr.department[i].name + '</span><span class="z_fr"><i class="fa fa-angle-right fa-2x"></i></span></a></li>';
                    $(".department").append(_html);
                }
                //for (var i = 0; i < arr.person.length; i++) {
                //    var ColorIndex = parseInt(Math.random() * 4);
                //    var ColorName = arr.person[i].name.substr(0, 2);
                //    var _html = '<li class="addCont"><input type="checkbox" class="checkboxBtn fr findCheck" id="' + arr.person[i].id + '" status="2"/><a href="###" class="fr"><span class="fl contTxt" style="background:' + arrColor[ColorIndex] + '">' + ColorName + '</span><div class="fl"><p class="num_name">' + arr.person[i].name + '</p><p class="desc">' + arr.person[i].job + '</p></div></a></li>';
                //    $(".person").append(_html);
                //}
            }
            $(".department li:last").css("border", "0");
            //部门点击
            $(".department li a").click(function () {
                var id = $(this).attr("id");
                vm.pid = id;
                var _index = $(this).index();
                var txt = $(this).text();
                $(".am-breadcrumb li").removeClass("am-active");
                if (vm.defaultJson.department.length > 0) {
                    var htm = '<li class="am-active" id="' + id + '">' + txt + '</li>';
                }
                listDepartmentByPid();
                getData(vm.defaultJson);
                if (vm.departmentStatus != '2') {
                    $(".am-breadcrumb").append(htm);
                    for (var w = 0; w < $(".am-breadcrumb li").length; w++) {
                        var _width = $(".am-breadcrumb li").eq(w).width();
                    }
                    sum += _width;
                    // $(".am-breadcrumb").css("min-width", sum + 10);
                }
                for (var i = 0; i < vm.peopleId.length; i++) {
                    var obj = document.getElementById(vm.peopleId[i]);
                    if (obj) {
                        obj.checked = true;
                    }
                }
                //导航点击
                $(".am-breadcrumb li").click(function () {
                    var id = $(this).attr("id");
                    vm.pid = id;
                    var _i = $(this).index();
                    $(this).nextAll().remove();
                    listDepartmentByPid()
                    getData(vm.defaultJson);

                    $(".am-breadcrumb").css("min-width", "100%");
                    for (var i = 0; i < vm.peopleId.length; i++) {
                        var obj = document.getElementById(vm.peopleId[i]);
                        if (obj) {
                            obj.checked = true;
                        }
                    }
                });
            });
        }

        for (var i = 0; i < vm.peopleId.length; i++) {
            var obj = document.getElementById(vm.peopleId[i]);
            if (obj) {
                obj.checked = true;
            }
        }
        var peopleList;
        if (sessionStorage.getItem("departmentNames") == "全公司") {
            peopleList = ["全公司"];
        }
        else if (sessionStorage.getItem("departmentNames") == "") {
            peopleList = [];
        }
        else {
            peopleList = sessionStorage.getItem("departmentNames").split(",")
        }
        for (var i = 0; i < peopleList.length; i++) {
            if (peopleList[0] == "全公司") {
                vm.peopleNum.push({name: "全公司", id: 0});
                $("input[type='checkbox']").prop("checked", true);
                $("input[type='checkbox']").attr("disabled", true);
                $(".checkedAll").removeAttr("disabled");
            } else {
                vm.peopleNum.push({name: peopleList[i], id: vm.peopleId[i]});
            }

        }
    }

    $(document).on("click", ".checkboxBtn", function (e) {
        e.stopPropagation();
        var Txt = $(e.target).parents(".addCont").find(".contTxt").text();
        var id = $(e.target).attr("id");
        var status = $(e.target).attr("status");
        if ($(e.target).is(":checked")) {
            var obj = {name: Txt, id: id, status: status};
            for (var i = 0; i < vm.peopleId.length; i++) {
                if (id == vm.peopleId[i]) {
                    return;
                }
            }
            vm.peopleId.push(id);
            vm.peopleName.push(Txt);
            vm.peopleNum.push(obj);
        } else {
            var _index;
            for (var i = 0; i < vm.peopleId.length; i++) {
                if (id == vm.peopleId[i]) {
                    _index = i;
                }
            }
            vm.peopleNum.splice(_index, 1);
            vm.peopleName.splice(_index, 1);
            vm.peopleId.splice(_index, 1);
        }
    })
};

selectDepartment();
function selectDepartment() {
    var data = {
        "data": {
            "pid": 0,
            "companyId": sessionStorage.getItem("CRM_COMPANY_TOKEN")
            // "companyId": 1
        },
        "version": "V1.0"
    };
    var layerIndex;
    $.ajax({
        url: "http://192.168.6.151:8080/interfaceTrans/selectDepartment",
        type: 'POST',
        async: false,
        data: JSON.stringify(data),
        dataType: "json",
        contentType: "application/json",
        error: function () {
            layer.msg("出错了！");
        },
        beforeSend: function () {
            layerIndex = layer.load(1, {
                shade: [0.3, '#fff'] //0.1透明度的白色背景
            });
        },
        complete: function () {
            layer.close(layerIndex);
        },
        success: function (data) {
            vm.departmentName = data.data.department.name;
            vm.departmentId = data.data.department.id;
            vm.pid = data.data.department.id;
            console.log(vm.pid);
        }
    });
}

listDepartmentByPid();
function listDepartmentByPid() {
    var data = {
        "data": {
            "pid": vm.pid,
            "companyId": sessionStorage.getItem("CRM_COMPANY_TOKEN")
            //"companyId": 1
        },
        "version": "V1.0"
    };
    var layerIndex;
    $.ajax({
        url: "http://192.168.6.151:8080/interfaceTrans/listDepartmentByPid",
        type: 'POST',
        async: false,
        data: JSON.stringify(data),
        dataType: "json",
        contentType: "application/json",
        error: function () {
            layer.msg("出错了！");
        },
        beforeSend: function () {
            layerIndex = layer.load(1, {
                shade: [0.3, '#fff'] //0.1透明度的白色背景
            });
        },
        complete: function () {
            layer.close(layerIndex);
        },
        success: function (data) {
            if (data != null && data.data.departmentList.length > 0) {
                vm.defaultJson.department = data.data.departmentList;
                vm.departmentStatus = '1';
            } else {
                layer.msg("此部门下没有任何下级!");
                vm.departmentStatus = '2';
            }
        }
    });
}

function listDepartmentPage() {
    var data = {
        "data": {
            "companyId": sessionStorage.getItem("CRM_COMPANY_TOKEN"),
            //   "companyId": 1,
            "departmentName": $("#departmentName").val(),
            "pageNumber": 1,
            "pageSize": 5
        },
        "version": "V1.0"
    };
    var layerIndex;
    $.ajax({
        url: "http://192.168.6.151:8080/interfaceTrans/listDepartmentPage",
        type: 'POST',
        async: false,
        data: JSON.stringify(data),
        dataType: "json",
        contentType: "application/json",
        error: function () {
            layer.msg("出错了！");
        },
        beforeSend: function () {
            layerIndex = layer.load(1, {
                shade: [0.3, '#fff'] //0.1透明度的白色背景
            });
        },
        complete: function () {
            layer.close(layerIndex);
        },
        success: function (data) {
            vm.department = data.data.pageInfo.list;
        }
    });
}

$(".search").bind("input propertychange", function () {
    if ($(this).val().length > 0) {
        listDepartmentPage();
        $(".selectAll").show();
    } else {
        $(".selectAll").hide();
    }
});

$(".checkedAll").click(function () {
    if ($(this).prop("checked") == true) {
        vm.peopleNum = [];
        vm.peopleId = [];
        vm.peopleName = [];
        $("input[type='checkbox']").prop("checked", true);
        $("input[type='checkbox']").attr("disabled", true);
        $(".checkedAll").removeAttr("disabled");
        vm.peopleNum.push({name: "全公司", id: 0});
        vm.peopleId.push(0);
        vm.peopleName.push("全公司");
    } else {
        $("input[type='checkbox']").removeAttr("disabled");
        vm.peopleNum = [];
        vm.peopleId = [];
        vm.peopleName = [];
        $("input[type='checkbox']").prop("checked", false);
    }
});