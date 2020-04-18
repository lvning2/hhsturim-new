
function initPlaceTable(){
    layui.use('table', function () {
        var table = layui.table;

        $.ajax({
            url: "/place/getAllPlace",
            type: 'get',
            success: function (data) {
                if (data.code === 0) {
                    table.render({
                        elem: '#test'
                        , cols: [[
                            {field: 'id', width: '3%', title: 'id'}
                            ,{field: 'title', width: '12%', title: '主题'}
                            ,{field: 'details', width: '30%', title: '详细信息'}
                            , {field: 'type', width: '5%', title: '类型', sort: true} //minWidth：局部定义当前单元格的最小宽度，layui 2.2.1 新增
                            , {field: 'username', width: '5%', title: '发布人' }
                            , {field: 'phone', width: '10%', title: '电话'}
                            , {field: 'createTime', width: '13%', title: '发布时间',sort: true}
                            , {fixed: 'right',  align:'center', toolbar: '#barDemo'}
                        ]]
                        ,page:true
                        ,data:data.data
                    });
                }
            }
        })

    });

        //

}       // 初始化函数结束



initPlaceTable();


    layui.use('table', function () {
        var table = layui.table;
        table.on('tool(test)', function (obj) {
            var data = obj.data;
            if (obj.event === 'del') {
                layer.confirm('真的删除么', function (index) {

                    $.ajax({
                        url: "/place/deletePlaceById",
                        data: {
                            'id': data.id,
                        },
                        type: 'post',
                        success: function (data) {
                            if (data.code == 0) {
                                obj.del();
                                layer.close(index);
                            }
                        }
                    })

                });
            } else if (obj.event === 'edit') {
                // layer.alert('编辑行：<br>'+ JSON.stringify(data))


                layer.open({
                    type: 2 //此处以iframe举例
                    , title: data.username
                    , area: ['70%', '70%']
                    , shade: 0
                    , maxmin: true
                    // 用户名 姓名 年龄 电话 身份证 地址
                    , content: ['/console/placeInfo.html?id=' + data.id, 'no']
                    , btn: ['确定', '取消'] //只是为了演示
                    , btn1: function (layero, index) {
                        var openPage = window[index.find('iframe')[0]['name']];
                        openPage.update();
                        initPlaceTable();
                        layer.closeAll();

                    }
                    , btn2: function () {
                        layer.closeAll();
                    }
                    , success: function (layero) {

                    }
                });
            }


        });

    })

    //



