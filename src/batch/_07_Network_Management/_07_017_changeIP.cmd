@echo off

echo %0
echo.
echo.

echo --------可供选择的IP地址--------
echo     1.办公室网络使用的IP地址
echo     2.小区内部网使用的IP地址
echo.

:again
set /p input=请输入要设置的IP地址类型：
if "%input%"=="1" (
    :: netsh interface ip set address 设置网卡地址， “本地连接”代表对应网卡的名称， 最后的“1”表示跳数
    netsh interface ip set address 本地连接 static 18.18.18.18 255.255.255.0 18.18.18.1 1 >nul
    echo.
    echo 成功将网卡地址设置为办公室网络使用的IP地址！
    goto end
)
if "%input%"=="2" (
    netsh interface ip set address 本地连接 static 10.30.1.88 255.255.255.0 10.30.1.254 1 >nul
    echo.
    echo 成功将网卡地址设置为小区内部网使用的IP地址！
    goto end
)
echo 请正确输入选择的类型，1代表办公室网络，2代表小区内部网...
pause >nul
goto again

:end