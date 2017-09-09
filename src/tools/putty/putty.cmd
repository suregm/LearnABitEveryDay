@echo off

echo %0
echo.
echo.

set /p portForwarded=Please input forwarded port:
set /p remote_mysqlhost=Please input Database IP:
set /p portDB=Please input Database port:
set /p userName=Please input Database user:
set /p password=Please input Database password:


start %~dp0putty.exe -L %portForwarded%:localhost:%portDB% -pw %password% %userName%@%remote_mysqlhost%


:: putty.exe [-ssh | -telnet | -rlogin | -raw] [user@]host
:: Example: putty -ssh -l ossuser -pw Changeme_123 -P 2222 127.0.0.1

::  -V        print version information and exit
::  -pgpfp    print PGP key fingerprints and exit
::  -v        show verbose messages
::  -load sessname  Load settings from saved session
::  -ssh -telnet -rlogin -raw            force use of a particular protocol
::  -P port   connect to specified port
::  -l user   connect with specified username
::  -batch    disable all interactive prompts The following options only apply to SSH connections:  -pw passw login with specified password
::  -D [listen-IP:]listen-port           Dynamic SOCKS-based port forwarding
::  -L [listen-IP:]listen-port:host:port            Forward local port to remote address
::  -R [listen-IP:]listen-port:host:port            Forward remote port to local address
::  -X -x     enable / disable X11 forwarding
::  -A -a     enable / disable agent forwarding
::  -t -T     enable / disable pty allocation
::  -1 -2     force use of particular protocol version
::  -4 -6     force use of IPv4 or IPv6
::  -C        enable compression
::  -i key    private key file for authentication
::  -m file   read remote command(s) from file
::  -s        remote command is an SSH subsystem (SSH-2 only)
::  -N        don't start a shell/command (SSH-2 only)

:: 自动登录后执行默认的命令：
:: putty [-pw password] [-m file] user@ip_addr
:: -m参数，其后面跟着的是当前目录下存在的一个shell脚本，不是在远程的计算机上，而是在putty程序的相同目录中
