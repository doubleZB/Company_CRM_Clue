// [...document.querySelectorAll('.navList>div')].find((x,y)=>{
//     x['onclick'] =() =>{
//     [...document.querySelectorAll('.content>div')].find(d=>{
//     d.style.display ='none';
// });
// [...document.querySelectorAll('.line')].find(d=>{
//     d.style.display ='none';
// });
// [...document.querySelectorAll('.navList>div')].find(d=>{
//     d.style.color ='#bfbfbf';
// });
// [...document.querySelectorAll('.navList>div')][y].style.color = '#5caaf2';
// [...document.querySelectorAll('.line')][y].style.display = 'block';
// [...document.querySelectorAll('.content>div')][y].style.display = 'block';
// };
// });


// 点击取消
/*
$('.btn-default').click(function () {
    $('.editShow').css('right', '-550px')
});
*/


var app = new Vue({
    el: '#customFields',
    data: {
        active: 0,
        dataList: []
    },
    methods: {
        toggle: function (index) {
            this.active = index;
        },
        //获取所有自定义的业务以及业务类型
        getBusinessAndBusinessTypeList: function () {
            var that = this;
            var data = {
                "data": {
                    "companyId": sessionStorage.getItem("CRM_COMPANY_TOKEN")
                },
                "version": "V1.0"
            };
            var index;
            $.ajax({
                url: "/businessType/selectBusinessAndBusinessType",
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
                        that.dataList = data.data;
                        setTimeout(function(){
                            $(".navList").css("width",that.dataList.length*100+"px");
                        },0)
                    } else {
                        layer.msg(data.message);
                    }
                }
            });
        },
        //是否启用该业务字段按钮
        isEnabledButton: function (id, name, isEnabled) {
            var that = this;
            var prompt;
            if (isEnabled == 1) {
                prompt = "确定启用" + name;
            } else {
                prompt = "确定禁用" + name;
            }
            var index = layer.confirm(prompt, {
                btn: ['确定', '取消'] //按钮
            }, function () {
                that.isEnabled(id, isEnabled);
            }, function () {
                layer.close(index);
            });
        },
        //是否启用该业务字段
        isEnabled: function (id, isEnabled) {
            var that = this;
            var data = {
                "data": {
                    "businessTypeId": id,
                    "isEnabled": isEnabled
                },
                "version": "V1.0"
            };
            var layerIndex;
            $.ajax({
                url: "/businessType/isEnabledBusinessType",
                type: 'POST',
                async: false,
                data: JSON.stringify(data),
                dataType: "json",
                contentType: "application/json",
                error: function () {
                    layer.msg("服务器出错");
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
                        that.getBusinessAndBusinessTypeList();
                        layer.msg("操作成功");
                    } else {
                        layer.msg(data.message);
                    }
                }
            });
        },
        //编辑按钮
        editButton: function (id,name,showName) {
                $('#businessTypeId').val(id);
                $('#businessName').text(name);
                $('#businessShowName').val(showName);
                $('.editShow').css('right', '4px')
        },
        //编辑取消按钮
        editCancelButton: function () {
            $('.editShow').css('right', '-550px');
            $('#businessTypeId').val("");
            $('#businessName').text("");
            $('#businessShowName').val("");
        },
        editSavleButton: function () {
            var id = $('#businessTypeId').val();
            var showName = $('#businessShowName').val();
            if (showName == ''){
                layer.msg("显示名称不能为空");
                return false;
            }
            var data = {
                "data": {
                    "businessType": {
                        "id": id,
                        "showName": showName
                    }
                },
                "version": "V1.0"
            };
            this.editSavle(data);
        },
        editSavle: function (data) {
            var that = this;
            var layerIndex;
            $.ajax({
                url: "/businessType/editBusinessType",
                type: 'POST',
                async: false,
                data: JSON.stringify(data),
                dataType: "json",
                contentType: "application/json",
                error: function () {
                    layer.msg("服务器出错");
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
                        that.getBusinessAndBusinessTypeList();
                        layer.msg("操作成功");
                        $('.editShow').css('right', '-550px');
                    } else {
                        layer.msg(data.message);
                    }
                }
            });
        },
        businessTypeFieldSet: function (businessTypeId) {
            window.location.href='/BusinessTypeFieldSet/skipBusinessTypeFieldSetPage?businessTypeId='+businessTypeId;
        }
    }

});
app.getBusinessAndBusinessTypeList();