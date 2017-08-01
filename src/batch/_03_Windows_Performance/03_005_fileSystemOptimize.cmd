@echo off

echo %0
echo.
echo.

echo Windows Registry Editor Version 5.00 >temp.reg

echo 打开windows的应用程序预读功能...
echo [HKEY_LOCAL_MACHINE\SYSTEM\CurrentControlSet\Control\Session Manager\Memory Management\PrefetchParameters] >>temp.reg
:: 取值范围0~4. 0表示禁止预读功能，1表示启用预读应用程序，2表示系统启动时使用预读功能，3表示同时开启应用程序及启动预读功能，4表示系统重启时使用预读功能
echo "EnablePrefetcher"=dword:00000001 >>temp.reg
echo 启用windows磁盘自动优化功能...
echo [HKEY_LOCAL_MACHINE\SOFTWARE\Microsoft\Dfrg\BootOptimizeFunction] >>temp.reg
echo "Enable"="yes" >>temp.reg
echo 增加windows执行文件I/O操作时的缓存容量...
echo [HKEY_LOCAL_MACHINE\SYSTEM\CurrentControlSet\Control\Session Manager\Memory Management] >>temp.reg
:: 设置为“00001000”表示windows执行文件I/O操作时所能使用的最大缓存容量为32MB
echo 'IoPageLockLimit"=dword:00001000 >>temp.reg
echo 增加文件系统缓存提高文件访问速度...
echo.
regedit /s temp.reg
del /q /f temp.reg >nul

