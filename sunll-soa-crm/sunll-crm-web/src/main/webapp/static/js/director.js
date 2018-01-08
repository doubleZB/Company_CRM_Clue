var token = sessionStorage.getItem("accountToken");
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
                    var _html = '<li class="addCont"><input type="checkbox" class="checkboxBtn fr findCheck" id="'+ arr.person[i].id + '" status="2"/><a href="###" class="fr"><span class="fl contTxt" style="background:' + arrColor[ColorIndex] + '">' + ColorName + '</span><div class="fl"><p class="num_name">' + arr.person[i].name + '</p><p class="desc">' + arr.person[i].job + '</p></div></a></li>';
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


selectDepartment()
function selectDepartment() {
    $.ajax({
        url: "/department/selectDepartment",
        type: 'POST',
        async: false,
        data: {"pid": 0,"accountToken":token},
        dataType: "json",
        traditional: true,
        error: function () {
            layer.msg("出错了！");
        },
        success: function (data) {
            vm.departmentName = data.name;
            vm.departmentId = data.id;
            vm.pid = data.id;
        }
    });
}

selectDepartmentAndUser()
function selectDepartmentAndUser() {
    $.ajax({
        url: "/department/selectDepartmentAndUser",
        type: 'POST',
        async: false,
        data: {"departmentId": vm.pid},
        dataType: "json",
        traditional: true,
        error: function () {
            layer.msg("出错了！");
        },
        success: function (data) {
            if (data.departments.length == 0 && data.userList.length == 0) {
                layer.msg("此部门下没有用户和部门！");
                vm.departmentStatus = '2';
        }else{
                vm.defaultJson.department = data.departments;
                vm.defaultJson.person = data.userList;
                vm.departmentStatus = '1';
            }
        }
    });
}

function listUserPage() {
    $.ajax({
        url: "/user/listUserPage?pageNumber=1&pageSize=5",
        type: 'POST',
        async: false,
        data: {"name": $("#userName").val(), "accountToken": token},
        dataType: "json",
        traditional: true,
        error: function () {
            layer.msg("出错了！");
        },
        success: function (data) {
            vm.user = data.list;
        }
    });
}
$(".search").bind("input propertychange", function () {
    if ($(this).val().length > 0) {
        listUserPage()
        $(".selectAll").show();

    } else {
        $(".selectAll").hide();
    }
});
