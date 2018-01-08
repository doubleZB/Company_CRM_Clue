var vm = new Vue({
    el: "#container",
    data: {
        pageSizeField: [10, 20, 30, 40, 50],
        startTime: "",
        endTime: "",
        pageNum: 1,
        pageSize: 10,
        pages: 0,//共多少页
        total: 50,
        checkedDepartmentId: "",
        checkedUserId: "",
        tableHead: [
            {name: '发信人'},
            {name: '发送条数'},
            {name: '到达条数'},
            {name: '费用统计（元）'},
        ],
        operate: [
            {
                "id": 0,
                "name": "",
                "sendNumber": "",
                "arrivalNumber": "",
                "totalExpenses": ""
            },
        ],
        allTotal: [
            {totalExpenses: ''},
            {sendNumber: ''},
            {arrivalNumber: ''},
        ],
    },
    created: function () {
        //执行一个laydate实例
        let self = this;
        lay('#version').html('-v' + laydate.v);
        laydate.render({
            elem: `#date` //指定元素
            , done: function (value, date) {
                self.datte = value;
                // this 是laydate中的指向，一般来说，jquery的插件在事件中，都会将this指向到当前dom（至少有一个属性是指向这个dom的）
                $(this.elem[0]).trigger('change');
            },
        });

    },
    methods: {
        lastPage: function () {//上一页
            if (vm.pageNum > 1) {
                vm.pageNum--;
                vm.reload();
            } else {
                layer.msg("已经是第一页了",{time:2000});
            }
        },
        nextPage: function () {//下一页
            if (vm.pageNum < vm.pages) {
                vm.pageNum++;
                vm.reload();
            } else {
                layer.msg("已经是最后一页了",{time:2000});
            }
        },
        jumpPage: function () {
            if ($("#jumpPage").val() <= vm.pages) {
                vm.pageNum = $("#jumpPage").val();
                vm.reload();
            } else {
                layer.msg("没有此页",{time:2000});
            }
        },
        checkedPageSize: function (e) {
            vm.pageSize = $(e.target).val();
            vm.reload();
        },
        reload: function () {
            var date = $("#date").val();
            console.log("部门id：" + vm.checkedDepartmentId + "用户id：" + vm.checkedUserId + "pageNum:" + vm.pageNum + "pageSize:" + vm.pageSize);
            $.ajax({
                url: "/message/selectMessageUserTogether",
                type: 'POST',
                async: false,
                data: {
                    "CRM_TOKEN": sessionStorage.getItem("CRM_TOKEN"),
                    "startTime": date,
                    "endTime": date,
                    "PageNum": vm.pageNum,
                    "PageSize": vm.pageSize,
                    "checkedDepartmentId": vm.checkedDepartmentId,
                    "checkedUserId": vm.checkedUserId
                },
                dataType: "json",
                error: function () {
                },
                success: function (data) {
                    console.log(data);
                    vm.operate = data.list;
                    vm.total = data.total;
                    vm.pages = data.pages;
                    vm.pageNum = data.pageNum;
                    $.ajax({
                        url: "/message/selectMessageUserTogetherTotal",
                        type: 'POST',
                        async: false,
                        data: {
                            "CRM_TOKEN": sessionStorage.getItem("CRM_TOKEN"),
                            "startTime": date,
                            "endTime": date,
                            "PageNum": vm.pageNum,
                            "PageSize": vm.pageSize,
                            "checkedDepartmentId": vm.checkedDepartmentId,
                            "checkedUserId": vm.checkedUserId
                        },
                        dataType: "json",
                        error: function () {
                        },
                        success: function (data) {
                            console.log(data);
                            vm.allTotal = data;
                            //layer.close(index);
                        }
                    });
                }
            });
        },
        query: function () {
            vm.reload();
        },
        //选择部门
        sendOther: function () {
            var sendOther = layer.open({
                type: 2,
                title: false,
                closeBtn: 0,
                area: ['70%', '85%'],
                content: "/jspSkip/selectDepAndUser",
                anim: 1, btn: ['确定', '取消'],
                yes: function (index, layero) {
                    var iframeWin = window[layero.find('iframe')[0]['name']];
                    if (iframeWin.vm.peopleId != null && iframeWin.vm.peopleId != '') {
                        var length = iframeWin.vm.peopleId.length;
                        var userIds = [];
                        var departmentIds = [];
                        for (var i = 0; i < iframeWin.vm.peopleNum.length; i++) {
                            if (iframeWin.vm.peopleNum[i].id[0] == "d") {
                                var length = iframeWin.vm.peopleNum[i].id.length
                                departmentIds.push(iframeWin.vm.peopleNum[i].id.substring(1, length + 1));
                            } else {
                                userIds.push(iframeWin.vm.peopleNum[i].id)
                            }
                        }
                        if (departmentIds[0] != null) {
                            vm.checkedDepartmentId = departmentIds[0];
                            vm.checkedUserId = null;
                            console.log("部门id：" + vm.checkedDepartmentId);
                        } else {
                            vm.checkedDepartmentId = null;
                            vm.checkedUserId = userIds[0];
                            console.log("用户id：" + vm.checkedUserId);
                        }
                        $("#departmentName").text(iframeWin.vm.peopleName);
                        layer.close(sendOther);
                    }
                },
                //取消
                btn2: function (index, layero) {
                }
            })
        },
        exportExcel: function () {
            var date = $("#date").val();
            console.log("部门id：" + vm.checkedDepartmentId + "用户id：" + vm.checkedUserId + "pageNum:" + vm.pageNum +
                "pageSize:" + vm.pageSize);
            $("input[name='CRM_TOKEN']").val(sessionStorage.getItem("CRM_TOKEN"));
            $("input[name='startTime']").val(date);
            $("input[name='endTime']").val(date);
            $("input[name='PageNum']").val(vm.pageNum);
            $("input[name='PageSize']").val(vm.pageSize);
            $("input[name='checkedDepartmentId']").val(vm.checkedDepartmentId);
            $("input[name='checkedUserId']").val(vm.checkedUserId);
            $("#exf").submit();
        }
    }
});
vm.reload();
