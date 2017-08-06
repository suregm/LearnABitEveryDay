@echo off

echo %0
echo.
echo.

echo Windows Registry Editor Version 5.00 >temp.reg

echo 正在清除“运行”对话框中的历史记录...
echo [-HKEY_CURRENT_USER\Software\Microsoft\Windows\CurrentVersion\Explorer\RunMRU] >>temp.reg
echo 正在清除windows搜索历史纪录...
echo [-HKEY_CURRENT_USER\Software\Microsoft\Search Assistant\ACMru] >>temp.reg
echo 正在清除访问web站点的历史记录...
echo [-HKEY_CURRENT_USER\Software\Microsoft\Internet Explorer\TypedURLs] >>temp.reg
echo 正在清除访问过的历史文件夹信息...
echo [-HKEY_CURRENT_USER\Software\Microsoft\Windows\CurrentVersion\Explorer\RecentDocs\folder] >>temp.reg
echo 正在清除“最近的文档”中记录的访问文件记录...
echo [-HKEY_CURRENT_USER\Software\Microsoft\Windows\CurrentVersion\Explorer\ComDlg32\OpenSaveMRU] >>temp.reg
echo.
regedit /s temp.reg
del /q /f temp.reg >nul