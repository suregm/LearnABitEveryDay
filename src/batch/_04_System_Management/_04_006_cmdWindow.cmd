@echo off

echo %0
echo.
echo.

echo 正在定制命令行窗口的工作风格...
echo [HKEY_LOCAL_MACHINE\System\CurrentControlSet\Control\Session Manager\Environment] >>temp.reg
:: 设置命令行的提示符
echo "PROMPT"="Sure GM:" >>temp.reg
echo [HKEY_CURRENT_USER\Console] >>temp.reg
echo "FullScreen"=dword:00000000 >>temp.reg
echo "FaceName"="楷体" >>temp.reg
:: 命令行历史缓存命令数56条
echo "HistoryBufferSize"=dword:00000038 >>temp.reg
:: 字符编辑模式为插入模式
echo "InsertMode"=dword:00000001 >>temp.reg
:: 历史缓冲区
echo "NumberOfHistoryBuffers"=dword:00000006 >>temp.reg
:: 是否允许命令行状态下启用鼠标编辑功能
echo "QuickEdit"=dword:00000001 >>temp.reg
:: 命令行窗口颜色
echo "PopupColors"=dword:ffffff >>temp.reg
:: 自动填充使能，按【Tab】键
echo "CompletionChar"=dword:00000009 >>temp.reg
:: 前四位表示窗口高度，后四位表示宽度，单位是像素
echo "WindowSize"=dword:00190050 >>temp.reg
echo.
regedit /s temp.reg
del /q /f temp.reg >nul
echo.
echo 成功定制命令行窗口！