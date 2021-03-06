<#include "common/header.ftl">

<body>
<div class="container-fluid larry-wrapper">
    <div class="row">
        <div class="col-xs-12 col-sm-12 col-md-12">
            <!--头部搜索-->
            <section class="panel panel-padding">
                <form class="layui-form" data-params='{"dataName":"role","key":"id","action":"list"}'
                      action="${base}/role/list">
                    <div class="layui-form">
                        <div class="layui-inline">
                            <div class="layui-input-inline">
                                <input class="layui-input" name="name" placeholder="关键字">
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
                <@shiro.hasPermission name="role:delete">
                    <button class="layui-btn layui-btn-small layui-btn-danger ajax-all"
                            data-params='{"url": "${base}/role/delete","dataName":"role","key":"id","action":"del"}'>
                        <i class="iconfont">&#xe626;</i> 删除
                    </button>
                </@shiro.hasPermission>
                <@shiro.hasPermission name="role:add">
                    <button class="layui-btn layui-btn-small modal"
                            data-params='{"content": "${base}/role/toAdd", "title": "添加角色","full":"true","action":"add","dataName":"role","key":"id","type":2}'>
                        <i class="iconfont">&#xe649;</i> 添加角色
                    </button>
                </@shiro.hasPermission>
                </div>
                <div class="layui-form">
                    <table id="example" class="layui-table jq-even" data-params='{"dataName":"role","key":"id"}'>
                        <thead>
                        <tr>
                            <th width="30"><input type="checkbox" id="checkall" data-name="id" lay-filter="check"
                                                  lay-skin="primary"></th>
                            <th width="60"><span class="order" data-params='{"field":"id","sort":"asc"}'>序号</span></th>
                            <th>角色名称</th>
                            <th>角色编码</th>
                            <th width="142">操作</th>
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
</body>

<script id="list-tpl" type="text/html"
        data-params='{"url":"${base}/role/list","dataName":"role","key":"id","pageid":"#page"}'>
    {{# layui.each(d.list, function(index, item){ }}
    <tr>
        <td><input type="checkbox" name="id" value="{{ item.id}}" lay-skin="primary"></td>
        <td>{{ item.id}}</td>
        <td>{{item.name}}</td>
        <td>{{item.code}}</td>
        <td>
            <div class="layui-btn-group">
            <@shiro.hasPermission name="role:update">
                <button class="layui-btn layui-btn-mini modal"
                        data-params='{"content": "${base}/role/toUpdate", "title": "{{item.name}}","full":"true","data":"id={{ item.id }}","action":"edit","bind":true,"type":2}'>
                    <i class="iconfont">&#xe653;</i>编辑
                </button>
            </@shiro.hasPermission>
            <@shiro.hasPermission name="role:delete">
                <button class="layui-btn layui-btn-mini layui-btn-danger ajax"
                        data-params='{"url": "${base}/role/delete","confirm":"true","action":"del","data":"id={{ item.id }}"}'>
                    <i class="iconfont">&#xe626;</i>删除
                </button>
            </@shiro.hasPermission>
            </div>
        </td>
    </tr>
    {{# }); }}
</script>

<script src="${base}/static/js/layui/layui.js"></script>
<#include "common/version.ftl">
<script>
    layui.use('list');
</script>

</html>