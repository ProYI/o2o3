$(function() {
    var loading = false;
    // 允许返回的最大条数
    var maxItems = 999;
    // 一页的返回最大条数
    var pageSize = 10;
    // 获取店铺列表的URL
    var listUrl = '/o2o/frontend/listshops';
    // 获取店铺里诶包及区域列表的URL
    var searchDivUrl = '/o2o/frontend/listshopspageinfo';
    // 页码
    var pageNum = 1;
    var parentId = getQueryString('parentId');
    var areaId = '';
    var shopCategoryId = '';
    var shopName = '';

    //渲染出店铺类别列表以及区域列表以供搜索
    getSearchDivData();

    // 预先加载10条店铺信息
    addItems(pageSize, pageNum);

    /**
    * 获取店铺类别列表以及区域列表信息
    * @return:
    */
    function getSearchDivData() {
        // 如果传入了parentId，则取出一级类别下面的所有二级类别
        var url = searchDivUrl + '?' + 'parentId=' + parentId;
        $
            .getJSON(
                url,
                function(data) {
                    if (data.success) {
                        var shopCategoryList = data.shopCategoryList;
                        var html = '';
                        html += '<a href="#" class="button" data-category-id=""> 全部类别  </a>';
                        shopCategoryList
                            .map(function(item, index) {
                                html += '<a href="#" class="button" data-category-id='
                                    + item.shopCategoryId
                                    + '>'
                                    + item.shopCategoryName
                                    + '</a>';
                            });
                        $('#shoplist-search-div').html(html);
                        var selectOptions = '<option value="">全部街道</option>';
                        var areaList = data.areaList;
                        areaList.map(function(item, index) {
                            selectOptions += '<option value="'
                                + item.areaId + '">'
                                + item.areaName + '</option>';
                        });
                        $('#area-search').html(selectOptions);
                    }
                });
    }

    /**
    * 获取分页展示的店铺列表信息
    * @return:
    */
    function addItems(pageSize, pageIndex) {
        // 拼接查询的URL，赋空值默认就去掉这个条件的限制，有值就代表按照这个条件筛选
        var url = listUrl + '?' + 'pageIndex=' + pageIndex + '&pageSize='
            + pageSize + '&parentId=' + parentId + '&areaId=' + areaId
            + '&shopCategoryId=' + shopCategoryId + '&shopName=' + shopName;
        // 设定加载符，若还在后台取数据则不能再次访问后台，避免多次重复加载
        loading = true;
        //访问后台获取相应查询条件下的商品列表
        $.getJSON(url, function(data) {
            if (data.success) {
                //获取当前查询条件下的商品的总数
                maxItems = data.count;
                var html = '';
                //遍历商品列表，拼接出卡片集合
                data.shopList.map(function(item, index) {
                    html += '' + '<div class="card" data-shop-id="'
                        + item.shopId + '">' + '<div class="card-header">'
                        + item.shopName + '</div>'
                        + '<div class="card-content">'
                        + '<div class="list-block media-list">' + '<ul>'
                        + '<li class="item-content">'
                        + '<div class="item-media">' + '<img src="'
                        + getContextPath() + item.shopImg + '" width="44">' + '</div>'
                        + '<div class="item-inner">'
                        + '<div class="item-subtitle">' + item.shopDesc
                        + '</div>' + '</div>' + '</li>' + '</ul>'
                        + '</div>' + '</div>' + '<div class="card-footer">'
                        + '<p class="color-gray">'
                        + new Date(item.lastEditTime).Format("yyyy-MM-dd")
                        + '更新</p>' + '<span>点击查看</span>' + '</div>'
                        + '</div>';
                });
                //将卡片集合添加到目标html组件里
                $('.list-div').append(html);
                // 获取目前为止以显示的总数，包含之前加载的
                var total = $('.list-div .card').length;
                // 若总数达到跟按照此查询条件列出来的总数一致，则停止后台的加载
                if (total >= maxItems) {
                    // 加载完毕，则注销无限加载事件，以防不必要的加载
                    $('.infinite-scroll-preloader').hide();
                } else {
                    // 显示加载提示符
                    $('.infinite-scroll-preloader').show();
                }

                // 否则页码+1， 继续加载新的店铺
                pageNum += 1;
                //加载结束，可以再次加载了
                loading = false;
                //刷新页面，显示新加载的店铺
                $.refreshScroller();
            }
        });
    }

    // 下滑屏幕自动进行分页搜索
    $(document).on('infinite', '.infinite-scroll-bottom', function() {
        if (loading)
            return;
        addItems(pageSize, pageNum);
    });

    // 点击店铺的卡片进入该店铺的详情页
    $('.shop-list').on('click', '.card', function(e) {
        var shopId = e.currentTarget.dataset.shopId;
        window.location.href = '/o2o/frontend/shopdetail?shopId=' + shopId;
    });

    // 选择新的店铺类别后，重置页码，清空原来的店铺列表重新加载新的搜索结果
    $('#shoplist-search-div').on(
        'click',
        '.button',
        function(e) {
            if (parentId) {// 如果传递过来的是一个父类下的子类
                shopCategoryId = e.target.dataset.categoryId;
                // 如果之前选定了别的category，移除其选定效果，改成选新的
                if ($(e.target).hasClass('button-fill')) {
                    $(e.target).removeClass('button-fill');
                    shopCategoryId = '';
                } else {
                    $(e.target).addClass('button-fill').siblings()
                        .removeClass('button-fill');
                }
                // 由于查询条件发生改变，清空店铺列表再进行查询
                $('.list-div').empty();
                // 重置页码
                pageNum = 1;
                addItems(pageSize, pageNum);
            } else {// 如果传递过来的父类为空，则按照父类查询
                parentId = e.target.dataset.categoryId;
                if ($(e.target).hasClass('button-fill')) {
                    $(e.target).removeClass('button-fill');
                    parentId = '';
                } else {
                    $(e.target).addClass('button-fill').siblings()
                        .removeClass('button-fill');
                }
                // 由于查询条件发生改变，清空店铺列表再进行查询
                $('.list-div').empty();
                // 重置页码
                pageNum = 1;
                addItems(pageSize, pageNum);
                parentId = '';
            }
        });

    // 查询名字发生变化，重置页码，清空列表重新显示查询结果
    $('#search').on('input', function(e) {
        shopName = e.target.value;
        $('.list-div').empty();
        pageNum = 1;
        addItems(pageSize, pageNum);
    });

    // 查询区域信息发生变化，重置页码，清空列表重新显示查询结果
    $('#area-search').on('change', function() {
        areaId = $('#area-search').val();
        $('.list-div').empty();
        pageNum = 1;
        addItems(pageSize, pageNum);
    });

    // 打开右边侧栏
    $('#me').click(function() {
        $.openPanel('#panel-left-demo');
    });

    // 初始化
    $.init();
});