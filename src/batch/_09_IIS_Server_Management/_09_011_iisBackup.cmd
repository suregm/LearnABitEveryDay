@echo off

echo %0
echo.
echo.

:: 通过iisback备份迅速恢复整体的IIS配置
:: 使用iiscnfg配置具体的每一个参数

echo ---------请选择要执行的操作---------
echo ----1.输入数字1并回车，创建普通的IIS配置数据备份
echo ----2.输入数字2并回车，创建加密的IIS配置数据备份
echo ----3.输入数字3并回车，利用备份恢复当前IIS配置
echo ----4.输入数字4并回车，删除指定的IIS配置数据备份
echo ----5.输入数字5并回车，将IIS服务器指定的数据导出到XML文件
echo ----6.输入数字6并回车，将XML配置文件导入IIS服务
echo ----7.输入数字7并回车，在IIS服务器直接复制配置数据
echo ----8.输入数字8并回车，删除指定的IIS配置数据备份
echo ----9.输入其他字符，直接退出批处理
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
if "%num%"=="5" (
    set /p xmlFileName=请输入用于存放备份信息文件的名称：
    set /p iisLocation=请输入备份数据的IIS位置：
    set /p encryption=若对备份文件加密请输入小写y：
    echo.
    if "%encryption%"=="y" (
        set /p encode=请输入加密密钥：
        echo.
        iiscnfg /export /f %xmlFileName% /sp %iisLocation% /d %encode%
        goto exit
    )
    :: 在指定“sp”对数时，对于FTP及Web站点只能使用IIS为站点指定的标识符。
    :: 对于FTP站点标识符前，应该添加“/lm/w3svc/”；对于Web站点前，应该添加“/lm/msftpsvc/”。
    :: 对于整个IIS配置数据备份应使用“/”，否则会提示找不到指定的数据库路径。
    iiscnfg /export /f %xmlFileName% /sp %iisLocation%
    goto exit
)
if "%num%"=="6" (
    set /p xmlFileName=请输入用于恢复备份信息文件的名称：
    set /p xmlLocation=请输入备份文件中用于恢复的备份数据位置：
    set /p iisLocation=请输入具体从IIS哪个位置恢复数据：
    set /p encryption=若该备份文件有加密，请输入小写y：
    echo.
    if "%encryption%"=="y" (
        set /p encode=请输入相应的加密密钥：
        echo.
    :: 在指定“sp”对数时，对于FTP及Web站点只能使用IIS为站点指定的标识符。
    :: 对于FTP站点标识符前，应该添加“/lm/w3svc/”；对于Web站点前，应该添加“/lm/msftpsvc/”。
    :: 对于整个IIS配置数据备份应使用“/”，否则会提示找不到指定的数据库路径。
        iiscnfg /import /f %xmlFileName% /sp %xmlLocation% %dp %iisLocation% /d %encode%
        goto exit
    )
    iiscnfg /import /f %xmlFileName% /sp %xmlLocation% %dp %iisLocation%
    goto exit
)
if "%num%"=="7" (
    cls
    set /p destination=请输入目标IIS服务器的IP地址或计算机名：
    set /p userName=请输入登录目标服务器的用户名：
    set /p password=请输入登录目标服务器的密码：
    echo.
    iiscnfg /copy /ts %destination% /tu %userName% /tp %password%
    goto exit
)
if "%num%"=="8" (
    cls
    echo 当前可用的IIS配置备份如下：
    iiscnfg /list
    echo.
    set /p cnfgName=请输入要删除的副本名称：
    echo.
    iiscnfg /delete /b %cnfgName%
    goto exit
)
echo.
echo 你输入了%num%字符，批处理自动退出...

:exit
set num=
set backupName=
set encryptingPassword=
set encrypt=
set xmlFileName=
set xmlLocation=
set iisLocation=
set encryption=
set encode=
set destination=
set userName=
set password=