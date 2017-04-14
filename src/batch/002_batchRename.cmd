@echo off
echo start to rename files's name...

::变量赋值非数值时等号两边不能含有空格
set extension=.rar
::参数/a表示数值类型变量
set /a sum = 0

::文件名不能包含空格，可以是002_batchRenameCopy.cmd或者002_batchRename-Copy.cmd，否则报语法错误
for %%f in (*) do (
    if not "%%f"=="002_batchRename.cmd" (
        ren %%f %%f%extension%
        set /a sum = sum + 1
    )
)

echo.
echo.
echo rename complete, totally %sum% files renamed.

set sum =
set extension =

echo.
echo.
pause