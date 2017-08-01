@echo off

echo %0
echo.
echo.

echo 正在定制命令行窗口的工作风格...
echo [HKEY_LOCAL_MACHINE\System\CurrentControlSet\Control\Session Manager\Environment] >>temp.reg
echo "PROMPT"="sunny:" >>temp.reg
echo [HKEY_CURRENT_USER\Console] >>temp.reg
echo "FullScreen"=dword:00000000 >>temp.reg
echo "FaceName"="楷体" >>temp.reg
echo "HistoryBufferSize"=dword:00000038 >>temp.reg
echo "InsertMode"=dword:00000001 >>temp.reg
echo "NumberOfHistoryBuffers"=dword:00000006 >>temp.reg
echo "QuickEdit"=dword:00000001 >>temp.reg
echo "PopupColors"=dword:ffffff >>temp.reg
echo "CompletionChar"=dword:00000009 >>temp.reg
echo "WindowSize"=dword:00190050 >>temp.reg
echo.
regedit /s temp.reg
del /q /f temp.reg >nul
echo.
echo 成功定制命令行窗口！