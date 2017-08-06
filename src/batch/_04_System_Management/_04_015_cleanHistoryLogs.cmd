@echo off

echo %0
echo.
echo.

echo Windows Registry Editor Version 5.00 >temp.reg

:: 不知道注册表项的子键，可以通过反向搜索技术查找

echo 正在清除“运行”对话框中的历史记录...
:: + 增加注册表项，- 删除注册表项
echo [-HKEY_CURRENT_USER\Software\Microsoft\Windows\CurrentVersion\Explorer\RunMRU] >>temp.reg
echo 正在清除windows搜索历史纪录...
echo [-HKEY_CURRENT_USER\Software\Microsoft\Search Assistant\ACMru] >>temp.reg
echo 正在清除访问web站点的历史记录...
echo [-HKEY_CURRENT_USER\Software\Microsoft\Internet Explorer\TypedURLs] >>temp.reg
echo 正在清除访问过的历史文件夹信息...
echo [-HKEY_CURRENT_USER\Software\Microsoft\Windows\CurrentVersion\Explorer\RecentDocs\folder] >>temp.reg
echo 正在清除“最近的文档”中记录的访问文件记录...
echo [-HKEY_CURRENT_USER\Software\Microsoft\Windows\CurrentVersion\Explorer\ComDlg32\OpenSaveMRU] >>temp.reg

echo 禁用windows的历史记录功能...
echo [HKEY_LOCAL_MACHINE\Software\Microsoft\Windows NT\CurrentVersion\Policies\Explorer] >>temp.reg
:: 网上邻居访问过的共享文件夹
echo "NoRecentDocsNetHood"=dword:00000001
echo "NoRecentDocsHistory"=dword:00000001
:: 用户操作
echo "NoInstrumentation"=dword:00000001
:: 最常用的程序
echo "NoStartMenuMFUprogramsList"=dword:00000001
echo.
echo 成功禁用windows的历史记录功能！

echo.
regedit /s temp.reg
del /q /f temp.reg >nul