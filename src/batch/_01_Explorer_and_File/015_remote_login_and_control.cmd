@echo off

echo %0
echo.
echo.

1、mstsc /console /v:IP地址:端口
2、net use x: \\IP地址\文件夹名


https://wenku.baidu.com/view/e91539ed4bfe04a1b0717fd5360cba1aa8118c0a.html


（DOS命令输入：）
net user \\%远程计算机IP%/ipc$ "%对方用户密码%" /user:%对方用户名%
at \\%对方用户名% %要自动登录的时间% "d:\remote.cmd"

:: Psexec实用工具（如 Telnet）和远程控制程序（如 Symantec 的 PC Anywhere）使您可以在远程系统上执行程序，但安装它们非常困难，并且需要您在想要访问的远程系统上安装客户端软件。PsExec 是一个轻型的 telnet 替代工具，它使您无需手动安装客户端软件即可执行其他系统上的进程，并且可以获得与控制台应用程序相当的完全交互性。PsExec 最强大的功能之一是在远程系统和远程支持工具（如 IpConfig）中启动交互式命令提示窗口，以便显示无法通过其他方式显示的有关远程系统的信息。注意：某些反病毒扫描程序会报告其中的一个或多个工具感染了“远程管理”病毒。这些 PsTools 都不带病毒，但是如果病毒使用了这些工具，则它们可能触发病毒通知。
psexec.exe \\%ip% -u %username% -p %password% -c open.bat
psexec.exe \\%ip% -u %username% -p %password% cmd /c open.bat






NET USE [devicename | *] [\\computername\sharename[\volume] [password | *]]

015_remote_login_and_control
net use \\%1\ipc$ %3 /u:"%2"

rem 批处理远程控制
sc config termservice start= auto
sc start termservice
echo 请输入你要远程这台计算机的用户名
set user=" "
set /p user=
echo 请输入你要远程这台计算机的用户名密码
set psd=" "
set /p psd=
net user "%user%" "%psd%" /add
net localgroup administrators "%user%" /add
reg add "HKEY_LOCAL_MACHINE\SYSTEM\CurrentControlSet\Control\Terminal Server" /v "fDenyTSConnections" /t REG_DWORD /d 00000000 /f
echo 你现在可以用%user%,密码%psd%来远程控制这台计算机了
set tm1=%time:~0,2%
set tm2=%time:~3,2%
set tm3=%time:~6,2%
@echo 创建时间： %date% %tm1%点%TM2%分%TM3%秒
pause