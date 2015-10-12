package doubleendedlinkedlist;

public class MyDoubleEndedLinkedListTest {

	public static void main(String[] args) {

		MyDoubleEndedLinkedList list = new MyDoubleEndedLinkedList();

		list.add("가나다");
		list.add("100");
		list.add("라마바");
		list.add("200");
		list.add("사아자");

		System.out.println(list);

		list.add(2, 300);
		list.addFirst("처음으로");

		System.out.println(list);

		System.out.println(list.get(3));

		list.remove(2);
		list.remove("사아자");
		System.out.println(list);

		list.removeFirst();
		System.out.println(list);

		list.removeLast();
		System.out.println(list);

		System.out.println("크기 : " + list.size());

	}

}
