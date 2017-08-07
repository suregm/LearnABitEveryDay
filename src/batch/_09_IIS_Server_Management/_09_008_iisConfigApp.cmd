@echo off

echo %0
echo.
echo.

cls
echo.
echo 当前配置数据库中可用的应用程序列表如下：
:: 显示
iisext /listapp
echo.
set /p app=请输入要启动的应用程序名：
echo.
:: 启动
:: 被启动的应用程序至少应该在配置数据库中有一个预先存在的依存关系，否则无法正常启动该应用程序。
iisext /enapp "%app%"
set app=