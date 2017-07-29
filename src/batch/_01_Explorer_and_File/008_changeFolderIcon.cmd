@echo off

echo %0
echo.
echo.

if not exist discover.ico (
    echo discover.ico icon file not exists.
    goto end
)

:: 添加一个“desktop.ini”的文件，并添加一行“[ShellClassInfo]”到该文件
echo [.ShellClassInfo] >desktop.ini
:: 追加一行内容，表示当前文件夹使用的自定义图标来自“iconfile”指定的图标库文件，该库文件可以是DLL/ICL/ICO等格式
echo iconfile=discover.ico >>desktop.ini
:: 追加一行，表示使用库文件中第0个图标。对于使用ico格式的库文件，“iconindex”的值永远设置为0
echo iconindex=0 >>desktop.ini
:: attrib 命令设置文件属性为隐藏、系统、只读，以保护文件不被普通用户误删除
attrib desktop.ini +h +s +r
attrib discover.ico +h +s +r
echo.
echo changed icon of the folder to discover.ico successfully.
:end