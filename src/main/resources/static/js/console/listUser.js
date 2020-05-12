






function initUserTable() {      // 初始化表格函数开始
    layui.use('table',function () {
      var  table = layui.table;

        $.ajax({
            url: "/user/getAllUser" ,
            type: 'get',
            success: function (data) {
                if (data.code === 0) {
                    table.render({
                        elem: '#test'
                        , url: "/user/getAllUser?"+new Date()
                        , cols: [[
                            {type:'numbers'}
                            ,{field: 'id', width: '1%', title: 'id',hide:true}
                            , {field: 'username', width: '7%', title: '用户名'}
                            , {field: 'name', width: '7%', title: '姓名'}
                            , {field: 'age', width: '7%', title: '年龄'}
                            , {field: 'idCard', width: '15%', title: '身份证'}
                            , {field: 'phone', width: '10%', title: '电话'}
                            , {field: 'address', width: '11%', title: '地址'}
                            , {field: 'lastLoginTime', width: '10%', title: '最后登录时间', sort: true}
                            , {field: 'lastLoginIp', width: '10%', title: '最后登录IP', sort: true}
                            , {field: 'enable', title: '是否锁定', width: 110, templet: '#checkboxTpl', event: 'setEnable', unresize: true}
                            , {fixed: 'right', align: 'center', toolbar: '#barDemo', title: '操作'}
                        ]]
                        , page: true
                        ,data:data.data
                    });

                }
            }
        })




        //监听锁定操作
        layui.form.on('checkbox(lockDemo)', function(obj){
            //var dd=JSON.stringify(obj.);

            //alert(this.value+" a "+this.name + " b " + obj.elem.checked)
            $.ajax({
                url: "/user/setEnable" ,
                data:{
                    "uid":this.value,
                    "enable":obj.elem.checked===true?0:1
                },
                type: 'post',
                success: function (data) {
                    // if (data.code === 0) {
                    //     info.id = id;
                    //     info.username = data.data.username;
                    //     info.title = data.data.title;
                    //     info.phone = data.data.phone;
                    //     info.details = data.data.details;
                    //     info.type = parseInt(data.data.type);
                    //     t.d=data.data.type;
                    //     info.price = data.data.price;
                    // }
                }
            })


        });

    })

}       // 初始化表格函数结束

 initUserTable();

layui.use('table', function () {
    var table = layui.table;

    // 删除和编辑
    table.on('tool(test)', function(obj){
        var data = obj.data;
        if(obj.event === 'del'){
            layer.confirm('真的删除么', function(index){

                $.ajax({
                    url:"/user/deleteUserById",
                    data:{
                        'id':data.id,
                    },
                    type:'post',
                    success:function(data){
                        if (data.code==0){
                            obj.del();
                            layer.close(index);
                        }
                    }
                })

            });
        } else if(obj.event === 'edit'){
            layer.open({
                type: 2 //此处以iframe举例
                ,title: data.username
                ,area: ['70%', '85%']
                ,shade: 0
                ,maxmin: true
                // 用户名 姓名 年龄 电话 身份证 地址
                ,content: ['/console/userInfo.html?id='+data.id, 'no']
                ,btn: ['确定', '取消'] //只是为了演示
                ,btn1: function(layero,index){
                    var openPage = window[index.find('iframe')[0]['name']];
                    openPage.update();
                    initUserTable();
                    layer.closeAll();

                }
                ,btn2: function(){
                    layer.closeAll();
                }
                ,success: function(layero){

                }
            });
        }

    });
})




