$(function() {
    var loading = false;
    // 分页允许最大条数
    var maxItems = 20;
    // 默认一页返回的商品数
    var pageSize = 10;
    //列出商品列表的url
    var listUrl = '/o2o/frontend/listproductsbyshop';
    //默认页码
    var pageNum = 1;
    //从地址栏里获取ShopId
    var shopId = getQueryString('shopId');
    var productCategoryId = '';
    var productName = '';
    //获取本店铺信息以及商品类别信息列表的url
    var searchDivUrl = '/o2o/frontend/listshopdetailpageinfo?shopId=' + shopId;
    //渲染出店铺基本信息以及商品类别列表以供搜索
    getSearchDivData();
    //预加载10条商品信息
    addItems(pageSize, pageNum);

    function getSearchDivData() {
        var url = searchDivUrl;
        $
            .getJSON(
                url,
                function(data) {
                    if (data.success) {
                        var shop = data.shop;
                        $('#shop-cover-pic').attr('src', shop.shopImg);
                        $('#shop-update-time').html(
                            new Date(shop.lastEditTime)
                                .Format("yyyy-MM-dd"));
                        $('#shop-name').html(shop.shopName);
                        $('#shop-desc').html(shop.shopDesc);
                        $('#shop-addr').html(shop.shopAddr);
                        $('#shop-phone').html(shop.phone);

                        // 获取后台返回的该店铺商品类别列表
                        var productCategoryList = data.productCategoryList;
                        var html = '';
                        //遍历商品列表，生成可以点击搜索相应商品类别下的商品的a标签
                        productCategoryList
                            .map(function(item, index) {
                                html += '<a href="#" class="button" data-product-search-id='
                                    + item.productCategoryId
                                    + '>'
                                    + item.productCategoryName
                                    + '</a>';
                            });
                        //将商品类别a标签绑定到相应的html组件中
                        $('#shopdetail-button-div').html(html);
                    }
                });
    }

    /**
    * 获取分页展示的商品列表信息
    * @param:
    * @return:
    */
    function addItems(pageSize, pageIndex) {
        // 拼接URL 赋空值默认就去掉这个条件的限制，有值就代表按照这个条件筛选
        var url = listUrl + '?' + 'pageIndex=' + pageIndex + '&pageSize='
            + pageSize + '&productCategoryId=' + productCategoryId
            + '&productName=' + productName + '&shopId=' + shopId;
        // 设定加载符，若还在后台取数据则不能再次访问后台，避免多次重复加载
        loading = true;
        //访问后台获取相应查询条件下的商品列表
        $.getJSON(url, function(data) {
            if (data.success) {
                //获取当前查询条件下的商品的总数
                maxItems = data.count;
                var html = '';
                //遍历商品列表，拼接出卡片集合
                data.productList.map(function(item, index) {
                    html += '' + '<div class="card" data-product-id='
                        + item.productId + '>'
                        + '<div class="card-header">' + item.productName
                        + '</div>' + '<div class="card-content">'
                        + '<div class="list-block media-list">' + '<ul>'
                        + '<li class="item-content">'
                        + '<div class="item-media">' + '<img src="'
                        + item.imgAddr + '" width="44">' + '</div>'
                        + '<div class="item-inner">'
                        + '<div class="item-subtitle">' + item.productDesc
                        + '</div>' + '</div>' + '</li>' + '</ul>'
                        + '</div>' + '</div>' + '<div class="card-footer">'
                        + '<p class="color-gray">'
                        + new Date(item.lastEditTime).Format("yyyy-MM-dd")
                        + '更新</p>' + '<span>点击查看</span>' + '</div>'
                        + '</div>';
                });
                $('.list-div').append(html);
                var total = $('.list-div .card').length;
                if (total >= maxItems) {
                    // 隐藏加载提示符
                    $('.infinite-scroll-preloader').hide();
                } else {
                    // 显示加载提示符
                    $('.infinite-scroll-preloader').show();
                }
                pageNum += 1;
                loading = false;
                $.refreshScroller();
            }
        });
    }

    // 下滑屏幕自动分页搜索
    $(document).on('infinite', '.infinite-scroll-bottom', function() {
        if (loading) {
            return;
        }
        addItems(pageSize, pageNum);
    });

    //选择新的商品类别之后,重置页码，清空原先的商品列表，按照新的类别去查询
    $('#shopdetail-button-div').on(
        'click',
        '.button',
        function(e) {
            productCategoryId = e.target.dataset.productSearchId;
            if (productCategoryId) {
                if ($(e.target).hasClass('button-fill')) {
                    $(e.target).removeClass('button-fill');
                    productCategoryId = '';
                } else {
                    $(e.target).addClass('button-fill').siblings()
                        .removeClass('button-fill');
                }
                $('.list-div').empty();
                pageNum = 1;
                addItems(pageSize, pageNum);
            }
        });

    //点击商品的卡片进入该商品的详情页
    $('.list-div').on(
        'click',
        '.card',
        function(e) {
            var productId = e.currentTarget.dataset.productId;
            window.location.href = '/o2o/frontend/productDetail?productId='
                + productId;
        });

    //需要查询的商品名字发生变化后，重置页码，清空原先的商品列表，按照新的名字去查询
    $('#search').on('change', function(e) {
        productName = e.target.value;
        $('.list-div').empty();
        pageNum = 1;
        addItems(pageSize, pageNum);
    });

    //打开侧栏
    $('#me').click(function() {
        $.openPanel('#panel-left-demo');
    });
    $.init();
});

