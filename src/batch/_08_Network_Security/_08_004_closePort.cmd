@echo off

echo %0
echo.
echo.

:: 一些危险的公共端口（FTP的21端口，Http的80端口，telnet的23端口）
:: IP标识网络中的计算机，端口用于标识计算机中运行的网络程序。IP地址和端口号组合就唯一标识网络中某个计算机中运行的程序
:: 端口有UDP和TCP两种，0~1023保留为公用服务使用，1024~65535用户可使用但需保证不冲突。
:: 服务可以理解为不提供任何用户界面的一类特殊的应用程序

echo 正在关闭Windows中的Internet服务...
:: >err 表示将命令正常执行时的输出欣喜转到名为err的文件中，避免显示在命令行窗口中，最后予以删除
iisreset /stop 2>&0 >err
echo http端口80，ftp端口21及STMP端口25已经成功关闭！
echo 正在关闭telnet服务...
:: 运行services.msc可打开“服务”管理窗口，查看当前系统中所有已注册的服务
net stop telnet 2>&0 >err
echo telnet端口23被成功关闭！
del /q /f err >nul