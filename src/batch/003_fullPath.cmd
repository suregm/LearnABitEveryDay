@echo off

echo %0
echo.
echo.

echo the path of the running file is:
::“~”为扩展符号，与不同字母结合时代表的含义不同。
::“%0”代表文件本身，“%~dp0”表示“%0”文件的路径信息
::“~dp”组合代表将指定的变量扩展到路径
echo %~dp0

echo.
echo.
pause