<#include "common/header.ftl">

<body>
<div class="container-fluid larry-wrapper">
    <div class="row">
        <div class="col-xs-12 col-sm-12 col-md-12">
            <section class="panel panel-padding">
                <form id="form1" class="layui-form" data-params='{"dataName":"role","key":"id","action":"add"}' action="${base}/role/add">
                    <div class="layui-form-item ">
                        <div class="layui-form-item">
                            <label class="layui-form-label">角色名称</label>
                            <div class="layui-input-block">
                                <input type="text" name="name" jq-verify="required" jq-error="请输入角色名称" placeholder="请输入角色名称" autocomplete="off" class="layui-input ">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">角色编码</label>
                            <div class="layui-input-block">
                                <input type="text" name="code" placeholder="角色编码" autocomplete="off" class="layui-input ">
                            </div>
                        </div>

                        <div class="layui-form-item permission-list">
                            <label class="layui-form-label">拥有权限</label>
                            <div class="layui-input-block role-box" id="list"></div>
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <div class="layui-input-block">
                            <button class="layui-btn" jq-submit jq-filter="submit">立即提交</button>
                            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                        </div>
                    </div>
                </form>
            </section>
        </div>
    </div>
</div>
</body>

<script id="list-tpl" type="text/html" data-params='{"url":"${base}/permission/listNoPages","dataName":"permision","callBack":"bindRole"}'>
    {{# layui.each(d.list, function(index, item){ }}
    <div class="jq-role-inline">
        <fieldset class="layui-elem-field">
            <legend>{{item.name}}<#--<input type="checkbox" name="roles[]" value="{{item.id}}" title="{{item.name}}" lay-skin="primary" lay-filter="role" />--></legend>
            <div class="layui-field-box">
                {{# if(item.sub && item.sub.length>0){ }}
                <ul>
                    {{# layui.each(item.sub, function(index2, item2){ }}
                    <li><input type="checkbox" name="roles[]" value="{{item2.id}}" title="{{item2.name}}" lay-skin="primary" lay-filter="role" /> {{# if(item2.sub && item2.sub.length>0){ }} {{# layui.each(item2.sub, function(index3, item3){ }}
                        <dl> <input type="checkbox" name="roles[]" value="{{item3.id}}" title="{{item3.name}}" lay-skin="primary" lay-filter="role" /> {{# if(item3.sub && item3.sub.length>0){ }} {{# layui.each(item3.sub, function(index4, item4){ }}
                            <dd><input type="checkbox" name="roles[]" value="{{item4.id}}" title="{{item4.name}}" lay-skin="primary" lay-filter="role" /></dd>
                            {{# }) }} {{# } }}
                        </dl>
                        {{# }) }} {{# } }}

                    </li>
                    {{# }) }}
                </ul>
                {{# } }}
            </div>
        </fieldset>
    </div>
    {{# }) }}
</script>

<script src="${base}/static/js/layui/layui.js"></script>
<#include "common/version.ftl">
<script>
    layui.use('role');
</script>

</html>