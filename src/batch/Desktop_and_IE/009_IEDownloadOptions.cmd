@echo off

echo %0
echo.
echo.

echo Windows Registry Editor Version 5.00 >temp.reg
echo 启用IE浏览器的下载功能...
echo [HKEY_CURRENT_USER\Software\Microsoft\Windows\CurrentVersion\Internet Settings\2ones\3] >>temp.reg
:: 键值项“1803”设置为0，表示启用浏览器的下载功能
:: 若禁用下载功能，则 "1803"=dword:00000003
echo "1803"=dword:00000000
echo 设置下载默认保存目录
echo [HKEY_CURRENT_USER\Software\Microsoft\Internet Explorer] >>temp.reg
echo "Download Directory"="D:\IEDownload" >>temp.reg
echo 开启多线程下载功能，并设置最大线程数
echo [HKEY_CURRENT_USER\Software\Microsoft\Windows\CurrentVersion\Internet Settings] >>temp.reg
:: 多线程下载时，最大线程数目不应超过10个
echo "MaxConnectionPerServer"="10" >>temp.reg
echo 下载完成后弹出通知对话框
echo [HKEY_CURRENT_USER\Software\Microsoft\Internet Explorer\Main] >>temp.reg
echo "NotifyDownloadComplete"="yes"

echo.
regedit /s temp.reg
del /q /f temp.reg >nul