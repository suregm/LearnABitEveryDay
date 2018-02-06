@echo off

rem 使用FTP 到某台机器上进行文件上传、下载的操作，在命令方式下基本逻辑是：
rem 1. ftp 主机名
rem 2. 输入用户名
rem 3. 输入密码
rem 4. 切换模式(asc 或者是 bin)
rem 5. 上传(put)或下载(get)
rem 6. 退出(bye or quit)

:: ftp命令
:: ftp -n -s:"ftpCMD.bat"

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