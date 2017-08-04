@echo off

echo %0
echo.
echo.

echo Windows Registry Editor Version 5.00 >temp.reg

echo 正在解决Windows系统故障后自动重启问题...
echo [HKEY_LOCAL_MACHINE\SYSTEM\CurrentControlSet\Control\CrashControl] >>temp.reg
echo "AutoReboot"=dword:00000000 >>temp.reg
echo "SendAlert"=dword:00000001 >>temp.reg
:: 进行内存转储，取值范围0~3
:: 0 不进行内存转储，1 完全内存转储，2 核心内存转储，3 小内存转储。
echo "CrashDumpEnabled"=dword:00000003 >>temp.reg
echo "LogEvent"=dword:00000001 >>temp.reg
:: 在转储内存时覆盖现有文件
echo "Overwrite"=dword:00000001 >>temp.reg
:: 核心内存转储及完全内存转储时使用的文件目录
echo "DumpFile"="C:\dump\failure.dmp" >>temp.reg
:: 小内存转储时使用的目录
echo "MinidumpDir"="C:\dump" >>temp.reg
echo 成功解决了Windows出现问题自动重启问题！
echo.
regedit /s temp.reg
del /q /f temp.reg >nul