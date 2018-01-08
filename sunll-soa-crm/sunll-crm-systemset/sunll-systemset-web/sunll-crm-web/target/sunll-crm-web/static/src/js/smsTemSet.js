var app = new Vue({
    el: '#BusinessType',
    data: {
        smsTemList: [],
        typeName: [
            {names: '请选择归属部门'},
            {names: '修改归属部门'},
            {names: '修改归属部门'}
        ],
        addOrEditTitle: '新增短信模板',
        addOrEditBtnType: 1
    },
    methods: {
        //添加编辑按钮
        addOrEditBtn: function (type,smsTemId) {
            if (type == 1) {
                this.addOrEditTitle = '新增短信模板';
                this.addOrEditBtnType = 1;
            } else {
                this.addOrEditTitle = '编辑短信模板';
                this.addOrEditBtnType = 2;
                this.getSmsTemById(smsTemId);
            }
            $('.AddTape').css('right', '4px');
        },
        //添加编辑中的取消按钮
        cancelAddOrEditBtn: function () {
            $('.AddTape').css('right', '-550px');
            $("#smsTemName").val("");
            $("#smsTemId").val("");
            $("#smsTemSignature").val("");
            $("#smsTemContent").val("");
            $("#smsTemTest").val("");
        },
        //获取所有自定义的业务
        getSmsTemList: function () {
            var that = this;
            var data = {
                "data": {
                    "companyId": sessionStorage.getItem("CRM_COMPANY_TOKEN")
                },
                "version": "V1.0"
            };
            var layerIndex;
            $.ajax({
                url: "/smsTemSet/selectSmsTemList",
                type: 'POST',
                async: false,
                data: JSON.stringify(data),
                dataType: "json",
                contentType: "application/json",
                error: function () {
                    layer.msg("获取业务数据出错");
                },
                beforeSend: function () {
                    layerIndex = layer.load(1, {
                        shade: [0.3,'#fff'] //0.1透明度的白色背景
                    });
                },
                complete: function () {
                    layer.close(layerIndex);
                },
                success: function (data) {

                    if (data.code == 20000) {
                        that.smsTemList = data.data;
                    } else {
                        layer.msg(data.message);
                    }
                }
            });
        },
        //新建或者编辑按钮
        savleAddOrEditBtn: function () {
            var that = this;
            var smsTemName = $("#smsTemName").val();
            var smsTemId = $("#smsTemId").val();
            var smsTemSignature = $("#smsTemSignature").val();
            var smsTemContent = $("#smsTemContent").val();
            var smsTemTest = $("#smsTemTest").val();
            //业务名称非空验证
            if (smsTemName.trim() == ''){
                layer.msg("模板名称不能为空");
                return false;
            }
            if (smsTemSignature.trim() == ''){
                layer.msg("短信签名不能为空");
                return false;
            }
            if (smsTemContent.trim() == ''){
                layer.msg("短信内容不能为空");
                return false;
            }
            if (smsTemTest.trim() == ''){
                layer.msg("测试手机号不能为空");
                return false;
            }
            //根据业务名称以及企业id查询业务名称是否存在
            var data = {
                "data": {
                    "companyId": sessionStorage.getItem("CRM_COMPANY_TOKEN"),
                    "smsTemName": smsTemName,
                    "smsTemId": smsTemId
                },
                "version": "V1.0"
            };
            var layerIndex;
            $.ajax({
                url: "/smsTemSet/isExistSmsTemName",
                type: 'POST',
                async: false,
                data: JSON.stringify(data),
                dataType: "json",
                contentType: "application/json",
                error: function () {
                    layer.msg("数据出错");
                },
                beforeSend: function () {
                    layerIndex = layer.load(1, {
                        shade: [0.3,'#fff'] //0.1透明度的白色背景
                    });
                },
                complete: function () {
                    layer.close(layerIndex);
                },
                success: function (data) {
                    if (data.code == 30000) {
                        layer.msg("模板名称已经存在");
                    } else {
                        that.addOrEditSmsTem();
                    }
                }
            });
        },
        addOrEditSmsTem: function () {
            var that = this;
            var data;
            var url;
            var smsTemName = $("#smsTemName").val();
            var smsTemId = $("#smsTemId").val();
            var smsTemSignature = $("#smsTemSignature").val();
            var smsTemContent = $("#smsTemContent").val();
            var smsTemTest = $("#smsTemTest").val();
            if (that.addOrEditBtnType == 1) {
                //新建数据
                data = {
                    "data": {
                        "smsTem": {
                            "name": smsTemName,
                            "content": smsTemContent,
                            "signature": smsTemSignature,
                            "createUserId": sessionStorage.getItem("USER_TOKEN"),
                            "companyId": sessionStorage.getItem("CRM_COMPANY_TOKEN"),
                            "testPhone": smsTemTest
                        }
                    },
                    "version": "V1.0"
                };
                url = "/smsTemSet/insertSmsTem";
            } else {
                //编辑数据
                data = {
                    "data": {
                        "smsTem": {
                            "id": smsTemId,
                            "name": smsTemName,
                            "content": smsTemContent,
                            "signature": smsTemSignature,
                            "updateUserId": sessionStorage.getItem("USER_TOKEN"),
                            "companyId": sessionStorage.getItem("CRM_COMPANY_TOKEN"),
                            "testPhone": smsTemTest
                        }
                    },
                    "version": "V1.0"
                };
                url = "/smsTemSet/updateSmsTem";
            }
            var layerIndex;
            $.ajax({
                url: url,
                type: 'POST',
                async: false,
                data: JSON.stringify(data),
                dataType: "json",
                contentType: "application/json",
                error: function () {
                    layer.msg("数据出错");
                },
                beforeSend: function () {
                    layerIndex = layer.load(1, {
                        shade: [0.3,'#fff'] //0.1透明度的白色背景
                    });
                },
                complete: function () {
                    layer.close(layerIndex);
                },
                success: function (data) {
                    if (data.code == 20000) {
                        that.getSmsTemList();
                        that.cancelAddOrEditBtn();
                        layer.msg("保存成功");
                    } else {
                        layer.msg(data.message);
                    }
                }
            });
        },
        getSmsTemById: function (smsTemId) {
            var that = this;
            var data = {
                "data": {
                    "smsTemId": smsTemId
                },
                "version": "V1.0"
            };
            var layerIndex;
            $.ajax({
                url: "/smsTemSet/getSmsTemById",
                type: 'POST',
                async: false,
                data: JSON.stringify(data),
                dataType: "json",
                contentType: "application/json",
                error: function () {
                    layer.msg("数据出错");
                },
                beforeSend: function () {
                    layerIndex = layer.load(1, {
                        shade: [0.3,'#fff'] //0.1透明度的白色背景
                    });
                },
                complete: function () {
                    layer.close(layerIndex);
                },
                success: function (data) {
                    if (data.code == 20000) {
                        $("#smsTemName").val(data.data.name);
                        $("#smsTemId").val(data.data.id);
                        $("#smsTemSignature").val(data.data.signature);
                        $("#smsTemContent").val(data.data.content);
                    } else {
                        $("#smsTemName").val("");
                        $("#smsTemId").val("");
                        $("#smsTemSignature").val("");
                        $("#smsTemContent").val("");
                        layer.msg(data.message);
                    }
                }
            });
        }
    }
});
app.getSmsTemList();