import java.util.ArrayList;
import java.util.List;

/**
 * Created by sure GM on 2015/10/11.
 */
public class Joseph_circle
{
    public static void main(String[] args) throws Exception
    {
        countThree(50, 0, 3);
    }

    /**
     * countThree()方法
     * @param n 人的总数
     * @param start 开始报数的序号 start<n
     * @param m 出列的标记（可以大于n）
     */
    private static void countThree(int n, int start, int m) {
        int i;
        List<Integer> list = new ArrayList<Integer>();
        //初始化列表
        for (i=1; i<=n; i++)
        {
            list.add(i);
        }
        while (list.size() > 0)
        {
            //剩余的元素添加到链表末尾
            for (i=0; i<m-1; i++)
            {
                list.add(list.remove(start));
            }
            System.out.println(list.remove(start));
        }
    }
}
