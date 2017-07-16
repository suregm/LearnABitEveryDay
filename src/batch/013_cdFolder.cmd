@echo off

echo %0
echo.
echo.

echo Windwos Registry Editor Version 5.00 >temp.reg
echo 正在添加命令到文件夹快捷菜单...
:: 限定接下来操作的注册表键值项的位置，具体是“HKEY_CLASSES_ROOT\Directory\shell\cmd”子键下
echo [HKEY_CLASSES_ROOT\Directory\shell\cmd] >>temp.reg
:: 表示在文件夹右击菜单中显示的菜单项为“在CMD窗口打开文件夹”，“&”符号表示紧跟的字符作为快捷键，即此处的快捷键为【C】
echo @="在&CMD窗口打开文件夹" >>temp.reg
echo [HKEY_CLASSES_ROOT\Directory\shell\cmd\command] >>temp.reg
:: 表示使用命令行打开右击的文件夹
:: “cmd”是命令行窗口程序 “/k”参数表示后面的内容为可运行的命令
:: “%1”代表传递给命令行的右击文件夹，“~dp”表示展开文件或文件夹的完整路径
echo @="cmd /k cd "%~dp1"" >>temp.reg
echo 成功添加命令行窗口打开文件夹命令！
echo.
:: 将建立的注册表文件“temp.reg”导入注册表
regedit /s temp.reg
del /q /f temp.reg >nul
