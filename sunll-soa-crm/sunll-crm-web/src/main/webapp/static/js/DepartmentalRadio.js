var token = sessionStorage.getItem("accountToken");
var vm = new Vue({
    el: "#content",
    data: {
        departmentStatus:"1",
        department: [],
        json: [1, 1, 1],
        peopleNum: [],
        peopleName: [],
        peopleId: [],
        pid: 1,
        departmentName: '',
        departmentId: null,
        defaultJson: {
            department: []
        },
    },
    methods: {
        cancel: function () {
            parent.layer.closeAll();
        },
        //Determine: function () {
        //    alert(vm.peopleNum)
        //    alert(vm.peopleName)
        //    alert(vm.peopleId)
        //},
        removeList: function (num, id) {
            this.peopleNum.splice(num, 1);
            this.peopleName.splice(num, 1);
            this.peopleId.splice(num, 1);
            var obj = document.getElementById(id)
            obj.checked = false;
        },
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
            vm.peopleId = [];
            vm.peopleName = [];
            vm.peopleNum = [];
            vm.peopleId.push(id);
            vm.peopleName.push(Txt);
            vm.peopleNum.push(obj);
        }
    }
})
window.onload = function () {
    if (document.readyState == "complete") {
        var arrColor = ["#fe641c", "#1cb1fe", "#1cfe66", "#fe1c71"];
        $("body").show();
        var sum = $(".am-breadcrumb li").eq(0).width();
        var _w = 0;
        //默认数组
        //vm.defaultJson = {
        //    department: [],
        //    person: []
        //};
        //有部门有人员

        //默认数据
        getData(vm.defaultJson);


        function getData(arr) {
            $(".department").html("");
            $(".person").html("");
            if (arr.department == "") {
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
                //var _index = $(this).index();
                var id = $(this).attr("id");
                vm.pid = id;
                var txt = $(this).text();
                $(".am-breadcrumb li").removeClass("am-active");
                var htm = '<li class="am-active" id="' + id + '">' + txt + '</li>';
                //if (_index == 0) {
                //    getData(resource);
                //} else if (_index == 1) {
                //    getData(all);
                //} else if (_index == 2) {
                listDepartmentByPid();
                getData(vm.defaultJson);
                //} else {
                //    getData(haveNo);
                //}
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
                    var id = $(this).attr("id");
                    vm.pid = id;
                    var _i = $(this).index();
                    $(this).nextAll().remove();
                    //if (_i == 0) {
                    listDepartmentByPid()
                    getData(vm.defaultJson);
                    $(".am-breadcrumb").css("min-width", "100%");

                    for (var i = 0; i < vm.peopleId.length; i++) {
                        var obj = document.getElementById(vm.peopleId[i]);
                        if (obj) {
                            obj.checked = true;
                        }
                    }
                    //} else {
                    //
                    //}
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
            var objAll = document.querySelectorAll(".z_container [type=checkbox]");
            for (var i = 0; i < objAll.length; i++) {
                objAll[i].checked = false;
            }
            document.getElementById(id).checked = true;
            vm.peopleId = [];
            vm.peopleName = [];
            vm.peopleNum = [];
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
        data: {"pid": 0, "accountToken": token},
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

listDepartmentByPid()
function listDepartmentByPid() {
    $.ajax({
        url: "/department/listDepartmentByPid",
        type: 'POST',
        async: false,
        data: {"pid": vm.pid, "accountToken": token},
        dataType: "json",
        traditional: true,
        error: function () {
            layer.msg("出错了！");
        },
        success: function (data) {
            if (data != null && data.length > 0) {
                vm.defaultJson.department = data;
                vm.departmentStatus = '1';
            } else {
                layer.msg("此部门下没有任何下级");
                vm.departmentStatus = '2';
            }
        }
    });
}
function listDepartmentPage() {
    $.ajax({
        url: "/department/listDepartmentPage?pageNumber=1&pageSize=5",
        type: 'POST',
        async: false,
        data: {"departmentName": $("#departmentName").val(), "accountToken": token},
        dataType: "json",
        traditional: true,
        error: function () {
            layer.msg("出错了！");
        },
        success: function (data) {
            vm.department = data.list;
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
