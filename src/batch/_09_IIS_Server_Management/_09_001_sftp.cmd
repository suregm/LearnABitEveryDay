@echo off

rem 使用FTP 到某台机器上进行文件上传、下载的操作，在命令方式下基本逻辑是：
rem 1. ftp 主机名
rem 2. 输入用户名
rem 3. 输入密码
rem 4. 切换模式(asc 或者是 bin)
rem 5. 上传(put)或下载(get)
rem 6. 退出(bye or quit)

:: ftp命令
:: ftp [-v] [-d] [-i] [-n] [-g] [-s:FileName] [-a] [-w:WindowSize] [-A] [Host]
:: 参数：
:: -v：禁止显示远程服务器响应。
:: -d：启用调试、显示在客户端和服务器之间传递的所有ftp命令。
:: -i：多个文件传送时关闭交互提示。
:: -n：禁止自动登录到初始连接。
:: -g：禁用文件名组，它允许在本地文件和路径名中使用通配符字符（*和?）。
:: -s:FileName：指定包含ftp命令的文本文件；当ftp启动后，这些命令将自动运行。该参数中不允许有空格。使用该开关而不是重定向（>）。
:: -a：在捆绑数据连接时使用任何本地接口。
:: -w:WindowSize：替代默认大小为4096的传送缓冲区。
:: -A：匿名登陆。
:: Host：指定要连接到远程计算机的计算机名或IP地址。如果指定，计算机必须是最后一个参数。

rem 指定FTP用户名
set ftpUser=ftpuser
rem 指定FTP密码
set ftpPass=ftpuser
rem 指定FTP服务器地址，或IP
set ftpIp=ftp.******.com
rem 指定FTP服务器的端口号
set ftpPort=22
rem 指定待下载的文件位于FTP服务器的哪个目录
set ftpFolder=/pub/demo/install
rem 指定从FTP下载下来的文件存放到本机哪个目录
set LocalFolder=e:\download

echo open %ftpIp% %ftpPort% > temp.txt
echo user username password >> temp.txt
echo cd %ftpFolder% >> temp.txt
rem 更改本地计算机上的工作目录
echo lcd %LocalFolder% >>temp.txt
echo prompt off >>temp.txt
rem 使用二进制文件传输方式
echo bin >> temp.txt
rem 上载操作举例
echo put "C:\Documents and Settings\user\desktop\patch\upload_dir\temp.rar" "test/temp%date%.rar" >> temp.txt
rem 下载多个文件可以使用mget命令: mget **.zip
rem 若没有 "cd %ftpFolder%" 操作时，可以直接 "get 文件绝对路径包含文件名"
echo get ****.*** >> temp.txt
echo bye >> temp.txt
ftp -n -s:"temp.txt"
del /q "C:\Documents and Settings\user\desktop\patch\upload_dir\temp.rar"
del /q temp.txt