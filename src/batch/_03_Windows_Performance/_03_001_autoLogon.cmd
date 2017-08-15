@echo off

echo %0
echo.
echo.

echo Windows Registry Editor Version 5.00 >temp.reg

echo 正在设置自动登录当前系统的账户...
echo [HKEY_LOCAL_MACHINE\SOFTWARE\Microsoft\Windows NT\CurrentVersion\Winlogon] >>temp.reg
echo "Autoadminlogon"="1" >>temp.reg
echo "Defaultusername"="NormalAccount" >>temp.reg
echo "Defaultpassword"="NoPrivilege" >>temp.reg
echo.
echo 成功将“NormalAccount”账户设置为当前自动登录的账户！
regedit /s temp.reg
del /q /f temp.reg >nul
