@echo off

echo %0
echo.
echo.

rem 使用FTP 到某台机器上进行文件上传、下载的操作，在命令方式下基本逻辑是：
rem 1. ftp 主机名
rem 2. 输入用户名
rem 3. 输入密码
rem 4. 切换模式(asc 或者是 bin)。binary：设置文件传输模式为binary，缺省为ascii。binary模式不会对数据进行任何处理，ascii模式会将回车换行
rem 5. 上传(put)或下载(get)
rem 6. 退出(bye or quit)


:: ftp命令
:: ftp [-v] [-d] [-i] [-n] [-g] [-s:FileName] [-a] [-w:WindowSize] [-A] [Host]
:: 参数：
:: -v：禁止显示远程服务器响应。
:: -d：启用调试、显示在客户端和服务器之间传递的所有ftp命令。
:: -i：多个文件传送时关闭交互提示。
:: -n：禁止自动登录到初始连接。
:: -g：禁用文件名组，它允许在本地文件和路径名中使用通配符字符（*和?），全局文件名。
:: -s:FileName：指定包含ftp命令的文本文件；当ftp启动后，这些命令将自动运行。该参数中不允许有空格。使用该开关而不是重定向（>）。
:: -a：在捆绑数据连接时使用任何本地接口。
:: -w:WindowSize：替代默认大小为4096的传送缓冲区。
:: -A：匿名登陆。
:: Host：指定要连接到远程计算机的计算机名或IP地址。如果指定，计算机必须是最后一个参数。


ftp命令详解

1 !
     Escape to the shell.
     从 ftp 子系统临时退出到命令提示符下。在本地机中执行交互shell，exit回到ftp环境，如：!ls*.zip.
     要返回到 ftp 子系统，请在命令提示符下键入 exit。
     语法：![cmd[args]]
2 append
     Append to a file
     使用当前文件类型设置将本地文件附加到远程计算机上的文件。若未指定远程系统文件名，则使用本地文件名。
     语法：append LocalFile [RemoteFile]
3 ascii
     Set ascii transfer type.
     设定以ASCII方式传送文件(缺省值)
     语法：ascii
4 bell
     Beep when command completed
     每个文件传输命令执行完后，计算机响铃一次。
     语法：bell
5 binary | bin
     Set binary transfer type
     设定以二进制方式传送文件。
     语法：binary
6 bye
     Terminate ftp session and exit.
     终止主机FTP进程,并退出。
     语法：bye
7 cd
     Change remote working directory.
     更改远程计算机上的工作目录。
     语法：cd RemoteDirectory
8 close
     Terminate ftp session
     结束与远程服务器进行的 FTP 会话，并停留在 ftp> 提示符下。
     语法：close
9 delete
     Delete remote file.
     删除远程计算机上的文件。
     语法：delete Remotefile
10 dir
     List contents of remote directory
     显示远程计算机上的目录文件和子目录列表。如果有[local-file]参数则将结果存入本地文件local-file。
     语法：dir [remote-directory] [local-file]
11 disconnect
     Terminate ftp session.
     从远程计算机断开连接，同时保留 ftp> 提示符。
     语法：disconnect
12 get
     Receive file.
     使用当前文件传送类型将远程文件复制到本地计算机。
     语法：get [remote-file] [local-file]
13 glob
     Toggle metacharacter expansion of local file names.
     切换本地文件名的通配符扩展。设置mdelete，mget，mput的文件名扩展，缺省时不扩展文件名，同命令行的-g参数。
     语法：glob
