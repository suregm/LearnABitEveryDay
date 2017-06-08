@echo off

echo %0
echo.
echo.

if not exist discover.ico (
    echo discover.ico icon file not exists.
    goto end
)

echo [.ShellClassInfo] >desktop.ini
echo iconfile=discover.ico >>desktop.ini
echo iconindex=0 >>desktop.ini
attrib desktop.ini +h +s +r
attrib discover.ico +h +s +r
echo.
echo changed icon of the folder to discover.ico successfully.
:end