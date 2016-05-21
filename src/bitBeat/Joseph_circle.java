package bitBeat;

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
     * countThree()����
     * @param n �˵�����
     * @param start ��ʼ��������� start<n
     * @param m ���еı�ǣ����Դ���n��
     */
    private static void countThree(int n, int start, int m) {
        int i;
        List<Integer> list = new ArrayList<Integer>();
        //��ʼ���б�
        for (i=1; i<=n; i++)
        {
            list.add(i);
        }
        while (list.size() > 0)
        {
            //ʣ���Ԫ����ӵ�����ĩβ
            for (i=0; i<m-1; i++)
            {
                list.add(list.remove(start));
            }
            System.out.println(list.remove(start));
        }
    }
}
