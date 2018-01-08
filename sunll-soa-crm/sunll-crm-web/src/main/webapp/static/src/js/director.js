var vm = new Vue({
    el: "#content",
    data: {
        departmentStatus:'1',
        user:[],
        departmentName: "",
        departmentId: "",
        pid: 0,
        json: [1, 1, 1],
        peopleNum: [],
        peopleName: [],
        peopleId: [],
        defaultJson: {
            department: [],
            person: []
        },
    },
    methods: {
        searchCont: function (e, id, txt) {
            vm.peopleId=[];
            vm.peopleName=[];
            vm.peopleNum=[];
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
})
window.onload = function () {
    if (document.readyState == "complete") {
        var arrColor = ["#fe641c", "#1cb1fe", "#1cfe66", "#fe1c71"];
        $("body").show();
        var sum = $(".am-breadcrumb li").eq(0).width();
        var _w = 0;


        getData(vm.defaultJson);

        function getData(arr) {
            $(".department").html("");
            $(".person").html("");
            if (arr.department == "" && arr.person == "") {
                $(".remark").show();
            } else {
                $(".remark").hide();
                for (var i = 0; i < arr.department.length; i++) {
                    var _html = '<li class="parentNode addCont"><input type="checkbox" class="checkboxBtn checkboxBtnParent fr" id="d' + arr.department[i].id + '" status="1"/><a href="javascript:;" class="fr" id="' + arr.department[i].id + '"><span class="z_fl lineHeight100 contTxt">' + arr.department[i].name + '</span><span class="z_fr"><i class="fa fa-angle-right fa-2x"></i></span></a></li>';
                    $(".department").append(_html);
                }
                for (var i = 0; i < arr.person.length; i++) {
                    var ColorIndex = parseInt(Math.random() * 4);
                    var ColorName = arr.person[i].name.substr(0, 2);
                    var _html = '<li class="addCont"><input type="checkbox" class="checkboxBtn fr findCheck" id="'+ arr.person[i].id + '" status="2"/><a href="###" class="fr"><span class="fl" style="background:' + arrColor[ColorIndex] + '">' + ColorName + '</span><div class="fl"><p class="num_name contTxt">' + arr.person[i].name + '</p><p class="desc"></p></div></a></li>';
                    $(".person").append(_html);
                }
            }
            $(".department li:last").css("border", "0");
            //部门点击
            $(".department li a").click(function () {
                var id = $(this).attr("id");
                vm.pid = id;
                var _index = $(this).index();
                var txt = $(this).text();
                $(".am-breadcrumb li").removeClass("am-active");
                var htm = '<li class="am-active" id="' + id + '">' + txt + '</li>';
                selectDepartmentAndUser()

                getData(vm.defaultJson);

                if (vm.departmentStatus != '2') {
                    $(".am-breadcrumb").append(htm);
                    for (var w = 0; w < $(".am-breadcrumb li").length; w++) {
                        var _width = $(".am-breadcrumb li").eq(w).width();
                    }
                    sum += _width;
                    $(".am-breadcrumb").css("min-width", sum + 10);
                }
                for (var i = 0; i < vm.peopleId.length; i++) {
                    var obj = document.getElementById(vm.peopleId[i]);
                    if (obj) {
                        obj.checked = true;
                    }
                }
                //导航点击
                $(".am-breadcrumb li").click(function () {
                    if($(this).attr("id").substr(0,1)=="d"){
                        var id = $(this).attr("id").substr(1,$(this).attr("id").length-1);
                    }else{
                        var id = $(this).attr("id");
                    }
                    vm.pid = id;
                    var _i = $(this).index();
                    $(this).nextAll().remove();

                    selectDepartmentAndUser()
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
    }
    ;

    $(document).on("click", ".checkboxBtn", function (e) {
        e.stopPropagation();
        var Txt = $(e.target).parents(".addCont").find(".contTxt").text();
        var id = $(e.target).attr("id");
        var status = $(e.target).attr("status");
        if ($(e.target).is(":checked")) {
            if(vm.peopleId.length>=1){
                var obj = {name: Txt, id: id, status: status};
                for (var i = 0; i < vm.peopleId.length; i++) {
                    if (id == vm.peopleId[i]) {
                        return;
                    }
                }
                $(".checkboxBtn").prop("checked",false);
                $("#"+id).prop("checked",true);
                vm.peopleId=[];
                vm.peopleName=[];
                vm.peopleNum=[];
                vm.peopleId.push(id);
                vm.peopleName.push(Txt);
                vm.peopleNum.push(obj);
            }else{
                var obj = {name: Txt, id: id, status: status};
                for (var i = 0; i < vm.peopleId.length; i++) {
                    if (id == vm.peopleId[i]) {
                        return;
                    }
                }
                vm.peopleId.push(id);
                vm.peopleName.push(Txt);
                vm.peopleNum.push(obj);
            }
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

aaa();
function aaa(){
    $.ajax({
        url: "/unitAccountInterface/getUnitAccountUrl",
        type: 'POST',
        async: false,
        data: {},
        dataType: "json",
        error: function () {
        },
        success: function (data) {
            sessionStorage.setItem("UNIT_ACCOUNT_BASE_URL", data.url);
            selectDepartment();
            selectDepartmentAndUser();
        }
    });
};

function selectDepartment() {
    var data = {
        "data": {
            "pid":0,
            "companyId": sessionStorage.getItem("CRM_COMPANY_TOKEN")
        },
        "version": "V1.0"
    };
    $.ajax({
        url: sessionStorage.getItem("UNIT_ACCOUNT_BASE_URL")+"/interfaceTrans/selectDepartment",
        type: 'POST',
        async: false,
        data: JSON.stringify(data),
        dataType: "json",
        contentType: "application/json",
        error: function () {
        },
        success: function (data) {
            vm.departmentName = data.data.department.name;
            vm.departmentId = data.data.department.id;
            vm.pid = data.data.department.id;
        }
    });
}

function selectDepartmentAndUser() {
    var data = {
        "data": {
            "departmentId": vm.pid
        },
        "version": "V1.0"
    };
    $.ajax({
        url: sessionStorage.getItem("UNIT_ACCOUNT_BASE_URL")+"/interfaceTrans/selectDepartmentAndUser",
        type: 'POST',
        async: false,
        data: JSON.stringify(data),
        dataType: "json",
        contentType: "application/json",
        error: function () {
        },
        success: function (data) {
            if (data.data.resultMap.departments.length == 0 && data.data.resultMap.userList.length == 0) {
                vm.departmentStatus = '2';
            }else{
                vm.defaultJson.department = data.data.resultMap.departments;
                vm.defaultJson.person = data.data.resultMap.userList;
                vm.departmentStatus = '1';
            }
        }
    });
}
function listUserPage() {
    var data = {
        "data": {
            "companyId": sessionStorage.getItem("CRM_COMPANY_TOKEN"),
            "name":$("#userName").val(),
            "pageNumber":1,
            "pageSize":5
        },
        "version": "V1.0"
    };
    $.ajax({
        url: sessionStorage.getItem("UNIT_ACCOUNT_BASE_URL")+"/interfaceTrans/listUserPage",
        type: 'POST',
        async: false,
        data: JSON.stringify(data),
        dataType: "json",
        contentType: "application/json",
        error: function () {
        },
        success: function (data) {
            console.log(data);
            if (data.code == 20000) {
                vm.user = data.data.list;
            } else {
                layer.msg(data.message);
            }
        }
    });
}

$(".search").bind("input propertychange", function () {
    if ($(this).val().length > 0) {
        //listUserPage();
        var data = {
            "data": {
                "companyId": sessionStorage.getItem("CRM_COMPANY_TOKEN"),
                "name":$("#userName").val(),
                "pageNumber":1,
                "pageSize":5
            },
            "version": "V1.0"
        };
        $.ajax({
            url: sessionStorage.getItem("UNIT_ACCOUNT_BASE_URL")+"/interfaceTrans/listUserPage",
            type: 'POST',
            async: false,
            data: JSON.stringify(data),
            dataType: "json",
            contentType: "application/json",
            error: function () {
            },
            success: function (data) {
                console.log(data);
                if (data.code == 20000) {
                    vm.user = data.data.pageInfo.list;
                } else {
                    layer.msg(data.message);
                }
            }
        });
        $(".selectAll").show();

    } else {
        $(".selectAll").hide();
    }
});
