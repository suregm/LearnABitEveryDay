@echo off

echo %0
echo.
echo.

:: 当用户以缩略图方式查看具有大量图片的文件夹时，现实速度会比较慢，此时可以通过以下批处理命令来提高缩略图显示性能。

echo Windows Registry Editor Version 5.00 >temp.reg

echo 正在调整系统整体参数，以使系统性能得到综合性的提高...
echo [HKEY_CURRENT_USER\Software\Microsoft\Windows\CurrentVersion\Explorer] >>temp.reg
:: 使用最小的尺寸显示缩略图，“ThumbnailSize”取值范围32~256
echo "ThumbnailSize"=dword:00000020 >>temp.reg
:: 十六进制的“32”表示以原图质量的50%显示缩略图，“ThumbnailQuality”取值范围为50~100
echo "ThumbnailQuality"=dword:00000030 >>temp.reg
echo [HKEY_CURRENT_USER\Software\Microsoft\Windows\CurrentVersion\Explorer\Advanced] >>temp.reg
:: 允许系统生成缩略图缓冲文件“Thumbs.db”，该文件可以大幅度地提高显示缩略图的速度
echo "DisableThumbnailCache"=dword:00000000 >>temp.reg
echo 成功提高缩略图查看方式时的显示性能！
echo.
regedit /s temp.reg
del /q /f temp.reg >nul