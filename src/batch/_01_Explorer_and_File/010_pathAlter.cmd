@echo off

echo %0
echo.
echo.

echo 从当前盘符切换到d:\Program Files目录下......

:: pushd 命令直接切换，不论是否在同一盘符
pushd d:\Program Files
echo d:\Program Files目录下包含以下文件夹：
:: "/ad"表示只显示文件夹，"/b"表示以简洁方式显示（即不显示标题信息和摘要）
dir /ad /b
echo.
echo 返回到批处理运行时所在的盘符及路径
:: popd 命令返回到最近一次执行"pushd"命令之前的路径。
:: “popd”与“pushd”通常是成对使用，“push”和“pop”。
:: 可连续多次执行“pushd”或“popd”命令。“pushd”命令将当前路径保存到栈并切换到命令中指定的路径，而“popd”命令是将栈顶层的路径取出并将当前路径设置为该路径。
popd