14 hash
     Toggle printing `#‘ for each buffer transferred.
     切换已传输的每个数据块的数字签名 (#) 打印。每传输1024个字节，显示一个hash符号（#）
     语法：hash
15 help
     Print local help information.
     显示 ftp 子命令说明。
     语法：help [Command]
16 lcd
     Change local working directory.
     更改本地计算机上的工作目录。默认情况下，工作目录是启动ftp的目录。
     语法：lcd [directory]
17 literal
     Send arbitrary ftp command.
     将参数逐字发送到远程 FTP 服务器。单一 FTP 答复代码返回。
     语法：literal Argument [ ...]
18 ls
     List contents of remote directory.
     在远程目录上显示文件简短目录和子目录。如果有[LocalFile]参数则将目录列表存入本地文件LocalFile。
     语法：ls [RemoteDirectory] [LocalFile]
19 mdelete
     Delete multiple files.
     删除远程计算机上的多个文件。
     语法：mdelete RemoteFiles [ ...]
20 mdir
     List contents of multiple remote directories.
     显示远程目录中的文件和子目录列表。
     语法：mdir RemoteFiles [ ...] LocalFile
21 mget
     Get multiple files.
     使用当前文件传送类型将远程多个文件复制到本地计算机。
     语法：mget RemoteFiles [ ...]
22 mkdir
     Get multiple files
     创建远程计算机上的目录。
     语法：mkdir directory
23 mls
     List contents of multiple remote directories.
     在远程目录上显示文件简短目录和子目录。
     语法：mls RemoteFiles [ ...]LocalFile
24 mput
     Send multiple files.
     使用当前多个文件传送类型将本地文件复制到远程计算机上。
     语法：mput LocalFiles [ ...]
25 open
     Connect to remote tftp.
     与指定的 FTP 服务器连接。
     语法：open Computer(IP) [Port]
26 prompt
     Force interactive prompting on multiple commands.
     在 prompt 的开模式和关模式之间切换。
     设置多个文件传输时的交互提示（默认为提示，批量下载在每个文件都会提示，输入Y才能进行下一文件的传输，prompt可取消交互提示）
     语法：prompt
27 put
     Send one file.
     使用当前一个文件传送类型将本地文件复制到远程计算机上。
     语法：put LocalFile [RemoteFile]
28 pwd
     Print working directory on remote machine.
     打印远程计算机上的当前目录。
     语法：pwd
29 quit
     Terminate ftp session and exit.
     结束与远程计算机的 FTP 会话并退出 ftp 到命令提示符下。
     语法：quit
30 quote
     Send arbitrary ftp command.
     将参数逐字发送到远程 FTP 服务器。单一 FTP 答复代码返回。
     语法：quote Argument [ ...]
31 recv
     Receive file.
     使用当前文件传送类型将远程文件复制到本地计算机。
     语法：recv RemoteFile [LocalFile]
32 remotehelp | rhelp
     Get help from remote server.
     显示远程命令的帮助。
     语法：remotehelp [Command]
33 rename
     Rename file.
     重命名远程文件。
     语法：rename FileName NewFileName
34 rmdir
     Remove directory on the remote machine.
     删除远程目录。
     语法：mdir Directory
35 send
     Send one file.
     使用当前文件传送类型将本地文件复制到远程计算机上。
     语法：send LocalFile [RemoteFile]
36 status
     Show current status.
     显示 FTP 连接的当前状态。
     语法：status
37 trace
     Toggle packet tracing.
     切换数据包跟踪。
     语法：trace
38 type
     Set file transfer type.
     设置或显示文件传送类型。缺省为ascii，如：type binary，设置二进制传输方式。
     语法：type [TypeName]
39 user
     Send new user information
     指定远程计算机的用户。
     语法：user UserName [Password] [Account]
40 verbose
     Toggle verbose mode.
     切换 verbose 模式。
     语法：verbose

?[cmd]：同help。

umask[newmask]：将远程服务器的缺省umask设置为newmask，如：umask 3。

tick：设置传输时的字节计数器。

tenex：将文件传输类型设置为TENEX机的所需的类型。

system：显示远程主机的操作系统类型。

sunique：将远程主机文件名存储设置为唯一(与runique对应)。

struct[struct-name]：将文件传输结构设置为struct-name，缺省时使用stream结构。

size file-name：显示远程主机文件大小，如：site idle 7200。

site arg1，arg2...：将参数作为SITE命令逐字发送至远程ftp主机。

sendport：设置PORT命令的使用。

runique：设置文件名唯一性存储，若文件存在，则在原文件后加后缀..1，.2等。

reset：清除回答队列。

restart marker：从指定的标志marker处，重新开始get或put，如：restart 130。

rstatus[file-name]：若未指定文件名，则显示远程主机的状态，否则显示文件状态。

reget remote-file[local-file]：类似于get，但若local-file存在，则从上次传输中断处续传。

proxy ftp-cmd：在次要控制连接中，执行一条ftp命令，该命令允许连接两个ftp服务器，以在两个服务器间传输文件。第一条ftp命令必须为open，以首先建立两个服务器间的连接。

passive：进入被动传输方式。

newer file-name：如果远程机中file-name的修改时间比本地硬盘同名文件的时间更近，则重传该文件。

nlist[remote-dir][local-file]：显示远程主机目录的文件清单，并存入本地硬盘的local-file。

nmap[inpattern outpattern]：设置文件名映射机制，使得文件传输时，文件中的某些字符相互转换，如：nmap $1.$2.$3[$1，$2].[$2，$3]，则传输文件a1.a2.a3时，文件名变为a1，a2。该命令特别适用于远程主机为非UNIX机的情况。

ntrans[inchars[outchars]]：设置文件名字符的翻译机制，如ntrans 1R，则文件名LLL将变为RRR。

mode[modename]：将文件传输方式设置为modename，缺省为stream方式。

modtime file-name：显示远程主机文件的最后修改时间。

macdef macro-name：定义一个宏，遇到macdef下的空行时，宏定义结束。

image：设置二进制传输方式(同binary)。

idle[seconds]：将远程服务器的休眠计时器设为[seconds]秒。

form format：将文件传输方式设置为format，缺省为file方式。

debug[debug-value]：设置调试方式，显示发送至远程主机的每条命令，如：deb up 3，若设为0，表示取消debug。

cr：使用asscii方式传输文件时，将回车换行转换为回行。

cdup：进入远程主机目录的父目录。

chmod mode file-name：将远程主机文件file-name的存取方式设置为mode，如：chmod 777 a.out。

case：在使用mget时，将远程主机文件名中的大写转为小写字母。

$ macro-ame[args]：执行宏定义macro-name.

account[password]：提供登录远程系统成功后访问系统资源所需的补充口令。









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








cls
echo.
echo.
echo ---------请选择要执行的操作---------
echo ----1.输入数字1并回车，创建普通的FTP站点
echo ----2.输入数字2并回车，创建具有指定IP地址和端口的FTP站点
echo ----3.输入数字3并回车，删除指定的FTP站点
echo ----4.输入数字4并回车，为FTP站点创建虚拟目录
echo ----5.输入数字5并回车，删除FTP站点中的虚拟目录
echo ----6.输入其他字符，直接退出批处理
echo.
echo.

set /p num=请选择要执行的操作：
if "%num%"=="1" (
    echo.
    set /p sourcePath=请输入站点资源的路径：
    set /p siteName=请输入站点的名称：
    echo.

    :: iisftp /create %sourcePath% %siteName%
    :: iisftp /query
    :: iisftp /start %siteName%
    :: iisftp /pause %siteName%
    :: iisftp /stop %siteName%

    iisftp /create %sourcePath% %siteName%
    goto exit
)
if "%num%"=="2" (
    echo.
    set /p sourcePath=请输入站点资源的路径：
    set /p siteName=请输入站点的名称：
    set /p ip=请输入站点使用的ip地址：
    set /p port=请输入站点使用的端口号：
    echo.
    iisftp /create %sourcePath% %siteName% /I %ip% /b %port%
    goto exit
)
if "%num%"=="3" (
    cls
    :: 显示当前所有的FTP站点信息，包括站点名、IP、Port及当前状态等
    iisftp /query
    set /p removedSite=请输入要删除的站点名称：
    echo.
    iisftp /delete %removedSite%
    goto exit
)
if "%num%"=="4" (
    cls
    iisftp /query
    set /p siteName=请输入要创建虚拟目录的FTP站点名称：
    set /p sourceVD=请输入虚拟目录的资源路径：
    set /p vdName=请输入虚拟目录的名称：
    echo.
    :: 创建虚拟目录
    iisftpdr /create %siteName% %vdName% %sourceVD%
    goto exit
)
if "%num%"=="5" (
    cls
    iisftp /query
    set /p siteName=请输入要删除虚拟目录的FTP站点：
    echo.
    iisftpdr /query %siteName%
    set /p vdName=请输入要删除的虚拟目录：
    echo.
    iisftpdr /delete %siteName%\%vdName%
    goto exit
)
echo.
echo 你输入了%num%字符，批处理自动退出...

:exit
set num=
set sourcePath=
set siteName=
set ip=
set port=
set removedSite=
set sourceVD=
set vdName=