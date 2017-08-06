@echo off

echo %0
echo.
echo.

echo 正在清除ARP缓存...
:: “>nul”表示将命令的输出欣喜定向到空设备中，即不显示在命令行窗口中
:: “2>&0”表示将命令执行出错的信息转到键盘输入流中，不显示在命令行窗口中
arp -d >nul 2>&0
echo 正在清除NetBT缓存...
nbtstat -r >nul 2>&0
echo 正在清除DNS缓存...
dnscmd /clearcache >nul 2>&0
echo 成功刷新缓存数据，修复网络连接！