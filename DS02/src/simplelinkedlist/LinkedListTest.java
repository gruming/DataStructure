package simplelinkedlist;

public class LinkedListTest {

	public static void main(String[] args) {

		MyLinkedList list = new MyLinkedList();

		list.add("숫자");
		list.add("일자");
		list.add(400);
		list.add(500);

		System.out.println("최초 값 : " + list);

		list.add(2, 300);
		System.out.println("2번지에 '300'데이터를 추가 함 : " + list);

		list.addFirst(600);
		System.out.println("리스트 처음에 '600'데이터를 추가 함 : " + list);

		System.out.println("리스트 2번지에 있는 데이터를 조회 함 : " + list.get(2));

		list.remove(2);
		System.out.println("리스트의 2번지를 삭제함 : " + list);

		list.remove(new Integer(400));
		System.out.println("리스트의 데이터 중 '400'값을 가지고 있는 노드를 삭제함 : " + list);

		list.remove("숫자");
		System.out.println("리스트의 데이터 중 '숫자'값을 가지고 있는 노드를 삭제함 : " + list);

		list.removeFirst();
		System.out.println("리스트의 첫번째 노드를 삭제함 : " + list);

		list.removeLast();
		System.out.println("리스트의 마지막 노드를 삭제함 : " + list);

		System.out.println("크기 : " + list.size());

		boolean bo = list.remove("숫자");
		System.out.println("다시 리스트의 데이터 중 '숫자'값을 가지고 있는 노드를 삭제함 : " + list
				+ ", " + bo);

	}

}
