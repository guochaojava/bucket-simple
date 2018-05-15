<#include "common/header.ftl">

<body>
<div class="container-fluid larry-wrapper">
    <div class="row">
        <div class="col-xs-12 col-sm-12 col-md-12">
            <!--头部搜索-->
            <section class="panel panel-padding">
                <form class="layui-form" data-params='{"dataName":"admin","action":"list"}' action="${base}/user/list">
                    <div class="layui-form">
                        <div class="layui-inline">
                            <div class="layui-input-inline">
                                <input class="layui-input" name="loginName" placeholder="登录名">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <div class="layui-input-inline">
                                <input class="layui-input" name="nickName" placeholder="用户昵称">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <button lay-submit class="layui-btn" lay-filter="search">查找</button>
                        </div>
                    </div>
                </form>
            </section>

            <!--列表-->
            <section class="panel panel-padding">
            <div class="group-button">
            <@shiro.hasRole name="admin">
                <@shiro.hasPermission name="user:delete">
                    <button class="layui-btn layui-btn-small layui-btn-danger ajax-all"
                            data-params='{"url": "${base}/user/delete","confirm":"true","dataName":"admin","key":"id","action":"del"}'>
                        <i class="iconfont">&#xe626;</i> 删除
                    </button>
                </@shiro.hasPermission>
                <@shiro.hasPermission name="user:add">
                    <button class="layui-btn layui-btn-small layui-btn-normal ajax-all"
                            data-params='{"url": "${base}/user/status","data":"switch=1","dataName":"admin","key":"id"}'>
                        <i class="layui-icon">&#x1005;</i> 启用
                    </button>
                    <button class="layui-btn layui-btn-small modal"
                            data-params='{"content":".add-subcat","area":"720px,700px", "title":"添加用户","type":"1","dataName":"admin","key":"id","action":"add"}'>
                        <i class="iconfont">&#xe649;</i> 添加
                    </button>
                </@shiro.hasPermission>
            </div>
            </@shiro.hasRole>
                <div class="layui-form">
                    <table id="example" class="layui-table jq-even" data-params='{"dataName":"admin","key":"id"}'>
                        <thead>
                        <tr>
                            <th width="30"><input type="checkbox" id="checkall" data-name="id" lay-filter="check"
                                                  lay-skin="primary"></th>
                            <th width="70"><span class="order" data-params='{"field":"id","sort":"asc"}'> 序号</span></th>
                            <th>帐号</th>
                            <th>姓名</th>
                            <th>角色</th>
                            <th>电话</th>
                            <th width="80">状态</th>
                            <th width="150">操作</th>
                        </tr>
                        </thead>
                        <tbody id="list"></tbody>
                    </table>
                </div>

                <div class="text-right" id="page"></div>
            </section>
        </div>
    </div>
</div>
<div class="add-subcat">
    <form id="form1" class="layui-form" data-params='{"dataName":"admin","key":"id","action":"add"}'
          action="${base}/user/add" method="POST">
        <div class="layui-form-item">
            <label class="layui-form-label">帐号</label>
            <div class="layui-input-block">
                <input type="text" name="loginName" required jq-verify="required" jq-error="请输入帐号" placeholder="请输入帐号"
                       autocomplete="off" class="layui-input ">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">密码</label>
            <div class="layui-input-block">
                <input type="password" name="password" required jq-verify="required|pass" jq-error="请输入密码|请检查密码格式"
                       placeholder="密码" autocomplete="off" class="layui-input ">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">密码确认</label>
            <div class="layui-input-block">
                <input type="password" name="pwd2" required jq-verify="required|pass2" jq-error="请确认密码|两次密码不一致"
                       placeholder="密码确认" autocomplete="off" class="layui-input ">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">所属角色</label>
            <div class="layui-input-inline">
                <select id="role" name="roleId" required hidden-required="true" jq-verify="required" jq-error="请选择角色">
                    <option value="">请选择角色</option>
                </select>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">姓名</label>
            <div class="layui-input-block">
                <input type="text" name="nickName" required jq-verify="required" jq-error="请输入姓名" placeholder="姓名"
                       autocomplete="off" class="layui-input ">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">电话</label>
            <div class="layui-input-block">
                <input type="text" name="telephone" required jq-verify="required" jq-error="请输入电话" placeholder="请输入电话"
                       autocomplete="off" class="layui-input ">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">状态</label>
            <div class="layui-input-block">
                <input type="radio" name="status" title="启用" value="1" checked/>
                <input type="radio" name="status" title="禁用" value="2"/>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" jq-submit jq-filter="submit">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>
</body>
<script id="list-tpl" type="text/html"
        data-params='{"url":"${base}/user/list","dataName":"admin","key":"id","pageid":"#page"}'>

    {{# layui.each(d.list, function(index, item){ }}
    <tr>
        <td><input type="checkbox" name="id" value="{{ item.id}}" lay-skin="primary"></td>
        <td>{{ item.id}}</td>
        <td>{{ item.loginName}}</td>
        <td>{{ item.nickName}}</td>
        <td>{{ item.roleName}}</td>
        <td>{{ item.telephone}}</td>
        <td>
        <@shiro.hasPermission name="user:update">
            <input type="checkbox" name="status" lay-skin="switch" lay-text="启用|禁用" value="1"
                   {{#if (item.status== 1){ }}checked="checked" {{# } }} lay-filter="ajax"
                   data-params='{"url":"${base}/user/status","data":"id={{ item.id}}"}'>
        </@shiro.hasPermission>
        </td>
        <td>
        <@shiro.hasPermission name="user:update">
            <button class="layui-btn layui-btn-mini modal"
                    data-params='{"content": ".add-subcat","area":"500px,480px","title":"编辑管理员","data":"id={{item.id}}","type":"1","action":"edit","bind":true}'>
                <i class="iconfont">&#xe653;</i>编辑
            </button>
        </@shiro.hasPermission>
        <@shiro.hasPermission name="user:delete">
            <button class="layui-btn layui-btn-mini layui-btn-danger ajax"
                    data-params='{"url": "${base}/user/delete","confirm":"true","data":"id={{item.id}}","action":"del"}'>
                <i class="iconfont">&#xe626;</i>删除
            </button>
        </@shiro.hasPermission>
        </td>
        </td>
    </tr>
    {{# }); }}
</script>
<script src="${base}/static/js/layui/layui.js"></script>
<script src="${base}/static/js/common.js"></script>
<#include "common/version.ftl">
<script>
    layui.use('list', function () {
        var $ = layui.jquery,
                form = layui.jqform;

        form.verify({
            username: function (value) {
                if (!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)) {
                    return '文章标题不能有特殊字符';
                }
                if (/(^\_)|(\__)|(\_+$)/.test(value)) {
                    return '文章标题首尾不能出现下划线\'_\'';
                }
                if (/^\d+\d+\d$/.test(value)) {
                    return '文章标题不能全为数字';
                }
            },
            pass2: function (value) {
                var pwd = $("input[name='password']").val();
                if (value != pwd) {
                    return '两次密码不一致';
                }
            },
            pass: [
                /^[\S]{6,12}$/, '密码必须6到12位，且不能出现空格'
            ]
        });

        //获取角色
        $.ajax({
            url: "${base}/role/listNoPages",
            dataType: "json",
            success: function (data) {
                $.each(data.data, function (i, item) {
                    $("#role").append("<option value='" + item.id + "'>" + item.name + "</option>")
                });
            }
        });
    });
</script>

</html>