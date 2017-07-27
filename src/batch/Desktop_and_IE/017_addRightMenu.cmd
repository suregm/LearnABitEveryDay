@echo off

echo %0
echo.
echo.

echo Windows Registry Editor Version 5.00 >temp.reg

echo 在文件夹和驱动器右键菜单中添加菜单命令...
:: 在 "HKEY_LOCAL_MACHINE\SOFTWARE\CLASSES\Directory\shell" 键下添加“重启计算机”子键
echo [+HKEY_LOCAL_MACHINE\SOFTWARE\CLASSES\Directory\shell\重启计算机] >>temp.reg
:: "+" 添加子键
echo [+HKEY_LOCAL_MACHINE\SOFTWARE\CLASSES\Directory\shell\重启计算机\command] >>temp.reg
:: 接下来的键值项限制在子键下
echo [HKEY_LOCAL_MACHINE\SOFTWARE\CLASSES\Directory\shell\重启计算机\command] >>temp.reg
:: 在文件夹、“开始”按钮及驱动器右键菜单中添加命令，执行“ EXIT WINDOWS EXEC”
echo @="C:\Windows\Rundll.exe user.exe, EXIT WINDOWS EXEC" >>temp.reg
echo 在任意类型文件的右击菜单中添加菜单命令...
echo [+HKEY_CLASSES_ROOT\*\shell] >>temp.reg
echo [+HKEY_CLASSES_ROOT\*\shell\记事本] >>temp.reg
echo [+HKEY_CLASSES_ROOT\*\shell\记事本\command] >>temp.reg
echo [HKEY_CLASSES_ROOT\*\shell\记事本\command] >>temp.reg
echo @="notepad %1" >>temp.reg
echo.
regedit /s temp.reg
del /q /f temp.reg >nul