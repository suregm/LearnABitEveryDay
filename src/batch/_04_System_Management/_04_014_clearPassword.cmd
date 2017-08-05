@echo off

echo %0
echo.
echo.

:: 无法进入windows系统时
:: 启动系统时按【F8】键，在菜单中选择进入DOS命令行，然后切换到批处理目录，输入“clearPassword.bat”，按【Enter】键即可执行清除密码操作。
echo 正在删除系统中当前使用的SAM文件及相关注册表文件...
echo.
:: "sam"文件存放帐户信息，包含加密后的密码
del c:\windows\system32\config\sam /f /q >nul
del c:\windows\system32\config\system /f /q >nul
del c:\windows\system32\config\software /f /q >nul
del c:\windows\system32\config\security /f /q >nul
del c:\windows\system32\config\default /f /q >nul
echo 正在复制系统的备份SAM文件及相关注册表文件...
echo.
copy c:\windows\repair\sam c:\windows\system32\config\ >nul
copy c:\windows\repair\system c:\windows\system32\config\ >nul
copy c:\windows\repair\software c:\windows\system32\config\ >nul
copy c:\windows\repair\security c:\windows\system32\config\ >nul
copy c:\windows\repair\default c:\windows\system32\config\ >nul
echo 成功清除windows所有的帐户及密码信息，现在重启计算机即可直接进入系统！