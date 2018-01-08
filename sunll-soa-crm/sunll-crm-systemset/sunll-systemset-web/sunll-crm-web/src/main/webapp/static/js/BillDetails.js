layui.use('laydate', function () {
    var laydate = layui.laydate;
    laydate.render({
        elem: '#test6'
        , range: true
    });
})
var vm = new Vue({
    el: "#content",
    data: {
        paging: [10, 20, 30, 40, 50],
        DisplayMost: 10,//最多显示
        common: 50,//共多少条
        Pages: 0,//共多少页
        Page: 1,//第几页
        enterMoney: [],
        json: [],
        chuRuZhang: 0,
        allamount: 0,
    },
    methods: {
        PreviousPage: function () {//上一页
            if (this.Page > 1) {
                this.Page--;
                vm.query();
            } else {
                alert("已经是第一页了");
            }
        },
        nextPage: function () {//下一页
            if (this.Page < this.Pages) {
                this.Page++;
                vm.query();
            } else {
                alert("已经是最后一页了");
            }
        },
        selectSize: function (e) {
            this.DisplayMost = $(e.target).val();
            vm.query();
        },
        selectchuRuZhang: function (e) {
            if ($(e.target).val() == 2) {
                vm.chuRuZhang = null;
            } else {
                vm.chuRuZhang = $(e.target).val();
            }
            vm.queryStatus();
        },
        jumpPage: function () {
            this.Page = $("#jumpPage").val();
            vm.query();
        },
        query: function () {
            var timeSelect = $("#test6").val().split(" - ");
            var timeSelectStart = timeSelect[0];//查询开始时间
            var timeSelectEnd = timeSelect[1];//查询结束时间
            var liuShuiHao = $("#liuShuiHao").val()//支付流水号
            if (sessionStorage.getItem("homePage_BillDetails_details") == "yesterday") {
                var s = vm.getDateStr(-1);
                timeSelectStart = s;//获取昨日时间
                timeSelectEnd = s;//获取昨日时间
                sessionStorage.removeItem("homePage_BillDetails_details");
            }
            reloadPage(this.Page, this.DisplayMost, timeSelectStart, timeSelectEnd, liuShuiHao);
        },
        Export: function () {
            var timeSelect = $("#test6").val().split(" - ");
            var timeSelectStart = timeSelect[0];//查询开始时间
            var timeSelectEnd = timeSelect[1];//查询结束时间
            $("input[name='timeSelectStart']").val(timeSelectStart);
            $("input[name='timeSelectEnd']").val(timeSelectEnd);
            $("input[name='liuShuiHao']").val($("#liuShuiHao").val());
            $("input[name='accountToken']").val(sessionStorage.getItem("accountToken"));
            $("input[name='status']").val($("#status").val());
            $("#exf").submit();
        },
        queryStatus: function () {
            $.ajax({
                url: "/bill/getEnterType",
                type: 'POST',
                async: false,
                data: {"accountToken": sessionStorage.getItem("accountToken"), "chuRuZhang": vm.chuRuZhang},
                dataType: "json",
                traditional: true,
                error: function () {
                },
                success: function (data) {
                    vm.enterMoney = data;
                }
            });
        },
        getDateStr:function (dayCount) {
            if (null == dayCount) {
                dayCount = 0;
            }
            var dd = new Date();
            dd.setDate(dd.getDate() + dayCount);//设置日期
            var y = dd.getFullYear();
            var m = dd.getMonth() + 1;//获取当前月份的日期
            var d = dd.getDate();
            return y + "-" + m + "-" + d;
        }
    }
})
vm.queryStatus();
vm.query();
//获取用户列表
function reloadPage(pageNumber, pageSize, timeSelectStart, timeSelectEnd, liuShuiHao) {
    var status = $("#status").val();
    //默认消费
    if (status == null || status == "") {
        status = 1;
    }
    $.ajax({
        url: "/bill/getListBillQuery",
        type: 'POST',
        async: false,
        data: {
            "pageNumber": pageNumber,
            "pageSize": pageSize,
            "timeSelectStart": timeSelectStart,
            "timeSelectEnd": timeSelectEnd,
            "liuShuiHao": liuShuiHao,
            "status": status
        },
        dataType: "json",
        traditional: true,
        error: function () {
        },
        success: function (data) {
            vm.common = data.total;
            vm.Page = data.pageNum;
            vm.Pages = data.pages;
            vm.json = data.list;
        }
    });
    $.ajax({
        url: "/bill/getListBillQuery",
        type: 'POST',
        async: false,
        data: {
            "pageNumber": pageNumber,
            "pageSize": pageSize,
            "timeSelectStart": timeSelectStart,
            "timeSelectEnd": timeSelectEnd,
            "liuShuiHao": liuShuiHao,
            "status": status
        },
        dataType: "json",
        traditional: true,
        error: function () {
        },
        success: function (data) {
            vm.common = data.pageInfo.total;
            vm.Page = data.pageInfo.pageNum;
            vm.Pages = data.pageInfo.pages;
            vm.json = data.pageInfo.list;
            vm.allamount = data.allAmount;
        }
    });
}