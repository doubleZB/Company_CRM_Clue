var app = new Vue({
    el: '#content',
    data: {
        businessTypeId: $("#businessTypeId").val(),
        Tablis: [
            {currency: 'Foo'},
            {currency: 'Bar'}
        ],
        todos: [],
        fieldType: 0,//字段类型
        editType: 1,//编辑字段时显示的类型
        editfieldName: "",//编辑字段时显示的区块
        editFindfield: "",//编辑字段时显示的字段
        list: [],
        //多选下拉添加字段
        selectList: ["手机", "电话", "固定电话"],
        addOrEditBlockTitle: '添加区块',
        addOrEditBtnBlockType: 1,
        selectTxt: [1],
        checkboxTxt: [1],
        num: 2,
        fieldInfo: {},
        enterPrompt: '',
        editfieldId: 0,
        itemTypeList: [
            {name: "请选择", value: "0"},
            {name: "单选下拉框", value: "select"},
            {name: "多选下拉框", value: "checkbox"},
            {name: "单行文字", value: "input"},
            {name: "多行文字", value: "text"}
        ],
        isReadonly:false,
    },
    ready: function () {

    },

    methods: {
        //返回上一页
        back: function () {
            window.history.go(-1);
        },
        //编辑添加
        editAdd: function () {
            var that = this;
            that.num = that.num + 1;
            this.list.push(that.num);
        },
        //编辑删除
        editDel: function (num) {
            this.list.splice(num, 1);
        },
        //编辑保存
        editBaocun: function () {
            /*var arr=[];
             $(".editTxt").each(function (x) {
             arr.push({name:$(".editTxt").eq(x).val(),id:$(".editTxt").attr("id")});
             });
             console.log(arr);
             */

            var fieldPid = this.editfieldId;
            var fieldType = this.editFindfield;
            if (fieldType == "0") {
                layer.msg("请选择字段类型");
                return false;
            }
            var showName = $("#editShowName").val();
            if (showName == '') {
                layer.msg("请输入显示名称");
                return false;
            }
            var arr = [];
            var flag = false;
            if (this.editFindfield == 'select') {

                $(".selectEditTxt").each(function (x) {
                    if ($(".selectEditTxt").eq(x).val() == '') {
                        flag = true;
                        return false;
                    } else {
                        arr.push({name: $(".selectEditTxt").eq(x).val(), value: x + 1, isEnabled: 1, isDel: 1});
                    }

                });

            } else if (this.editFindfield == 'checkbox') {
                $(".checkboxEditTxt").each(function (x) {
                    if ($(".checkboxEditTxt").eq(x).val() == '') {
                        flag = true;
                        return false;
                    } else {
                        arr.push({name: $(".checkboxEditTxt").eq(x).val(), value: x + 1, isEnabled: 1, isDel: 1});
                    }
                });
            }
            if (flag) {
                layer.msg("筛选框值不能为空");
                return false;
            }
            var data;
            if (this.editFindfield == 'select' || this.editFindfield == 'checkbox') {
                data = {
                    "data": {
                        "businessTypeField": {
                            "id": this.fieldInfo.id,
                            "fieldShowName": showName,
                            "fieldType": this.editFindfield,
                            "pid": fieldPid,
                            "updateUserId": sessionStorage.getItem("USER_TOKEN"),
                            "businessTypeFieldValueChooseList": arr
                        }
                    },
                    "version": "V1.0"
                };
            } else if (this.editFindfield == 'input') {
                data = {
                    "data": {
                        "businessTypeField": {
                            "id": this.fieldInfo.id,
                            "fieldShowName": showName,
                            "fieldType": this.editFindfield,
                            "pid": fieldPid,
                            "updateUserId": sessionStorage.getItem("USER_TOKEN"),
                            "enterPrompt": $("#inputEditText").val()
                        }
                    },
                    "version": "V1.0"
                };
            } else if (this.editFindfield == 'text') {
                data = {
                    "data": {
                        "businessTypeField": {
                            "id": this.fieldInfo.id,
                            "fieldShowName": showName,
                            "fieldType": this.editFindfield,
                            "pid": fieldPid,
                            "updateUserId": sessionStorage.getItem("USER_TOKEN"),
                            "enterPrompt": $("#textEditText").val()
                        }
                    },
                    "version": "V1.0"
                };
            }

            var that = this;
            var layerIndex;
            $.ajax({
                url: "/BusinessTypeFieldSet/insertOrEditBlock",
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
                        shade: [0.3, '#fff'] //0.1透明度的白色背景
                    });
                },
                complete: function () {
                    layer.close(layerIndex);
                },
                success: function (data) {
                    if (data.code == 20000) {
                        that.getBusinessTypeFieldByBusinessTypeId();
                        that.cancel();
                        layer.msg("保存成功");
                    } else {
                        layer.msg(data.message);
                    }
                }
            });


        },
        //添加筛选项
        Add: function (i) {
            var that = this;
            that.num = that.num + 1;
            if (i == 1) {
                that.selectTxt.push(that.num);
            } else if (i == 2) {
                that.checkboxTxt.push(that.num);
            }
        },
        //删除筛选项
        del: function (num, i) {
            var that = this;
            if (num == 1) {
                that.selectTxt.splice(i, 1);
            } else if (num == 2) {
                that.checkboxTxt.splice(i, 1);
            }
        },
        //多选下拉字段操作
        delSelectList: function (i) {
            this.selectList.splice(i, 1);
        },
        //添加字段
        addField: function () {
            $('.addFieldTxt').css('right', '4px');
        },
        //编辑
        detilTo: function (editfieldId, fieldName, fieldInfo, e) {
            //
            $("#editShowName").val(fieldInfo.fieldShowName);
            this.list = fieldInfo.businessTypeFieldValueChooseList;
            this.checkboxTxt = fieldInfo.businessTypeFieldValueChooseList;
            //editType   判断编辑的是什么类型的选择框
            //list   编辑时 显示的值
            this.editfieldId = editfieldId;
            this.fieldInfo = fieldInfo;
            this.editfieldName = fieldName;
            //判断是否可以编辑
            if (this.fieldInfo.isModify == 1) {
                $("#editBlock").attr("disabled", "disabled");
                $("#editFieldType").attr("disabled", "disabled");
            }
            if (fieldInfo.fieldType == 'input') {
                this.editFindfield = 'input';
                this.enterPrompt = fieldInfo.enterPrompt;
            }
            else if (fieldInfo.fieldType == 'select') {
                this.editFindfield = 'select';
                this.list = fieldInfo.businessTypeFieldValueChooseList;
            }
            else if (fieldInfo.fieldType == 'checkbox') {
                this.editFindfield = 'checkbox';
                this.list = fieldInfo.businessTypeFieldValueChooseList;
            }
            else if (fieldInfo.fieldType == 'text') {
                this.editFindfield = 'text';
                this.enterPrompt = fieldInfo.enterPrompt;
            }
            $('.editFieldTxt').css('right', '4px');
        },
        //取消
        cancel: function () {
            $('.editFieldTxt').css('right', '-102%');
            $('.addFieldTxt').css('right', '-102%');
            $(".blockCont").css('right', '-102%');
            $("#blockName").val("");
            $("#blockId").val("");
            this.editFindfield = 0;
            this.editfieldId = 0;
            this.fieldType = 0;
            $("#showName").val("");
            /*$("#editShowName").val("");
             $("#textEditText").val("");
             $("#inputEditText").val("");*/
            $("#inputText").val("");
            $("#textText").val("");
            this.fieldInfo = [];
            $("#editBlock").attr("disabled", false);
            $("#editFieldType").attr("disabled", false);
            this.list = [];
            this.getBusinessTypeFieldByBusinessTypeId();
        },
        //添加区块
        addOrEditBlock: function (type, id) {
            if (type == 1) {
                this.addOrEditTitle = '添加区块';
                this.addOrEditBtnType = 1;
            } else {
                this.addOrEditTitle = '编辑区块';
                this.addOrEditBtnType = 2;
            }
            $(".blockCont").css('right', '4px');
        },
        //根据businessTypeId 的值获取类型字段对应的数据
        getBusinessTypeFieldByBusinessTypeId: function () {
            var that = this;
            var data = {
                "data": {
                    "businessTypeId": parseInt(this.businessTypeId),
                    "pid": 0
                },
                "version": "V1.0"
            };
            var layerIndex;
            $.ajax({
                url: "/BusinessTypeFieldSet/selectByBusinessTypeIdAndPid",
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
                        shade: [0.3, '#fff'] //0.1透明度的白色背景
                    });
                },
                complete: function () {
                    layer.close(layerIndex);
                },
                success: function (data) {
                    if (data.code == 20000) {
                        that.todos = data.data;
                        setTimeout(function () {
                            a();
                        }, 0)
                        setTimeout(function () {
                            $(".taBb").eq(0).find(".tabSortOne").find("tr").eq(0).find("input").attr("disabled",true).addClass("biTan");
                            $(".taBb").eq(0).find(".tabSortOne").find("tr").eq(1).find("input").attr("disabled",true).addClass("biTan");
                        }, 1)
                    } else {
                        layer.msg(data.message);
                    }

                }
            });
        },
        //是否启用该功能
        isEnabled: function (id, e) {
            var that = this;
            var isEnabledStat;
            var layerIndex;
            if ($(e.target).is(":checked")) {
                isEnabledStat = 1;
            } else {
                isEnabledStat = 2;
                let data = {
                    "data": {
                        "businessTypeFieldId": id,
                        "isRequired": 2,
                        "updateUserId": 1
                    },
                    "version": "V1.0"
                };
                var layerIndex;
                $.ajax({
                    url: "/BusinessTypeFieldSet/isRequiredBusinessTypeField",
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
                            shade: [0.3, '#fff'] //0.1透明度的白色背景
                        });
                    },
                    complete: function () {
                        layer.close(layerIndex);
                    },
                    success: function (data) {
                        if (data.code == 20000) {
                            that.getBusinessTypeFieldByBusinessTypeId();
                        } else {
                            layer.msg(data.message);
                        }
                    }
                });
            }

            var data = {
                "data": {
                    "businessTypeFieldId": id,
                    "isEnabled": isEnabledStat,
                    "updateUserId": sessionStorage.getItem("USER_TOKEN")
                },
                "version": "V1.0"
            };

            $.ajax({
                url: "/BusinessTypeFieldSet/isEnabledBusinessTypeField",
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
                        shade: [0.3, '#fff'] //0.1透明度的白色背景
                    });
                },
                complete: function () {
                    layer.close(layerIndex);
                },
                success: function (data) {
                    if (data.code == 20000) {
                        that.getBusinessTypeFieldByBusinessTypeId();
                    } else {
                        layer.msg(data.message);
                    }
                }
            });
        },
        //是否必填
        isRequired: function (id, e) {
            var that = this;
            var layerIndex;
            var isRequiredStat;
            if ($(e.target).is(":checked")) {
                isRequiredStat = 1;
                var data = {
                    "data": {
                        "businessTypeFieldId": id,
                        "isEnabled": 1,
                        "updateUserId": sessionStorage.getItem("USER_TOKEN")
                    },
                    "version": "V1.0"
                };

                $.ajax({
                    url: "/BusinessTypeFieldSet/isEnabledBusinessTypeField",
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
                            shade: [0.3, '#fff'] //0.1透明度的白色背景
                        });
                    },
                    complete: function () {
                        layer.close(layerIndex);
                    },
                    success: function (data) {
                        if (data.code == 20000) {
                            that.getBusinessTypeFieldByBusinessTypeId();
                        } else {
                            layer.msg(data.message);
                        }
                    }
                });
            } else {
                isRequiredStat = 2;
            }
            var data = {
                "data": {
                    "businessTypeFieldId": id,
                    "isRequired": isRequiredStat,
                    "updateUserId": 1
                },
                "version": "V1.0"
            };

            $.ajax({
                url: "/BusinessTypeFieldSet/isRequiredBusinessTypeField",
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
                        shade: [0.3, '#fff'] //0.1透明度的白色背景
                    });
                },
                complete: function () {
                    layer.close(layerIndex);
                },
                success: function (data) {
                    if (data.code == 20000) {
                        that.getBusinessTypeFieldByBusinessTypeId();
                    } else {
                        layer.msg(data.message);
                    }
                }
            });
        },
        //保存区块按钮
        saveBlockButton: function () {
            var blockName = $("#blockName").val();
            if (blockName == '') {
                layer.msg("区块名称不能为空");
                return false;
            }
            var businessTypeFieldId = $("#blockId").val();
            this.isExistBlock(businessTypeFieldId, parseInt(this.businessTypeId), blockName);
        },

        isExistBlock: function (businessTypeFieldId, businessTypeId, blockName) {
            var that = this;
            var data = {
                "data": {
                    "businessTypeId": businessTypeId,
                    "blockName": blockName,
                    "businessTypeFieldId": businessTypeFieldId
                },
                "version": "V1.0"
            };
            var layerIndex;
            $.ajax({
                url: "/BusinessTypeFieldSet/isExistBlock",
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
                        shade: [0.3, '#fff'] //0.1透明度的白色背景
                    });
                },
                complete: function () {
                    layer.close(layerIndex);
                },
                success: function (data) {
                    if (data.code == 30000) {
                        layer.msg("业务类型已经存在");
                    } else {
                        that.insertOrEditBlock();
                    }
                }
            });
        },
        insertOrEditBlock: function () {
            var that = this;
            var blockId = $("#blockId").val();
            var data;
            var url;
            var blockName = $("#blockName").val();
            if (that.addOrEditBtnType == 1) {
                //新建数据
                data = {
                    "data": {
                        "businessTypeField": {
                            "fieldName": blockName,
                            "fieldType": "div",
                            "isEnabled": 1,
                            "isRequired": 1,
                            "pid": 0,
                            "businessTypeId": parseInt(this.businessTypeId),
                            "createUserId": sessionStorage.getItem("USER_TOKEN"),
                            "isModify": 2
                        }
                    },
                    "version": "V1.0"
                };
            } else {
                //编辑数据
                data = {
                    "data": {
                        "businessTypeField": {
                            "id": blockId,
                            "fieldName": blockName,
                            "updateUserId": sessionStorage.getItem("USER_TOKEN")
                        }
                    },
                    "version": "V1.0"
                };
            }
            var layerIndex;
            $.ajax({
                url: "/BusinessTypeFieldSet/insertOrEditBlock",
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
                        shade: [0.3, '#fff'] //0.1透明度的白色背景
                    });
                },
                complete: function () {
                    layer.close(layerIndex);
                },
                success: function (data) {
                    if (data.code == 20000) {
                        $("#blockName").val("");
                        $("#blockId").val("");
                        that.getBusinessTypeFieldByBusinessTypeId();
                        that.cancel();
                        layer.msg("保存成功");
                    } else {
                        layer.msg(data.message);
                    }
                }
            });
        },
        //添加字段
        addSaveField: function () {
            var fieldPid = $("#fieldPid option:selected").val();
            var fieldType = this.fieldType;
            if (fieldType == "0") {
                layer.msg("请选择字段类型");
                return false;
            }
            var showName = $("#showName").val();
            if (showName == '') {
                layer.msg("请输入显示名称");
                return false;
            }
            var arr = [];
            var flag = false;
            if (this.fieldType == 'select') {

                $(".selectDTxt").each(function (x) {
                    if ($(".selectDTxt").eq(x).val() == '') {
                        flag = true;
                        return false;
                    } else {
                        arr.push({name: $(".selectDTxt").eq(x).val(), value: x + 1, isEnabled: 1, isDel: 1});
                    }

                });

            } else if (this.fieldType == 'checkbox') {
                $(".checkboxDTxt").each(function (x) {
                    if ($(".checkboxDTxt").eq(x).val() == '') {
                        flag = true;
                        return false;
                    } else {
                        arr.push({name: $(".checkboxDTxt").eq(x).val(), value: x + 1, isEnabled: 1, isDel: 1});
                    }
                });
            }
            if (flag) {
                layer.msg("筛选框值不能为空");
                return false;
            }

            var data;
            if (this.fieldType == 'select' || this.fieldType == 'checkbox') {
                data = {
                    "data": {
                        "businessTypeField": {
                            "fieldName": showName,
                            "fieldShowName": showName,
                            "fieldType": this.fieldType,
                            "isEnabled": 1,
                            "isRequired": 1,
                            "pid": fieldPid,
                            "businessTypeId": parseInt(this.businessTypeId),
                            "isDel": 1,
                            "createUserId": sessionStorage.getItem("USER_TOKEN"),
                            "isModify": 2,
                            "businessTypeFieldValueChooseList": arr
                        }
                    },
                    "version": "V1.0"
                };
            } else if (this.fieldType == 'input') {
                data = {
                    "data": {
                        "businessTypeField": {
                            "fieldName": showName,
                            "fieldShowName": showName,
                            "fieldType": this.fieldType,
                            "isEnabled": 1,
                            "isRequired": 1,
                            "pid": fieldPid,
                            "businessTypeId": parseInt(this.businessTypeId),
                            "isDel": 1,
                            "createUserId": sessionStorage.getItem("USER_TOKEN"),
                            "isModify": 2,
                            "enterPrompt": $("#inputText").val()
                        }
                    },
                    "version": "V1.0"
                };
            } else if (this.fieldType == 'text') {
                data = {
                    "data": {
                        "businessTypeField": {
                            "fieldName": showName,
                            "fieldShowName": showName,
                            "fieldType": this.fieldType,
                            "isEnabled": 1,
                            "isRequired": 1,
                            "pid": fieldPid,
                            "businessTypeId": parseInt(this.businessTypeId),
                            "isDel": 1,
                            "createUserId": sessionStorage.getItem("USER_TOKEN"),
                            "isModify": 2,
                            "enterPrompt": $("#textText").val()
                        }
                    },
                    "version": "V1.0"
                };
            }

            var that = this;
            var layerIndex;
            $.ajax({
                url: "/BusinessTypeFieldSet/insertOrEditBlock",
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
                        shade: [0.3, '#fff'] //0.1透明度的白色背景
                    });
                },
                complete: function () {
                    layer.close(layerIndex);
                },
                success: function (data) {
                    if (data.code == 20000) {
                        that.getBusinessTypeFieldByBusinessTypeId();
                        that.cancel();
                        layer.msg("保存成功");
                    } else {
                        layer.msg(data.message);
                    }
                }
            });

        }
    }
});

app.getBusinessTypeFieldByBusinessTypeId();

var fixHelperModified = function (e, tr) {
        var $originals = tr.children();
        var $helper = tr.clone();
        $helper.children().each(function (index) {
            $(this).width($originals.eq(index).width())
        });
        return $helper;
    },
    updateIndex = function (e, ui) {
        $('td .index', ui.item.parent()).each(function (i) {
            $(this).html(i + 1);
        });
    };
$(".tabSortOne").sortable({
    containment: "parent",
    disabled: false,
    helper: fixHelperModified,
    stop: updateIndex
}).disableSelection();
$(".sort").mousedown(function () {
    $(".tabSortOne").sortable({
        disabled: false,
        containment: "parent",
        helper: fixHelperModified,
        stop: updateIndex,
    }).disableSelection().bind('sortupdate', function () {
        $(".tabSortOne").sortable({
            disabled: true,
            containment: "parent",
            helper: fixHelperModified,
            stop: updateIndex,
        }).disableSelection();
    });
});

function a() {
    $("input[name='checkbox']").each(function () {
        if ($(this).val() == 1) {
            $(this).prop("checked", true);
        }
    });
}