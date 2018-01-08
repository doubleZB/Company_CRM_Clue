var app = new Vue({
    el: '#BusinessType',
    data: {
        departmentList: [],
        departmentNames: [],
        departmentIds: [],
        TypeName: '业务类型',
        department: '归属部门',
        Codizu: '操作',
        TypeList: [],
        typeName: [],
        addOrEditTitle: '添加类型',
        addOrEditBtnType: 1,
        peopleId: '',
        businessInfo:{}
    },
    methods: {
        delSpan:function(i){
            this.departmentList.splice(i,1);
            this.departmentIds.splice(i,1);
            this.departmentNames.splice(i,1);
        },
        //编辑成员：部门多选
        skipDepartmentMultipleByUpdateUser: function () {
            sessionStorage.setItem("departmentIds",app.departmentIds);
            sessionStorage.setItem("departmentList",app.departmentList);
            sessionStorage.setItem("departmentNames",app.departmentNames);
            var adjustmentDepartment = layer.open({
                type: 2,
                title: false,
                closeBtn: 0,
                area: ['70%', '82%'],
                content: "/business/skipDepartmentMultiple",
                anim: 1,
                btn: ['确定', '取消'],
                yes: function (index, layero) {
                    app.departmentIds = [];
                    app.departmentList = [];
                    app.departmentNames = [];
                    var iframeWin = window[layero.find('iframe')[0]['name']];
                    console.log(iframeWin.vm.peopleId)
                    if (iframeWin.vm.peopleId != null && iframeWin.vm.peopleId != '') {
                        for (var i = 0; i < iframeWin.vm.peopleNum.length; i++) {
                            var department1 = {id: "", name: ""};
                            app.departmentIds.push(parseInt(iframeWin.vm.peopleNum[i].id));
                            department1.id = iframeWin.vm.peopleNum[i].id;

                            app.departmentNames.push(iframeWin.vm.peopleNum[i].name);
                            department1.name = iframeWin.vm.peopleNum[i].name;
                            app.departmentList.push(department1);
                        }
                        layer.close(adjustmentDepartment);
                    } else {
                        layer.msg("请选择部门！");
                    }
                },
                btn2: function (index, layero) {
                    sessionStorage.removeItem("departmentIds");
                    sessionStorage.removeItem("departmentList");
                    sessionStorage.removeItem("departmentNames");
                }
            })
        }
        ,
        //添加编辑按钮
        addOrEditBtn: function (type, businessId) {
            if (type == 1) {
                this.addOrEditTitle = '添加类型';
                this.addOrEditBtnType = 1;
            } else {
                this.addOrEditTitle = '编辑类型';
                this.addOrEditBtnType = 2;
                this.getBusinessById(businessId);
            }
            $('.AddTape').css('right', '4px');
        },
        //添加编辑中的取消按钮
        cancelAddOrEditBtn: function () {
            $('.AddTape').css('right', '-550px');
            $("#businessName").val("");
            $("#businessId").val("");
            this.departmentList = [];
            this.departmentNames = [];
            this.departmentIds = [];
        },
        //获取所有自定义的业务
        getBusinessList: function () {
            var that = this;
            var data = {
                "data": {
                    "companyId": sessionStorage.getItem("CRM_COMPANY_TOKEN")
                },
                "version": "V1.0"
            };
            var index;
            $.ajax({
                url: "/business/selectBusiness",
                type: 'POST',
                async: false,
                data: JSON.stringify(data),
                dataType: "json",
                contentType: "application/json",
                error: function () {
                    layer.msg("获取业务数据出错");
                },
                beforeSend: function () {
                    index = layer.load(1, {
                        shade: [0.3,'#fff'] //0.1透明度的白色背景
                    });
                },
                complete: function () {
                    layer.close(index);
                },
                success: function (data) {
                    if (data.code == 20000) {
                        that.TypeList = data.data;
                    } else {
                        layer.msg(data.message);
                    }
                }
            });
        },
        //新建或者编辑按钮
        savleAddOrEditBtn: function () {
            var that = this;
            var businessName = $("#businessName").val();
            var businessId = $("#businessId").val();
            //业务名称非空验证
            if (businessName.trim() == '') {
                layer.msg("业务类型不能为空");
                return false;
            }
            if (that.departmentIds.length == 0 ) {
                layer.msg("请选择归属部门");
                return false;
            }
            //根据业务名称以及企业id查询业务名称是否存在
            var data = {
                "data": {
                    "companyId": sessionStorage.getItem("CRM_COMPANY_TOKEN"),
                    "businessName": businessName,
                    "businessId": businessId
                },
                "version": "V1.0"
            };
            var index;
            $.ajax({
                url: "/business/isExistBusinessName",
                type: 'POST',
                async: false,
                data: JSON.stringify(data),
                dataType: "json",
                contentType: "application/json",
                error: function () {
                    layer.msg("数据出错");
                },
                beforeSend: function () {
                    index = layer.load(1, {
                        shade: [0.3,'#fff'] //0.1透明度的白色背景
                    });
                },
                complete: function () {
                    layer.close(index);
                },
                success: function (data) {
                    if (data.code == 30000) {
                        layer.msg("业务类型已经存在");
                    } else {
                        that.addOrEditBusiness();
                    }
                }
            });
        },
        addOrEditBusiness: function () {
            var that = this;
            var businessId = $("#businessId").val();
            var data;
            var url;
            var businessName = $("#businessName").val();
            if (that.addOrEditBtnType == 1) {
                //新建数据
                data = {
                    "data": {
                        "business": {
                            "name": businessName,
                            "showName": businessName,
                            "isEnabled": 1,
                            "industryType": 1,
                            "createUserId": sessionStorage.getItem("USER_TOKEN"),
                            "companyId": sessionStorage.getItem("CRM_COMPANY_TOKEN")
                        },
                        "organizationList": that.departmentIds
                    },
                    "version": "V1.0"
                };
                url = "/business/insertBusiness";
            } else {
                //编辑数据
                data = {
                    "data": {
                        "business": {
                            "id": businessId,
                            "name": businessName,
                            "showName": businessName,
                            "isEnabled": 1,
                            "industryType": 1,
                            "createUserId": sessionStorage.getItem("USER_TOKEN"),
                            "companyId": sessionStorage.getItem("CRM_COMPANY_TOKEN")
                        },
                        "organizationList": that.departmentIds
                    },
                    "version": "V1.0"
                };
                url = "/business/updateBusiness";
            }
            var index;
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
                    index = layer.load(1, {
                        shade: [0.3,'#fff'] //0.1透明度的白色背景
                    });
                },
                complete: function () {
                    layer.close(index);
                },
                success: function (data) {
                    if (data.code == 20000) {
                        that.getBusinessList();
                        $("#businessName").val("");
                        $("#businessId").val("");
                        that.departmentIds = [];
                        that.cancelAddOrEditBtn();
                        layer.msg("保存成功");
                    } else {
                        layer.msg(data.message);
                    }
                }
            });
        },
        getBusinessById: function (businessId) {
            var index;
            var that = this;
            var data = {
                "data": {
                    "businessId": businessId
                },
                "version": "V1.0"
            };
            $.ajax({
                url: "/business/getBusinessById",
                type: 'POST',
                async: false,
                data: JSON.stringify(data),
                dataType: "json",
                contentType: "application/json",
                error: function () {
                    layer.msg("数据出错");
                },
                beforeSend: function () {
                    index = layer.load(1, {
                        shade: [0.3,'#fff'] //0.1透明度的白色背景
                    });
                },
                complete: function () {
                    layer.close(index);
                },
                success: function (data) {
                    console.log(data.data);
                    if (data.code == 20000) {
                        $("#businessName").val(data.data.name);
                        $("#businessId").val(data.data.id);
                        if (data.data.orgNames != null && data.data.orgIds != null){
                            that.departmentNames = data.data.orgNames;
                            that.departmentIds = data.data.orgIds;
                            for (var i=0;i<that.departmentIds.length;i++){
                                var department1 = {id: "", name: ""};
                                department1.id = that.departmentIds[i];
                                department1.name = that.departmentNames[i];
                                that.departmentList.push(department1);
                            }
                        }

                    } else {
                        $("#businessName").val("");
                        $("#businessId").val("");
                        layer.msg(data.message);
                    }
                }
            });
        }
    }
});
app.getBusinessList();