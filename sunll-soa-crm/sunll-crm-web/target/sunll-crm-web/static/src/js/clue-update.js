layui.use(['form', 'layedit', 'laydate', 'laypage', 'layer'], function() {
    var form = layui.form,
        layer = layui.layer,
        layedit = layui.layedit,
        laypage = layui.laypage,
        laydate = layui.laydate;
})

var vm = new Vue({
    el: "#container",
    data: {
        datte: null,
        flog:true,
        rowList: []
    },
    created: function () {
        let self = this;
        var sendData = {
            "data": {"businessTypeId":parseInt(sessionStorage.getItem("BUSINESS_TYPE_ID_UPDATE")), "pid": 0, "clueId": sessionStorage.getItem("CLUE_ID_UPDATE")},
            "version": "V1.0"
        };
        console.log(sessionStorage.getItem("BUSINESS_TYPE_ID_UPDATE"));
        console.log(sessionStorage.getItem("CLUE_ID_UPDATE"));
        $.ajax({
            url: "/clue/clueDetail",
            data: JSON.stringify(sendData),
            type: "POST",
            contentType: "application/json",
            async: false,
            dataType: "JSON",
            error: function () {
                alert("获取业务数据出错");
            },
            success: function (data) {
                console.log("获取业务数据")
                console.log(data.data)
                if (data.code == 20000) {
                    self.rowList = data.data;
                } else {
                    alert(data.message);
                }
            }
        });
        //lay('#version').html('-v' + laydate.v);
        let dateList = [];
        self.rowList.map((row) => {
            row.businessTypeFieldList.map((col) => {
                if (col.fieldType == 'date') {
                    layui.use('laydate', function () {
                        var laydate = layui.laydate;

                        //执行一个laydate实例
                        laydate.render({
                            elem: `#date${col.id}` //指定元素
                            ,type: 'datetime'
                        });
                    });
                    //laydate.render({
                    //    elem: `#date${col.id}` //指定元素
                    //    , done: function (value, date) {
                    //        self.datte = value;
                    //        $(this.elem[0]).trigger('change');
                    //    },
                    //});
                }
            });
        });
    },
    methods: {
        back: function () {
            window.history.go(-1);
        },
        //跳转页面
        iframeHref: function (url) {
            if (url) {
                window.location.href = url;
                // $("#iframe").attr("src", url);
            }
        },
        //更新线索
        updateClue: function () {

            let rowList = this.rowList;
            var listMap = [];
            var flag = true;
            var off = true;

            rowList.map((row) => {
                row.businessTypeFieldList.map((col) => {
                    var fieldAndvalue = {fieldId: "", value: ""};
                    fieldAndvalue.fieldId = col.id;
                    fieldAndvalue.value = col.value;
                    listMap.push(fieldAndvalue);
                    if (!col.value && col.isRequired == 1) {
                        Vue.set(col, 'error', 'ERROR');
                        flag = false;
                        return;
                    } else {
                        col.error = false
                    }
                    if (col.fieldAlias == 'phone') {

                        if (!(/^1[3|4|5|7|8][0-9]\d{4,8}$/.test(parseInt(col.value)))) {
                            layer.msg("请输入正确的手机号");
                            flag = false;
                            //return 3;
                        }
                    }
                    //if (col.fieldAlias == 'fixedTel') {
                    //
                    //    if (!(/^0\d{2,3}-[1-9]\d{6,7}$/.test(col.value))) {
                    //        layer.msg("请输入正确的固定电话号");
                    //        flag = false;
                    //        //return 3;
                    //    }
                    //}
                });
            });
            var data = {
                "version": "V1.0",
                "data": {
                    "businessTypeId": parseInt(sessionStorage.getItem("BUSINESS_TYPE_ID_UPDATE")),
                    "userId": parseInt(sessionStorage.getItem("USER_TOKEN")),
                    "clueId": sessionStorage.getItem("CLUE_ID_UPDATE"),
                    "listMap": listMap
                }
            };
            console.log("data数据");
            console.log(data);
            console.log(flag,vm.flog)

            if (!(/^1[3|4|5|7|8][0-9]\d{8}$/.test(parseInt($(".ce1").val())))) {
                layer.msg("请输入正确的手机号");
                flag = false;
                return false;
                //return 3;
            }
            console.log($(".ce1").val())
            if (flag) {
                if (vm.flog) {

                    $.ajax({
                        url: "/clue/updateEditClue",
                        data: JSON.stringify(data),
                        type: "POST",
                        async: false,
                        dataType: "json",
                        contentType: "application/json",

                        success: function (data) {
                            if (data.code == 20000) {

                                layer.msg("更新线索成功");
                                vm.flog = false;
                                setTimeout(function () {
                                    location.reload();
                                }, 2000)
                            } else {
                                layer.msg("更新线索失败")
                            }
                        }
                    })
                }
            } else {
                layer.msg("请填写必填项");
            }
        },
        regPhone: function (e) {
            //alert(e)
            if (!(/^1[3|4|5|7|8][0-9]\d{4,8}$/.test(parseInt(e)))) {
                //off = false;
                //flag = false;
                layer.msg("请输入正确的手机号");
            }
        },
        regTelphone: function (e) {
        //    //alert(e)
        //    if (!(/^0\d{2,3}-[1-9]\d{6,7}$/.test(e))) {
        //        //off = false;
        //        //flag = false;
        //        layer.msg("请输入正确的固定电话号");
        //    }

            console.log(1);
        },
    }
});

