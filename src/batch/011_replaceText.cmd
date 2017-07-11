@echo off

echo %0
echo.
echo.

setlocal enabledelayexpansion
cd. >profit_new.doc
echo being replacing...
echo.

for /f %%a in (profit.doc) do (
    set str=%%a
    set str=!str:OTHER=OTHER!
    echo !str! >>profit_new.doc
)

ren profit.doc profit_old.doc
ren profit_new.doc profit.doc
echo 成功替换文件中指定的字符串，profit_old.doc为未修改前的文件！
set str=