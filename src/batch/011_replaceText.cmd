@echo off

echo %0
echo.
echo.

:: 启用批处理变量延时扩充功能，当启用时，可以使用“!”批处理执行时扩充变量
setlocal enabledelayedexpansion
:: cd. 命令创建文件
cd. >profit_new.doc
echo being replacing...
echo.

for /f %%a in (profit.doc) do (
    set str=%%a
    :: 两个“!”之间的str使用了变量延时，先计算!!，将str变量中的“其它”替换为“其他”，然后将替换后的“str”赋值给原先的“str”变量
    :: 若在延时符号“!”之间未使用“setlocal enabledelayedexpansion”命令开启延时变量支持功能，
    :: 则延时符号“!”将不起任何作用，批处理只将其作为普通的字符处理。
    set str=!str:其它=其他!
    echo !str! >>profit_new.doc
)

ren profit.doc profit_old.doc
ren profit_new.doc profit.doc
echo 成功替换文件中指定的字符串，profit_old.doc为未修改前的文件！
set str=