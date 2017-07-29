@echo off

echo %0
echo.
echo.

echo 正在定制开始菜单中显示的内容...
echo Windows Registry Editor Version 5.00 >temp.reg
rem 限制键值项所处子键的位置
echo [HKEY_CURRENT_USER\Software\Microsoft\Windows\CurrentVersion\Policies\Explorer] >>temp.reg
rem 有如下设置选项：
:: StartMenuLogOff 是否隐藏开始菜单中的“注销”命令
:: NoRun
:: NoFind
:: NoClose 是否隐藏“关机”命令
:: NoRecentDocsMenu
:: NoSMHelp 帮助和支持
:: Start_ShowControlPanel 设置为1时表示隐藏
:: Start_ShowMyComputer
:: NoSMMyDocs
:: NoStartMenuMyMusic
:: NoSMMyPictures
:: Start_ShowNetConn 网上邻居
:: NoUserNameInStartMenu
:: NoStartMenuMorePrograms 所有程序
:: NoStartMenuMFUProgramsList 程序列表
:: NoStartBanner 值设置为二进制的“01000000”时，表示隐藏“单击这里开始“的提示信息
echo "StartMenuLogOff"=dword:00000001 >>temp.reg
echo "NoStartBanner"=hex:01,00,00,00 >>temp.reg
regedit /s temp.reg
del /q /f temp.reg >nul
echo.
echo 成功定制经典开始菜单!