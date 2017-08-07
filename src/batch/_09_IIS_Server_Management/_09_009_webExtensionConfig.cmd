@echo off

echo %0
echo.
echo.

cls
echo.
echo.
echo ---------请选择要执行的操作---------
echo ----1.输入数字1并回车，添加Web扩展服务之间的依赖关系
echo ----2.输入数字2并回车，删除Web扩展服务之间的依赖关系
echo ----3.输入数字3并回车，启用指定的Web扩展服务
echo ----4.输入数字4并回车，禁用指定的Web扩展服务
echo ----5.输入其他字符，直接退出批处理
echo.
echo.

set /p num=请选择要执行的操作：
if "%num%"=="1" (
    cls
    echo 当前可用的Web扩展服务如下：
    iisext /listext
    echo.
    set /p attachName=请输入需要依赖的Web扩展服务：
    set /p attachedName=请输入被依赖的Web扩展服务：
    echo.
    :: 建立%attachName%对%attachedName%的依赖关系
    :: 在建立依赖关系之前，必须确保被依赖的Web扩展服务已经启动
    iisext /adddep "%attachName%" "%attachedName%"
    goto exit
)
if "%num%"=="2" (
    echo.
    set /p attachName=请输入主动依赖的Web扩展服务：
    set /p attachedName=请输入被依赖的Web扩展服务：
    iisext /rmdep "%attachName%" "%attachedName%"
    goto exit
)
if "%num%"=="3" (
    cls
    echo 当前可用的Web扩展服务如下：
    iisext /listext
    echo.
    set /p extension=请输入需启动的Web扩展服务：
    echo.
    iisext /enext "%extension%"
    goto exit
)
if "%num%"=="4" (
    cls
    echo 当前可用的Web扩展服务如下：
    iisext /listext
    echo.
    set /p extension=请输入需禁用的Web扩展服务：
    echo.
    iisext /disext "%extension%"
    goto exit
)
echo.
echo 你输入了%num%字符，批处理自动退出...

:exit
set num=
set attachName=
set attachedName=
set extension=