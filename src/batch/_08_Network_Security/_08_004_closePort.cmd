@echo off

echo %0
echo.
echo.

:: 知识点：
:: 端口
:: 一些危险的公共端口（FTP的21端口，Http的80端口，telnet的23端口）
:: IP标识网络中的计算机，端口用于标识计算机中运行的网络程序。IP地址和端口号组合就唯一标识网络中某个计算机中运行的程序
:: 端口有UDP和TCP两种，0~1023保留为公用服务使用，1024~65535用户可使用但需保证不冲突。
:: 服务
:: 服务可以理解为不提供任何用户界面的一类特殊的应用程序
:: 管道
:: 管道（Pipe）是用于进程间通信的一段共享内存，创建管道的进程称为管道服务器，连接到一个管道的进程为管道客户机。一个进程在向管道写入数据后，另一进程就可以从管道的另一端将其读取出来。
:: 匿名管道是在父进程和子进程间单向传输数据的一种未命名管道，只能在本机使用，而不可以用于网络间的通信。
:: 命名管道通常用于网络环境中不同计算机上运行的进程之间的通信，可以是单向或双向的。
:: SAM 帐户
:: SAM(Security Account Manager)是Windows管理用户帐户使用的一种机制，SAM对帐号的管理是通过安全标识进行的。安全标识在帐号创建时同时创建，一旦帐号被删除，安全标识也同时被删除。安全标识是唯一的，即使是相同的用户名，每次创建时获得的安全标识也完全不同。


echo 正在关闭Windows中的Internet服务...
:: issreset ISS是Windows平台下的应用程序服务器，它提供了多种主要的Internet应用服务
:: issreset /start >nul  启动Internet服务
:: issreset /stop >nul  停止Internet服务
:: issreset /restart >nul  重启Internet服务
:: >err 表示将命令正常执行时的输出信息转到名为err的文件中，避免显示在命令行窗口中，最后予以删除
iisreset /stop 2>&0 >err
echo http端口80，ftp端口21及STMP端口25已经成功关闭！
echo 正在关闭telnet服务...
:: 运行services.msc可打开“服务”管理窗口，查看当前系统中所有已注册的服务
net stop telnet 2>&0 >err
echo telnet端口23被成功关闭！
del /q /f err >nul