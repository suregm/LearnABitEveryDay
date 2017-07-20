@echo off

echo %0
echo.
echo.

echo Windows Registry Editor Version 5.00 >temp.reg

echo 正在根据需要禁用“显示属性”对话框中的相应设置功能...
echo [HKEY_CURRENT_USER\Software\Microsoft\Windows\Current Version\Policies\system] >>temp.reg
rem “背景”和“桌面”标签
echo "NoDispBackgroundPage"=dword:00000001 >>temp.reg
rem “外观”标签
echo "NoDispAppearancePage"=dword:00000001 >>temp.reg
rem “效果”，“Web”和“设置”标签
echo "NoDispSettingsPage"=dword:00000001 >>temp.reg
rem “屏幕保护”标签
echo "NoDispScrSavPage"=dword:00000001 >>temp.reg
echo 成功定制“显示属性”对话框！
echo.
regedit /s temp.reg
del /q /f temp.reg >nul
echo.