@echo off

echo %0
echo.
echo.

echo being counting space, wait a minute...
echo --------------------------------------
:: /f 参数表示for语句以解析方式工作
:: for语句未明确使用“delims”参数时表示使用空格作为默认分隔符
for /f "tokens=*" %%a in ('dir') do (
:: “&&”前后顺序，前success才执行后语句
:: “tokens=3*”表示“%%b”代表“%%a”中第三个被分隔的字符串，“*”表示第三个字符串后的所有字符串都用一个变量来代替
    echo "%%a"|find " files">nul && for /f "tokens=3*" %%b in ("%%a") do (
        echo the current folder's space is: %%b%%c
    )
)
echo --------------------------------------

echo the children folder's spaces are:
for /f %%a in ('dir/ad/s/b') do (
    for /f "tokens=*" %%b in ('"dir %%a\"') do (
        echo "%%b"|find " files">nul && for /f "tokens=3*" %%c in ("%%b") do (
            echo %%a: %%c%%d
        )
    )
)