package simplelinkedlist;

/**
 * 단순 연결 리스트(simple linked list) 정리
 * 
 * 단순 연결 리스트(simple linked list, singly linked list)는 가장 단순한 연결 리스트의 형태로 각 노드들은
 * 다음 노드를 가리키는 하나의 참조만을 갖는다. 다음 노드의 참조밖에 가지고 있지 않으므로 노드의 접근은 한 방향으로만 가능하다.
 * 
 * 헤더는 처음 노드의 참조만 가지고 있으며 처음 노드는 두번째 노드, 두번째 노드는 세번째 노드를 가리키고 있으며 마지막 노드가 가리키는
 * 참조값은 null이 된다. 즉, 헤더가 가리키는 노드가 처음 노드며 참조값이 null인 노드가 마지막 노드가 되는 것이다.
 * 
 * @author gruming-new
 *
 */
public class MyLinkedList {

	private Node header;
	private int size;

	public MyLinkedList() {
		header = new Node(null);
		size = 0;
	}

	// 단순 연결 리스트 노드
	private class Node {

		private Object data;
		private Node nextNode;

		public Node(Object data) {
			this.data = data;
			this.nextNode = null;
		}

	}

	/**
	 * 데이터 검색 (index이용)
	 * 
	 * <기능> 데이터의 검색은 지정한 순번의 노드를 리스트의 처음부터 순차적으로 접근하여 찾은 후 해당 노드의 data 값을 반환하도록
	 * 작성한다.
	 * 
	 * <메소드> getNode() 메소드를 사용해서 지정한 index 의 노드를 가져와서 해당 노드의 data 를 반환한다.
	 * 
	 * @param index
	 * @return 데이터
	 */
	public Object get(int index) {
		// index 위치의 노드 데이터를 반환한다.
		return getNode(index).data;

	}

	/**
	 * 데이터 검색 (index이용)
	 * 
	 * <메소드> 인자의 index 값이 정상 범위의 값인지 확인한 후, header에서 부터 해당 index의 수만큼 nextNode 로
	 * 이동한다. index 번째의 nextNode를 반환한다.
	 * 
	 * @param index
	 * @return 노드클래스
	 */
	public Node getNode(int index) {
		// index 위치의 노드를 반환한다.
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index : " + index
					+ ", Size : " + size);
		}

		Node node = header.nextNode;

		for (int i = 0; i < index; i++) {
			node = node.nextNode;
		}

		return node;

	}

	// 첫번째 노드의 데이터를 반환한다.
	public Object getFirst() {
		return get(0);
	}

	// 해당 데이터의 노드 위치 index를 반환한다.
	public int getNodeIndex(Object obj) {

		if (size <= 0) {
			return -1;
		}

		int index = 0;
		Node node = header.nextNode;
		Object nodeData = node.data;

		// header 에서 부터 순차적으로 nodeData와 값을 비교한다.
		while (!obj.equals(nodeData)) {

			node = node.nextNode;

			if (node == null) {
				return -1;
			}

			nodeData = node.data;
			index++;

		}

		return index;

	}

	/**
	 * 리스트의 첫번째 위치에 데이터 삽입
	 * 
	 * <기능> 단순 연결 리스트의 삽입은 리스트의 첫번째 노드에 삽입이 효율적이다. (마지막 노드 뒤에 삽입하려면 header에서 마지막
	 * 노드까지 따라간 후에 삽입을 해야 한다.)
	 * 
	 * <메소드> 먼저 인자로 받은 데이터를 가지고 Node 를 생성하며 생성한 노드는 삽입되기 이전에 첫번째 노드인
	 * header.nextNode 를 다음 노드로 가리킨다. 그리고 헤더의 가리키는 첫번째 노드를 새로 생성한 노드를 참조하도록
	 * 지정한다. 새로운 노드가 삽입되었으므로 크기를 하나 증가시킨다.
	 * 
	 * @param data
	 */
	public void addFirst(Object data) {

		Node newNode = new Node(data);
		newNode.nextNode = header.nextNode;
		header.nextNode = newNode;
		size++;

	}

	// index 위치에 data를 삽입한다.
	public void add(int index, Object data) {

		if (index == 0) {
			addFirst(data);
			return;
		}

		Node previousNode = getNode(index - 1);
		Node nextNode = previousNode.nextNode;

		Node newNode = new Node(data);
		previousNode.nextNode = newNode;
		newNode.nextNode = nextNode;
		size++;

	}

	// 리스트의 마지막에 data를 삽입한다.
	public void addLast(Object data) {
		add(size, data);
	}

	// 리스트의 data를 삽입 시에 기본으로 마지막에 data를 삽입하기 위해 addLast()메소드를 호출한다.
	public void add(Object data) {
		addLast(data);
	}

	/**
	 * 데이터의 삭제(추출)
	 * 
	 * <기능> 데이터의 삭제는 리스트의 첫번째 노드에서 삭제시 효율적으로 이루어진다. 첫번째 노드의 데이터를 삭제시 헤더가 가리크는 처음
	 * 노드에 대한 참조를 두번째 노드로 바꿔 주면 된다.
	 * 
	 * <메소드> 먼저 삭제할 노드인 처음 노드를 꺼낸 뒤 헤더의 처음 노드를 가리키는 nextNode 값을 두번째 노드로 지정한다.
	 * 데이터를 한건 삭제했으므로 리스트의 크기를 하나 감소 시키고 삭제한 노드의 데이터를 반환한다.
	 * 
	 * @return
	 */
	public Object removeFirst() {

		Node firstNode = getNode(0);
		header.nextNode = firstNode.nextNode;
		size--;
		return firstNode.data;

	}

	// index 위치의 노드를 제거하고 데이터를 반환한다.
	public Object remove(int index) {

		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("index : " + index
					+ ", Size : " + size);
		} else if (index == 0) {
			return removeFirst();
		}

		Node previouseNode = getNode(index - 1);
		Node removeNode = previouseNode.nextNode;
		Node nextNode = removeNode.nextNode;

		previouseNode.nextNode = nextNode;
		size--;

		return removeNode.data;

	}

	// 리스트에서 data를 가진 노드를 제거하고 제거여부를 반환한다.
	public boolean remove(Object data) {

		int nodeIndex = getNodeIndex(data);

		if (nodeIndex == -1) {
			return false;
		} else {
			remove(nodeIndex);
			return true;
		}

	}

	// 리스트의 마지막 노드를 제거하고 데이터를 반환한다.
	public Object removeLast() {
		return remove(size - 1);
	}

	// 리스트의 크기를 반환한다.
	public int size() {
		return size;
	}

	// 리스트의 데이터 String으로 반환한다.
	public String toString() {

		StringBuffer result = new StringBuffer("[ ");

		Node node = header.nextNode;

		if (node != null) {
			result.append(node.data);
			node = node.nextNode;

			while (node != null) {
				result.append(", ");
				result.append(node.data);
				node = node.nextNode;
			}

		}

		result.append(" ]");

		return result.toString();
	}

}
