<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>后台</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1,user-scalable=no">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <!-- ico -->
    <link rel="icon" href="${base}/static/favicon.ico" type="image/x-icon">
    <link rel="shortcut icon" href="${base}/static/favicon.ico" type="image/x-icon">
    <!-- load css -->
    <link rel="stylesheet" type="text/css" href="${base}/static/css/font/iconfont.css?v=1.0.0" media="all">
    <link rel="stylesheet" type="text/css" href="${base}/static/css/layui.css?v=1.0.9" media="all">
    <link rel="stylesheet" type="text/css" href="${base}/static/css/jqadmin.css?v=2.0.0" media="all">
</head>

<body>
<ul class='right-click-menu' style="display: none">
    <li><a href='javascript:;' data-event='fresh'>刷新</a></li>
    <li><a href='javascript:;' data-event='close'>关闭</a></li>
    <li><a href='javascript:;' data-event='other'>关闭其它</a></li>
    <li><a href='javascript:;' data-event='all'>全部关闭</a></li>
</ul>
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <!-- logo区域 -->
        <div class="jqadmin-logo-box">
            <a class="logo" href="https://github.com/guochaojava" target="_blank" title="李狗蛋">
                <h1>李狗蛋</h1>
            </a>
            <div class="menu-type"><i class="iconfont">&#xe61a;</i></div>
        </div>

        <!-- 主菜单区域 -->
        <div class="jqadmin-main-menu">
            <ul class="layui-nav clearfix" id="menu" lay-filter="main-menu">
            <@shiro.hasRole name="admin">
                <li class="layui-nav-item layui-this">
                    <a href="javascript:;" data-title="网站设置">
                        <i class="iconfont hide-icon">&#xe689;</i>
                        <span>网站设置</span>
                    </a>
                </li>
            </@shiro.hasRole>
            </ul>
        </div>
        <!-- 头部右侧导航 -->
        <div class="header-right">
            <ul class="layui-nav jqadmin-header-item right-menu">
                <li class="layui-nav-item first">
                    <a href="javascript:;">
                        <cite><@shiro.principal property="nickName"/></cite>
                        <span class="layui-nav-more"></span>
                    </a>
                    <dl class="layui-nav-child">
                        <dd class="tab-menu">
                            <a href="javascript:;" data-url="${base}/sys/userInfo" data-title="个人信息">
                                <i class="iconfont " data-icon='&#xe672;'>&#xe672; </i>
                                <span>个人信息</span>
                            </a>
                        </dd>
                        <dd>
                            <a href="javascript:;" onclick="logout();" data-title="退出">
                                <i class="iconfont ">&#xe64b; </i>退出
                            </a>
                        </dd>
                    </dl>
                </li>
                <li class="layui-nav-item tab-menu">
                    <a class="msg" href="javascript:;" data-url="" data-title="站内信息">
                        <i class="iconfont" data-icon='&#xe63c;'>&#xe63c; </i>
                        <span class="msg-num">0</span>
                    </a>
                </li>
            </ul>
            <button title="刷新" class="layui-btn layui-btn-normal fresh-btn"><i class="iconfont">&#xe62e; </i></button>
        </div>
    </div>

    <!-- 左侧导航-->
    <div class="layui-side layui-bg-black jqamdin-left-bar">
        <div class="layui-side-scroll">
            <div id="submenu">
            <#--
             <div class="sub-menu">
                 <ul class="layui-nav layui-nav-tree">
                     <li class="layui-nav-item">
                         <a href="javascript:;" data-url="product.html" data-title="产品列表"> <i
                                 class="iconfont hide-icon" data-icon="&#xe61a;">&#xe61a;</i> <span>产品列表</span> </a>
                     </li>
                     <li class="layui-nav-item">
                         <a href="javascript:;" data-url="brand.html" data-title="品牌管理"> <i
                                 class="iconfont hide-icon" data-icon="&#xe60d;">&#xe60d;</i> <span>品牌管理</span> </a>
                     </li>
                     <li class="layui-nav-item">
                         <a href="javascript:;" data-url="product-cat.html" data-title="分类管理"> <i
                                 class="iconfont hide-icon" data-icon="&#xe610;">&#xe610;</i> <span>分类管理</span> </a>
                     </li>
                 </ul>
             </div>-->
            <@shiro.hasRole name="admin">
                <div class="sub-menu">
                    <ul class="layui-nav layui-nav-tree">
                        <li class="layui-nav-item layui-nav-itemed">
                            <a href="javascript:;" data-title="系统管理">
                                <i class="iconfont">&#xe646;</i>
                                <span>系统管理</span><em class="layui-nav-more"></em>
                            </a>
                            <dl class="layui-nav-child">
                                <dd>
                                    <a href="javascript:;" data-url="config.html" data-title="系统设置">
                                        <i class="iconfont" data-icon="&#xe689;">&#xe689;</i>
                                        <span>系统设置</span>
                                    </a>
                                </dd>
                                <dd>
                                    <a href="javascript:;" data-url="sys-log.html" data-title="系统日志">
                                        <i class="iconfont" data-icon="&#xe64a;">&#xe64a;</i>
                                        <span>系统日志</span>
                                    </a>
                                </dd>
                                <dd>
                                    <a href="javascript:;" data-url="menu.html" data-title="栏目管理">
                                        <i class="iconfont" data-icon="&#xe654;">&#xe654;</i>
                                        <span>栏目管理</span>
                                    </a>
                                </dd>
                            </dl>
                        </li>
                        <li class="layui-nav-item">
                            <a href="javascript:;" data-title="权限管理">
                                <i class="iconfont">&#xe609;</i>
                                <span>权限管理</span><em class="layui-nav-more"></em>
                            </a>
                            <dl class="layui-nav-child">
                                <dd>
                                    <a href="javascript:;" data-url="${base}/role" data-title="角色管理">
                                        <i class="iconfont" data-icon="&#xe608;">&#xe608;</i>
                                        <span>角色管理</span>
                                    </a>
                                </dd>
                                <dd>
                                    <a href="javascript:;" data-url="${base}/permission" data-title="权限设置">
                                        <i class="iconfont" data-icon="&#xe668;">&#xe668;</i>
                                        <span>权限设置</span>
                                    </a>
                                </dd>
                                <dd>
                                    <a href="javascript:;" data-url="${base}/user" data-title="人员管理">
                                        <i class="iconfont" data-icon="&#xe672;">&#xe672;</i>
                                        <span>人员管理</span>
                                    </a>
                                </dd>
                            </dl>
                        </li>
                    </ul>
                </div>
            </@shiro.hasRole>
            </div>
        </div>
    </div>

    <!-- 左侧侧边导航结束 -->
    <!-- 右侧主体内容 -->
    <div class="layui-body jqadmin-body">

        <div class="layui-tab layui-tab-card jqadmin-tab-box" id="jqadmin-tab" lay-filter="tabmenu" lay-notAuto="true">
            <ul class="layui-tab-title">
                <li class="layui-this" id="admin-home" lay-id="0" fresh=1>
                    <i class="iconfont">&#xe622;</i><em>后台首页</em>
                </li>
            </ul>
            <div class="menu-btn" title="显示左则菜单">
                <i class="iconfont">&#xe616;</i>
            </div>
            <div class="tab-move-btn">
                <span>更多<i class="iconfont">&#xe604;</i></span>
                <!--<span class="move-left-btn"><i class="iconfont">&#xe616;</i></span>
                <span class="move-right-btn"><i class="iconfont ">&#xe618;</i></span>-->
            </div>
            <div class="layui-tab-content">
                <div class="layui-tab-item layui-show">
                    <iframe class="jqadmin-iframe" data-id='0' src="${base}/welcome"></iframe>
                </div>
            </div>
        </div>
    </div>
    <!-- 底部区域 -->
    <div class="layui-footer jqadmin-foot">
        <div class="layui-mian">
            <p class="jqadmin-copyright">
                <span class="layui">—— Copyright © 2013</span>
                <a href="https://github.com/guochaojava" target="_blank">李狗蛋</a>(中国).
                <span class="layui">All rights reserved.|Privacy Policy</span>
            </p>
        </div>
    </div>
</div>

</body>
<script type="text/javascript" src="${base}/static/js/layui/layui.js"></script>
<#include "common/version.ftl">
<script>
    var global = {};
    layui.use('index');

    function logout() {
        var $ = layui.jquery;
        layer.confirm('您确定要离开我嘛？', {
            title: "提示",
            btn: ['这事不能勉强', '好嘛再陪你一会'] //按钮
        }, function () {
            $.ajax({
                url: "${base}/logout",
                success: function (data) {
                    if (data.status == 200) {
                        window.location.reload();
                    }
                }
            })
        });
    }
</script>
</html>