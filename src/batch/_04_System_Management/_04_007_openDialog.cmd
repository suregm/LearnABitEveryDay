@echo off

echo %0
echo.
echo.

echo Windows Registry Editor Version 5.00 >temp.reg

echo 正在定制windows的通用“打开”对话框...
echo [HKEY_CURRENT_USER\Software\Microsoft\Windows\CurrentVersion\Policies\Comdlg32] >>temp.reg
echo "NoBackButton"=dword:00000001 >>temp.reg
:: 不显示最近打开的文件列表
echo "NoFileMru"=dword:00000001 >>temp.reg
echo [HKEY_CURRENT_USER\Software\Microsoft\Windows\CurrentVersion\Policies\Comdlg32\Placesbar] >>temp.reg
:: 位置从上往下
echo "Place0"="e:\download" >>temp.reg
:: 特殊的值代表系统特定的文件夹，如：
:: CommonDocuments(共享文档), CommonMusic, CommonPictures, Desktop, MyComputer,
:: MyDocuments, MyFavorites, MyMusic, MyNetworkPlaces, MyPictures, Printers, ProgramFiles, Recent.
echo "Place1"="MyMusic" >>temp.reg
echo "Place2"="Desktop" >>temp.reg
echo "Place3"="ProgramFiles" >>temp.reg
:: 计算机名为“Dolphin”上的“movie”共享文件夹
echo "Place4"="\\Dolphin\movie" >>temp.reg
echo 成功定制了“打开”对话框的操作界面！
echo.
regedit /s temp.reg
del /q /f temp.reg >nul