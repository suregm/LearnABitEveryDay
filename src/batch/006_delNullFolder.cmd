@echo off

echo %0
echo.
echo.

echo deleting the empty folder...
echo ----------------------------
:: "cd."命令不显示任何内容，因此将该命令的结果利用“>”重定向操作符输出到文件，便创建一个空文件
cd. >listnull.txt
:: “dir /ad /b /s”表示显示当前目录及所有子目录中的文件夹，显示结果中每个文件夹信息占一行
for /f "delims=" %%i in ('dir /ad /b /s') do (
    :: “||”连接两个命令时，仅当符号前面的命令执行失败后，才继续执行符号后的命令
    :: “dir /b "%%i"” 显示“%%i”目录中的内容，“/b”参数确保“%%i”为空目录时不显示任何内容
    :: “|” 为管道符，起传递作用
    :: findstr .>nul 表示查找失败，查找结果为空，即为空目录
    :: “echo %%i >>listnull.txt” 将空目录名称保存到listnull.txt文件中
    dir /b "%%i"|findstr .>nul || echo %%i >>listnull.txt
)
:: “/a”参数表示数值类型
set /a sum=0
for /f %%i in (listnull.txt) do (
    echo deleted success: %%i
    :: 删除指定目录，“/q”参数为不显示确认信息
    rd /q %%i
    set /a sum=sum+1
)
echo ----------------------------
:: %cd%代表当前目录
echo deleted %sum% empty folders of %cd% successfully in total.
echo.
set sum=
del /q listnull.txt >nul