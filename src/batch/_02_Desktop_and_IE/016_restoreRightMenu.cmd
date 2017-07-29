@echo off

echo %0
echo.
echo.

echo Windows Registry Editor Version 5.00 >temp.reg

echo 正在恢复被禁用的右键菜单...
echo [HKEY_CURRENT_USER\Software\Microsoft\Windows\CurrentVersion\Policies\Explorer] >>temp.reg
echo "NoViewContextMenu"="0" >>temp.reg
echo "NoTrayContextMenu"="0" >>temp.reg
echo "NoViewContextMenu"="0" >>temp.reg
echo 成功恢复右击菜单！
echo.
regedit /s temp.reg
del /q /f temp.reg >nul