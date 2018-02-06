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




把ms-dos添加到右键
有些工具是命令行模式的（CLI），每次从运行进入cmd，再进入工具所在的文件夹很麻烦。我来告诉大家一个简单的方法，使得windows像linux一样可以随时随时进入CLI。
打开注册表编辑器，找到HKEY_CLASSES_ROOT\Directory\shell，然后选中它，点击鼠标右键，新建一个主键，取名为“DOS”，然后选中新建的主键，在右边双击默认字符串值，在弹出的对话框中输入“转到DOS窗口”，然后选中新建的主键，再建立一个主键，取名为“command”，选中“command”主键，修改默认值为“cmd.exe /k "cd %L"”即可。
注："cd %L"是进入当前文件夹。
在任务管理器中结束掉explorer.exe，再运行explorer.exe，设置就生效了，不需要重启。
现在随便右键一个文件夹，看看有键里是不是出现“转到DOS窗口”了？这样就可以随时随地的打开ms-dos，没有原来那么麻烦了。