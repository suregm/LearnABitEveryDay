@echo off

echo %0
echo.
echo.

:: G:\Code\LearnABitEveryDay!\src\batch>004_createShortcut.cmd 003_fullPath.cmd
:: 对应位置%0 %1
:: 没有指定 %1 位置的参数
if "%1"=="" (
    echo the correct syntax is:
    echo %0 filename
    echo parameter 'filename' means the name of the referred file.
    echo.
    echo the batch cannot create shortcut, for not referring to file.
    :: 直接跳转到“end”标号处，结束批处理的运行
    goto end
)

echo being creating shortcut......
:: “%~n1”代表只取“%1”的文件名，即文件名相同，扩展名使用“url”，扩展符号“~n”表示文件名
set shortcutName=%~n1.url
:: “>”写入/添加到文件
echo [InternetShortcut] >%shortcutName%
echo URL=%~f1 >>%shortcutName%
:: 使用图标库中的第1个图标作为自己的图标
echo IconIndex=1 >>%shortcutName%
:: 快捷方式使用系统图标库“shell32.dll”
echo IconFile=%windir%\system32\shell32.dll >>%shortcutName%
:: “>nul”表示将“copy”命令产生的信息发送到空设备，从而避免直接显示在命令行窗口
copy %shortcutName% "%userprofile%\desktop" >nul
echo.
echo the shortcut of the file %1 %shortcutName% has been created successfully.
set shortcutName=
:: 定义标号“end”，直接跳转
:end
echo.
echo.
pause