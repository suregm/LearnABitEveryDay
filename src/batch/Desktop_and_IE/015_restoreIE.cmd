@echo off

echo %0
echo.
echo.

echo Windows Registry Editor Version 5.00 >temp.reg

echo 正在恢复IE默认主页...
echo [HKEY_USER\.DEFAULT\Software\Policies\Microsoft\Internet Explorer\Control Panel] >>temp.reg
echo "homepage"="0" >>temp.reg
echo [HKEY_CURRENT_USER\Softwar\Microsoft\Internet Explorer\Main] >>temp.reg
echo "Start Page"="about:blank" >>temp.reg
echo 正在恢复IE标题栏
echo "Window Title"="Microsoft Internet Explorer" >>temp.reg
echo 正在清除登录时弹出的广告对话框...
echo [HKEY_CURRENT_USER\Software\Microsoft\Windows NT\CurrentVersion\Winlogon] >>temp.reg
echo "LegalNotice-Caption"=- >>temp.reg
echo "LegalNoticeText"=- >>temp.reg
echo 成功恢复IE默认设置！
echo.
regedit /s temp.reg
del /q /f temp.reg >nul