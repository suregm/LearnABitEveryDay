package bitBeat.NM.com.suregm.exam;

public class CollectionModel {
    // code back!



    private final int COUNT = 5000;
    NmObject[] c = new NmObject[COUNT];
    private static int sum = 0;

    private CollectionModel() {
    }

    private boolean exist(NmObject obj) {
        for(int i = 0; i < sum; i++) {
            if(c[i] == obj) {
                return true;
            }
        }
        return false;
    }

    private int getIndex(NmObject obj) {
        for(int i = 0; i < sum; i++) {
            if(c[i] == obj) {
                return i;
            }
        }
        return -1;
    }

    public void add(NmObject obj) {
        if(exist(obj)) {
            System.out.println("添加的" + obj + "重复了，请重新添加！");
        } else {
            sum++;
            if(sum > COUNT) {
                System.out.println("超出容量最大值，请删减后再添加！");
            } else {
                c[sum] = obj;
                System.out.println("添加了" + obj);
            }
        }
    }

    public void delete(NmObject obj) {
        if(exist(obj)) {
            int index = getIndex(obj);
            for(int i = index; i < sum; i++) {
                c[i] = c[i +  1];
            }
            System.out.println("删除了" + obj);
        }
    }

    public void printAll() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < sum; i++) {
            sb.append(c[i]).append(", ");
        }
        System.out.println("所有对象为：" + sb.toString().substring(0, sb.toString().length() - 2));
    }



    public static void main(String[] args) {
        CollectionModel c = new CollectionModel();
        c.add(new Ne("NE233"));
        c.printAll();
    }

    public void cong(String... wishes) {

    }
}
