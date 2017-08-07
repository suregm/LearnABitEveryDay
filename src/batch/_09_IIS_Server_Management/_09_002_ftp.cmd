@echo off

echo %0
echo.
echo.

cls
echo.
echo.
echo ---------请选择要执行的操作---------
echo ----1.输入数字1并回车，创建普通的FTP站点
echo ----2.输入数字2并回车，创建具有指定IP地址和端口的FTP站点
echo ----3.输入数字3并回车，删除指定的FTP站点
echo ----4.输入数字4并回车，为FTP站点创建虚拟目录
echo ----5.输入数字5并回车，删除FTP站点中的虚拟目录
echo ----6.输入其他字符，直接退出批处理
echo.
echo.

set /p num=请选择要执行的操作：
if "%num%"=="1" (
    echo.
    set /p sourcePath=请输入站点资源的路径：
    set /p siteName=请输入站点的名称：
    echo.

    :: iisftp /create %sourcePath% %siteName%
    :: iisftp /query
    :: iisftp /start %siteName%
    :: iisftp /pause %siteName%
    :: iisftp /stop %siteName%

    iisftp /create %sourcePath% %siteName%
    goto exit
)
if "%num%"=="2" (
    echo.
    set /p sourcePath=请输入站点资源的路径：
    set /p siteName=请输入站点的名称：
    set /p ip=请输入站点使用的ip地址：
    set /p port=请输入站点使用的端口号：
    echo.
    iisftp /create %sourcePath% %siteName% /I %ip% /b %port%
    goto exit
)
if "%num%"=="3" (
    cls
    :: 显示当前所有的FTP站点信息，包括站点名、IP、Port及当前状态等
    iisftp /query
    set /p removedSite=请输入要删除的站点名称：
    echo.
    iisftp /delete %removedSite%
    goto exit
)
if "%num%"=="4" (
    cls
    iisftp /query
    set /p siteName=请输入要创建虚拟目录的FTP站点名称：
    set /p sourceVD=请输入虚拟目录的资源路径：
    set /p vdName=请输入虚拟目录的名称：
    echo.
    :: 创建虚拟目录
    iisftpdr /create %siteName% %vdName% %sourceVD%
    goto exit
)
if "%num%"=="5" (
    cls
    iisftp /query
    set /p siteName=请输入要删除虚拟目录的FTP站点：
    echo.
    iisftpdr /query %siteName%
    set /p vdName=请输入要删除的虚拟目录：
    echo.
    iisftpdr /delete %siteName%\%vdName%
    goto exit
)
echo.
echo 你输入了%num%字符，批处理自动退出...

:exit
set num=
set sourcePath=
set siteName=
set ip=
set port=
set removedSite=
set sourceVD=
set vdName=