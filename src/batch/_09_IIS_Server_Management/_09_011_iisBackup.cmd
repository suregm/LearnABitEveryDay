@echo off

echo %0
echo.
echo.

:: 通过备份迅速恢复IIS配置

echo ---------请选择要执行的操作---------
echo ----1.输入数字1并回车，创建普通的IIS配置数据备份
echo ----2.输入数字2并回车，创建加密的IIS配置数据备份
echo ----3.输入数字3并回车，利用备份恢复当前IIS配置
echo ----4.输入数字4并回车，删除指定的IIS配置数据备份
echo ----5.输入其他字符，直接退出批处理
echo.
echo.

set /p num=请选择要执行的操作：
if "%num%"=="1" (
    set /p backupName=请输入备份副本的名称：
    echo.
    iisback /backup /b %backupName%
    goto exit
)
if "%num%"=="2" (
    set /p backupName=请输入备份副本的名称：
    set /p encryptingPassword=请输入用于加密的密钥：
    echo.
    iisback /backup /b %backupName% /e %encryptingPassword%
    goto exit
)
if "%num%"=="3" (
    cls
    echo 当前可用的IIS配置备份如下：
    iisback /list
    echo.
    set /p backupName=请输入用于恢复的副本名称：
    set /p encrypt=若是加密副本请输入小写字母y：
    echo.
    if %encrypt%=="y" (
        set /p encryptingPassword=请输入该副本的密钥：
        echo.
        iisback /restore /b %backupName% /e %encryptingPassword%
        goto exit
    )
    iisback /restore /b %backupName%
    goto exit
)
if "%num%"=="4" (
    cls
    echo 当前可用的IIS配置备份如下：
    iisback /list
    echo.
    set /p backupName=请输入要删除的副本名称：
    echo.
    iisback /delete /b %backupName%
    goto exit
)
echo.
echo 你输入了%num%字符，批处理自动退出...

:exit
set num=
set backupName=
set encryptingPassword=
set encrypt=