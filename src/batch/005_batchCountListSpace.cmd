@echo off

echo %0
echo.
echo.

echo being counting space, wait a minute...
echo --------------------------------------
:: /f 参数表示for语句以解析方式工作
:: for语句未明确使用“delims”参数时表示使用空格作为默认分隔符
:: “tokens”参数只有一个“*”，表示代表执行结果中的每一行
:: “%%a”代表“dir”命令中包含目录大小信息的那一行，形如“XX files YY bytes”
for /f "tokens=*" %%a in ('dir') do (
:: “&&”前后顺序，前success才执行后语句
:: “tokens=3*”表示“%%b”代表“%%a”中第三个被分隔的字符串，“*”表示第三个字符串后的所有字符串都用一个变量*来代替
    echo "%%a"|find " files">nul && for /f "tokens=3*" %%b in ("%%a") do (
        :: “%%c”代表从“%%a”中解析出的字符串bytes
        echo the current folder's space is: %%b%%c
    )
)
echo --------------------------------------

echo the children folder's spaces are:
:: “dir/ad/s/b”表示以最简洁的方式显示当前目录下所有目录及其子目录（包含完整路径名）
for /f %%a in ('dir/ad/s/b') do (
    :: “dir %%a\”用于显示每个子文件夹的内容从而获得目录大小，“%%a\”代表当前目录下的子文件夹
    for /f "tokens=*" %%b in ('"dir %%a\"') do (
        echo "%%b"|find " files">nul && for /f "tokens=3*" %%c in ("%%b") do (
            echo %%a: %%c%%d
        )
    )
)