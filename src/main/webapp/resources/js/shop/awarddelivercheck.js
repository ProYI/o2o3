$(function () {
    var awardName = '';
    getList();

    function getList() {
        //获取积分兑换记录的URL
        var listUrl = '/o2o/shopadmin/listuserawardmapsbyshop?pageIndex=1&pageSize=999&awardName=' + awardName;
        $.getJSON(listUrl, function (data) {
            if (data.success) {
                var userAwardMapList = data.userAwardMapList;
                var tempHtml = '';
                //拼接成展示列表
                userAwardMapList.map(function(item, index){
                    tempHtml += '' + '<div class="row row-userawarddeliver">'
                        + '<div class="col-10">'+ item.award.awardName +'</div>'
                        + '<div class="col-40">'+ new Date(item.createTime).Format("yyyy-MM-dd hh:mm:ss") +'</div>'
                        + '<div class="col-20">'+ item.user.name +'</div>'
                        + '<div class="col-10">'+ item.point +'</div>'
                        + '<div class="col-20">'+ item.operator.name +'</div>'
                        + '</div>';
                });
                $('.awarddeliver-wrap').html(tempHtml);
            }
        });
    }

    //搜索绑定，并获取按照用户名模糊查询
    $('#search').on('change', function (e) {
        awardName = e.target.value;
        $('.awarddeliver-wrap').empty();
        getList();
    });
});