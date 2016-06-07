package learn.TestCollection;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by sure GM on 2016/6/5 17:51.
 */
public class TestLinkedList {

	private static void printLinkedList(LinkedList<String> linkedList) {
		System.out.printf("当前链表元素集合：");
//		for (int i = 0; i < linkedList.size(); i++) {
//			System.out.printf(linkedList.get(i) + " ");
//		}
		// 列表可以使用java中foreach用法
		for (String s : linkedList) {
			System.out.printf(s + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		ArrayList<String> arrayList = new ArrayList<String>();
		// LinkedList比ArrayList更强大
		LinkedList<String> linkedList = new LinkedList<String>();
		linkedList.add("张三");
		linkedList.add("李四");
		linkedList.add("王五");
		linkedList.add("李四");
		linkedList.add("赵六");
		printLinkedList(linkedList);

		System.out.println("第一个“李四”出现的索引位置是：" + linkedList.indexOf("李四"));
		System.out.println();

		System.out.println(linkedList.peekFirst()); // 获取但不移除列首元素
		printLinkedList(linkedList);
		System.out.println(linkedList.peekLast());  // 获取但不移除列尾元素
		printLinkedList(linkedList);
		System.out.println();

		System.out.println(linkedList.pollFirst()); // 获取并移除列首元素
		printLinkedList(linkedList);
		System.out.println(linkedList.pollLast());  // 获取并移除列尾元素
		printLinkedList(linkedList);

		// 更多方法是【数据结构】中的链表处理知识
		linkedList.pop();   // 移除列首
		printLinkedList(linkedList);
		linkedList.push("张三");  // 压入列首
		printLinkedList(linkedList);
	}
}
