


function detilTo(a) {

    if ($('.editShow').css('right') == '-550px') {
        $('.editShow').css('right', '4px')
    }
}

// 点击取消
$('.btn-default').click(function () {
    $('.editShow').css('right', '-550px')
})


var app = new Vue({
    el: '#customFields',
    data: {
        Tablis:[
            {currency: 'Foo'},
            {currency: 'Bar'}
        ],
        todos:[
            {name:'线索',showName:'线索',zt:'是'},
            {name:'客户',showName:'客户',zt:'是'}



        ]
    }
})