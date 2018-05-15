<#include "common/header.ftl">

<body>
<div class="container-fluid larry-wrapper">
    <div class="row">
        <div class="col-xs-12 col-sm-8 col-md-4 col-md-offset-4 col-sm-offset-2 ">
            <!--头部搜索-->
            <section class="panel panel-padding login-pane">
                <h1 class="head-title">登录界面</h1>
                <form class="layui-form layui-form-pane" action="${base}/login" method="post">
                    <div class="layui-form-item">
                        <label class="layui-form-label"><i class="iconfont">&#xe672;</i> 帐号</label>
                        <div class="layui-input-block">
                            <input type="text" name="loginName" required jq-verify="required" placeholder="登录帐号"
                                   autocomplete="off" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label"><i class="iconfont">&#xe609;</i> 密码</label>
                        <div class="layui-input-block">
                            <input type="password" name="password" required jq-verify="required" placeholder="登录密码"
                                   autocomplete="off" class="layui-input">
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <div class="layui-input-inline">
                            <input type="checkbox" name="remeberMe" lay-skin="switch" checked>
                            <span class="font-pt">记住我的登录</span>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-input-block">
                            <button class="layui-btn" jq-submit jq-filter="submit">立即提交</button>
                        </div>
                    </div>
                </form>
            </section>

        </div>
    </div>
</div>
</body>
<script src="${base}/static/js/layui/layui.js"></script>
<#include "common/version.ftl">
<script>
    layui.use('simpleform');
</script>

</html>