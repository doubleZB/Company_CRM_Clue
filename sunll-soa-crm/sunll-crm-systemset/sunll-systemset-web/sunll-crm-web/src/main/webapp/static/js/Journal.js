var token = sessionStorage.getItem("accountToken");
layui.use('laydate', function () {
    var laydate = layui.laydate;
    laydate.render({
        elem: '#test6'
        , range: true
    });
})

 //自定义filter名称为'time'
Vue.filter('time',
    <!-- value 格式为13位unix时间戳 -->
    <!-- 10位unix时间戳可通过value*1000转换为13位格式 -->
    function (value) {
        if (value != '' && value != null) {
            var date = new Date(value);
            Y = date.getFullYear(),
                m = date.getMonth() + 1,
                d = date.getDate(),
                H = date.getHours(),
                i = date.getMinutes(),
                s = date.getSeconds();
            if (m < 10) {
                m = '0' + m;
            }
            if (d < 10) {
                d = '0' + d;
            }
            if (H < 10) {
                H = '0' + H;
            }
            if (i < 10) {
                i = '0' + i;
            }
            if (s < 10) {
                s = '0' + s;
            }
            <!-- 获取时间格式 2017-01-03 10:13:48 -->
            var t = Y + '-' + m + '-' + d + ' ' + H + ':' + i + ':' + s;
            <!-- 获取时间格式 2017-01-03 -->
            //var t = Y + '-' + m + '-' + d;
            return t;
        } else {
            return '';
        }
    }
);
var vm = new Vue({
    el: "#content",
    data: {
        paging: [10, 20, 30, 40, 50],
        DisplayMost: 10,//最多显示
        common: 50,//共多少条
        Page: 1,//第几页
        pages: 1,//共几页
        searchStatus: 2,//搜索标识
        json: [],
    },
    methods: {
        serchLogs: function () {
            vm.operatorName = $("#operatorName").val()
            var dateStr = $("#test6").val();
            if (dateStr != null && dateStr != '') {
                var strs = dateStr.split(" - ");
                $("#startTimeShow").val(strs[0]);
                $("#endTimeShow").val(strs[1]);
            }
            reloadPage(1);
        },
        changePageSize: function (pageSize) {
            vm.DisplayMost = pageSize;
            reloadPage(1);
        },
        skipPageNumber: function () {
            reloadPage($("#pageNumber").val());
        },
        StaffManagement: function () {
            window.location.href = "skipOrganizational";
        },
        PreviousPage: function () {//上一页
            if (this.Page > 1) {
                this.Page--;
                reloadPage(this.Page);
            } else {
                alert("已经是第一页了");
            }
        },
        nextPage: function () {//下一页
            if (this.Page < this.pages) {
                this.Page++;
                reloadPage(this.Page);
            } else {
                alert("已经是最后一页了");
            }
        },
    }
})
reloadPage(1)
//获取日志列表
function reloadPage(pageNumber) {
    if (pageNumber == 0) {
        layer.msg("当前是第一页啦，没有上一页！");
        return;
    }
//        var userNameToFind = $("#userNameToFind").val();
//        var userDepartmentId = $("#userDepartmentId").val();
    var index = layer.load(0, {shade: [0.3, '#000']});
    $.ajax({
        url: "/logs/listLogs?pageNumber=" + pageNumber + "&pageSize=" + vm.DisplayMost,
        type: 'POST',
        async: false,
        data: {
            "accountToken": token,
            "operationObject": $("#operationObject").val(),
            "operatorName": vm.operatorName,
            "startTimeShow": $("#startTimeShow").val(),
            "endTimeShow": $("#endTimeShow").val()
        },
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