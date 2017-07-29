@echo off

echo %0
echo.
echo.

echo Windows Registry Editor Version 5.00 >temp.reg
echo 正在按要求定制IE菜单...
echo [HKEY_CURRENT_USER\SOFTWARE\Policies\Microsoft\Internet Explorer\Restrictions] >>temp.reg
echo "NoFileOpen"="1" >>temp.reg
echo "NoBrowserSaveAs"="1" >>temp.reg
echo "NoBrowserClose"="1" >>temp.reg
:: 是否禁用从“文件”菜单中打开新窗口
echo "NoFileNew"="1" >>temp.reg
echo "NoViewSource"="1" >>temp.reg
:: 全屏显示
echo "NoTheaterMode"="1" >>temp.reg
echo "NoBrowserOptions"="1" >>temp.reg
echo "NoHelpMenu"="1" >>temp.reg
echo [HKEY_CURRENT_USER\Software\Microsoft\Windows\CurrentVersion\Policies\Explorer] >>temp.reg
:: 是否禁用“文件->新建”子菜单中的所有命令
echo "NoExpandedNewMenu"="1" >>temp.reg
:: 是否禁用“查看->工具栏”子菜单中的所有命令
echo "NoBandCustomize"="1" >>temp.reg
echo IE菜单定制完毕，重新启动IE浏览器即可生效！
echo.
regedit /s temp.reg
del /q /f temp.reg >nul