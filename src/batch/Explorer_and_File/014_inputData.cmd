@echo off

echo %0
echo.
echo.

:repeate
:: 清楚命令行窗口屏幕中显示的信息，以便批处理显示自己的操作菜单
cls
echo.
echo.
echo ####################请选择要执行的操作####################
echo ------1.输入数字1并按回车，则显示当前日期------
echo ------2.输入数字2并按回车，则显示当前时间------
echo ------3.输入数字3并按回车，则显示文件列表------
echo ------4.输入其他任何字符，则终止退出------

echo.
echo.
echo 请选择要执行的操作：
:: 接收用户输入的信息，并以字符串的形式保存在“num”变量中
set /p num=

if "%num%"=="1" (
    cls
    echo.
    echo.
    echo 当前的日期为：
    :: /t 参数表示只显示日期而不需要用户输入新的日期
    date /t
    pause
    goto repeate
)

if "%num%"=="2" (
    cls
    echo.
    echo.
    echo 当前的时间为：
    :: /T无需用户输入新的时间
    time /T
    pause
    goto repeate
)

if "%num%"=="3" (
    cls
    echo.
    echo.
    echo 当前目录下文件列表为：
    :: /w 只显示文件或文件夹名称
    dir /w
    pause
    goto repeate
)

echo.
echo.
echo 你输入了%num%字符，批处理自动退出。
