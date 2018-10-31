$(function() {
    var listUrl = '/o2o/shopadmin/listawardsbyshop?pageIndex=1&pageSize=999';
    //设置奖品的可见状态
    var changeUrl = '/o2o/shopadmin/modifyaward';

    getList();

    function getList() {
        //访问后台，获取奖品列表
        $.getJSON(listUrl, function(data) {
            if (data.success) {
                var awardList = data.awardList;
                var tempHtml = '';
                /*
                * 遍历每条奖品数量,拼接成一行显示,列信息包括:
                * 奖品名称,积分,上架\下架(含awardId),编辑按钮(含awardId)
                * 预览(含awardId)
                * */
                awardList.map(function(item, index) {
                    var textOp = "下架";
                    var contraryStatus = 0;
                    if (item.enableStatus == 0) {
                        //若状态值为0,表明是已下架的商品,操作变为下架(即点击上架按钮相关商品)
                        textOp = "上架";
                        contraryStatus = 1;
                    } else {
                        contraryStatus = 0;
                    }
                    tempHtml += '' + '<div class="row row-award">'
                        + '<div class="col-30">' + item.awardName + '</div>'
                        + '<div class="col-20">' + item.point + '</div>'
                        + '<div class="col-50">'
                        + '<a href="#" class="edit" data-id="' + item.awardId + '" data-status="' + item.enableStatus + '">编辑</a>'
                        + '<a href="#" class="delete" data-id="' + item.awardId + '" data-status="' + contraryStatus + '">' + textOp + '</a>'
                        + '<a href="#" class="preview" data-id="' + item.awardId + '" data-status="' + item.enableStatus + '">预览</a>'
                        + '</div>'
                        + '</div>';
                });
                //将拼接好的信息赋值进html控件中
                $('.award-wrap').html(tempHtml);
            }
        });
    }

    //将class为product-wrap里面的a标签绑定上点击事件
    $('.award-wrap').on( 'click','a',function(e) {
        var target = $(e.currentTarget);
        if (target.hasClass('edit')) {
            //如果有class edit则点击就进入奖品信息编辑页面,并带有awardId参数
            window.location.href = '/o2o/shopadmin/awardoperation?awardId=' + e.currentTarget.dataset.id;
        } else if (target.hasClass('delete')) {
            //如果有class status则调用后台功能上/下架相关商品,并带有awardId参数
            changeItemStatus(e.currentTarget.dataset.id, e.currentTarget.dataset.status);
        } else if (target.hasClass('preview')) {
            //如果有class preview则去前台展示系统该奖品详情页预览商品情况
            window.location.href = '/o2o/frontend/awarddetail?awardId=' + e.currentTarget.dataset.id;
        }
    });
    function changeItemStatus(id,enableStatus) {
        //定义award json对象并添加awardId以及状态(上架/下架)
        var award={};
        award.awardId=id;
        award.enableStatus=enableStatus;
        $.confirm("确定吗?",function () {
            //上下架相关操作
            $.ajax({
                url:changeUrl,
                type:'POST',
                data:{
                    awardStr:JSON.stringify(award),
                    statusChange:true
                },
                dataType:'json',
                success:function (data) {
                    if(data.success){
                        $.toast("操作成功!");
                        getList();
                    }else{
                        $.toast("操作失败!");
                    }
                }
            })
        })
    }

    $('#new').click(function() {
        window.location.href = '/o2o/shopadmin/awardoperation';
    });
});