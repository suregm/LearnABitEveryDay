@echo off

echo %0
echo.
echo.

if "%1" == "" (
    echo syntax: %0 filename
    echo filename表示要插入内容的文件
    echo.
    echo 未指定要插入内容的文件，无法执行插入操作。
    echo.
    goto end
)

if not exist %1 (
    echo.
    echo 指定要插入内容的文件%1不存在，请检查。
    goto end
)

echo being inserting...
echo.

cd. >content.temp
echo %本月利润表统计% >>content.temp
:: 将“content.temp”与“%1”中的内容按顺序放在一起，然后存入“temp”文件。
:: 在“temp”文件中，首先是“content.temp”中的内容，接着是“%1”文件的内容
copy content.temp + %1 temp >nul
:: /f 强制方式删除，/q 删除时不显示确认信息
del /f /q %1 >nul
del /f /q content.temp >nul
ren temp %1
echo 成功在%1文件前面插入指定内容！

:end


