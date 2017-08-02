@echo off

echo %0
echo.
echo.

echo 正在新建用于锁定命令行窗口的lockCMD.bat批处理文件，请稍候...
echo.

echo. >lockCMD.bat

echo @echo off >>lockCMD.bat
echo echo. >>lockCMD.bat
:: “setlocal”启用局部临时变量，遇到“endlocal”时自动取消自定义的变量
echo setlocal >>lockCMD.bat
echo set /a times=3 >>lockCMD.bat

echo :checkPassword >>lockCMD.bat
echo set /p password=请输入密码： >>lockCMD.bat
echo set /a times=times-1 >>lockCMD.bat
echo if "%%password%%"=="admin" goto continue >>lockCMD.bat
echo if "%%times%%"=="0" goto exit >>lockCMD.bat
echo echo 输入的密码不正确，请重新输入，还有%%times%%次机会！ >>lockCMD.bat
echo goto checkPassword >>lockCMD.bat

echo :exit >>lockCMD.bat
echo echo. >>lockCMD.bat
echo echo 你无权进入命令行状态！ >>lockCMD.bat
echo exit >>lockCMD.bat

echo :continue >>lockCMD.bat
:: cls 清除命令行窗口中显示的内容
echo cls >>lockCMD.bat
echo echo 欢迎回到命令行窗口工作！ >>lockCMD.bat

echo endlocal >>lockCMD.bat

:: 默认情况下，系统安装目录 %windir% 已经加入可执行搜索路径，因此可以直接运行“lockCMD.bat”而不需要指定详细的完整路径
copy lockCMD.bat %windir%
echo 正在设置注册表，从而让命令行窗口一打开便进入锁定状态...
echo Windows Registry Editor Version 5.00 >temp.reg
echo [HKEY_LOCAL_MACHINE\SOFTWARE\Microsoft\Command Processor] >>temp.reg
:: 打开命令行窗口Command Processor的同时运行批处理文件
echo "AutoRun"="lockCMD.bat" >>temp.reg
echo 成功锁定命令行，只有输入正确的密码才可使用！

regedit /s temp.reg
del /q /f temp.reg >nul
del /q /f lockCMD.bat >nul