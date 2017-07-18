@echo off

echo %0
echo.
echo.

echo 正在定制经典菜单中的内容...
echo Windows Registry Editor Version 5.00 >temp.reg
:: 限制键值项所处子键的位置
echo [HKEY_CURRENT_USER\Software\Microsoft\Windows\CurrentVersion\Policies\Explorer] >>temp.reg
:: 设置键值项，还有如下键值项
:: NoSetFolders 是否隐藏开始菜单中的“设置”命令
:: NoFavoritesMenu 是否隐藏“收藏夹”
:: NoRecentDocsHistory
:: MaxRecentDocs
:: EditLevel 加分隔线，若设置为00000001则表示删除分隔线
:: NoChangeStartMenu 是否允许用户对开始菜单进行修改
echo "NoSetFolders"=dword:00000001 >>temp.reg
echo "NoFavoritesMenu"=dword:00000001 >>temp.reg
regedit /s temp.reg
del /q /f temp.reg >nul
echo.
echo 成功定制经典开始菜单!

rem 批处理执行完毕后，必须重启计算机设置才能生效