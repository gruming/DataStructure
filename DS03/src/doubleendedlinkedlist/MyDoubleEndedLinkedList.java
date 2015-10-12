package doubleendedlinkedlist;

/**
 * 
 * 이중 말단 연결 리스트 ( double ended linked list )
 * 
 * -- 단순 연결 리스트는 첫 번째 노드에 대해 삽입, 삭제 작업을 할 경우 빠른 처리능력을 보여주지만 마지막에 데이터를 삽입, 삭제 할
 * 경우 처음부터 끝까지 순차 검색을 하여 마지막 노드를 찾아야 하기 때문에 저장된 데이터의 수가 많아 질수록 그 효율이 떨어진다.
 * 
 * 반면에 이중 말단 연결 리스트(double ended linked list)는 헤더에 처음 노드의 참조와 함께 마지막 노드에 대한 참조도
 * 같이 저장함으로써 마지막 노드에 대한 접근을 빠르게 처리할 수 있다는 장점을 가진다.
 * 
 * -- <단순연결리스트> 는 리스트의 처음 노드에 데이터를 저장하고 처음 노드의 데이터를 꺼내는 작업에 좋은 효율을 가지므로 마지막에 저장한
 * 데이터를 먼저 꺼내는 <LIFO>(last in first out) 구조인 <스택>(stack)을 구현하기 좋은 방법이고,
 * 
 * <이중말단연결리스트> 는 리스트의 마지막 노드에 데이터를 저장하고 처음 노드에서 데이터를 꺼내는 작업에 좋은 효율을 가지므로 처음에 저장한
 * 데이터를 먼저 꺼내는 <FIFO>(first in first out) 구조인 <큐>(queue)를 구현하기 좋은 방법이다.
 * 
 * -- 리스트에 새로운 데이터를 삽입, 삭제할 경우 단순 연결 리스트와 동일한 방식으로 처리하면 되지만
 * 
 * 삽입 또는 삭제되는 노드가 마지막 노드가 되는 경우 헤더의 마지막 노드에 대한 참조를 수정해 주는 부분을 추가적으로 고려하여 작성하면
 * 된다.
 * 
 * @author gruming-new
 *
 */
public class MyDoubleEndedLinkedList { // FIFO( First In first Out ) = 큐

	private Header header;
	private int size;

	public MyDoubleEndedLinkedList() {
		header = new Header();
		size = 0;
	}

	/**
	 * 이중 말단 연결 리스트 <해더> 클래스
	 * 
	 * <클래스> 단순 연결 리스트의 경우 Node클래스를 헤더와 겸용으로 사용했지만 이중 말단 연결 리스트는 헤더가 마지막 노드를
	 * 참조해야 하므로 Header클래스를 별도로 작성하였다.
	 * 
	 * <구성> 이중 말단 연결 리스트의 처음 노드를 참조하는 firstNode 와 마지막 노드를 참조하는 lastNode 로 구성된다.
	 * 
	 * 
	 * @author gruming-new
	 *
	 */
	private class Header {

		private Node firstNode;
		private Node lastNode;

		Header() {
			firstNode = null;
			lastNode = null;
		}

	}

	/**
	 * 이중 말단 연결 리스트 <노드> 클래스
	 * 
	 * Node 는 단순 연결 리스트와 구조가 갔다.
	 * 
	 * @author gruming-new
	 *
	 */
	private class Node {

		private Object data;
		private Node nextNode;

		Node(Object data) {
			this.data = data;
			this.nextNode = null;
		}

	}

	// //////////////////////////////////////////////////////
	// 데이터 검색
	// //////////////////////////////////////////////////////

	/**
	 * 데이터 검색
	 * 
	 * -- 단순 연결 리스트와 동일
	 * 
	 * @param index
	 * @return
	 */
	public Object get(int index) {
		return getNode(index).data;
	}

	// 첫번째 노드의 데이터를 반환한다.
	public Object getFirst() {
		return get(0);
	}

	/**
	 * 데이터 검색
	 * 
	 * -- 단순 연결 리스트와 동일
	 * 
	 * @param index
	 * @return
	 */
	public Node getNode(int index) {

		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index : " + index
					+ ", Size : " + size);
		}

		Node node = header.firstNode;
		for (int i = 0; i < index; i++) {
			node = node.nextNode;
		}

		return node;

	}

	// 해당 데이터의 노드 위치 index를 반환한다.
	public int getNodeIndex(Object obj) {

		if (size <= 0) {
			return -1;
		}

		int index = 0;
		Node node = header.firstNode;
		Object nodeData = node.data;

		// header에서 부터 순차적으로 nodeData와 값을 비교한다.
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

	// //////////////////////////////////////////////////////
	// 데이터 삽입
	// //////////////////////////////////////////////////////

	/**
	 * 리스트 처음에 삽입
	 * 
	 * -- 기본 코드는 단순 연결 리스트와 같지만 새로운 노드의 nextNode 값이 null 일 경우 즉, 새로운 노드가 리스트의
	 * 마지막 노드일 경우 헤더의 lastNode 를 새로운 노드로 지정하는 부분이 추가되었다.
	 * 
	 * @param data
	 */
	public void addFirst(Object data) {

		Node newNode = new Node(data);
		newNode.nextNode = header.firstNode;
		header.firstNode = newNode;
		size++;

		if (newNode.nextNode == null) {
			header.lastNode = newNode;
		}

	}

	/**
	 * 리스트의 마지막에 삽입
	 * 
	 * -- 헤더의 마지막 노드 참조값이 null 일 경우 즉, 삽입된 노드가 하나도 없을 경우 addFirst()를 호출하여, 데이터를
	 * 삽입한다.
	 * 
	 * -- 기존에 데이터가 있을 경우 마지막 위치에 삽입하기 위해서 삽입할 새로운 노드를 생성한 후 기존의 마지막 노드의 다음 노드
	 * 참조값과 헤더이 마지막 노드 참조값을 새로운 노드로 지정하고 크기를 하나 증가 시킨다.
	 * 
	 * ps. 단순 연결 리스트에서는 마지막 노드를 찾기 위해서 노드의 처음부터 마지막까지 순차검색을 했지만 이중 말단 연결 리스트는
	 * 헤더에 마지막 노드의 참조가 저장되어 있기 때문에 헤더의 lastNode를 이용하여 직접 접근이 가능하므로 마지막 노드의 참조작업이
	 * 빠르게 처리된다.
	 * 
	 * @param data
	 */
	public void addLast(Object data) {

		if (header.lastNode == null) {
			addFirst(data);
		} else {
			Node newNode = new Node(data);
			header.lastNode.nextNode = newNode;
			header.lastNode = newNode;
			size++;
		}

	}

	// index의 위치에 data를 삽입한다.
	public void add(int index, Object data) {

		if (index == 0) {
			addFirst(data);
			return;
		} else if (index == size) {
			addLast(data);
			return;
		} else {

			Node previousNode = getNode(index - 1);
			Node nextNode = previousNode.nextNode;

			Node newNode = new Node(data);
			previousNode.nextNode = newNode;
			newNode.nextNode = nextNode;
			size++;

		}

	}

	// data를 리스트의 마지막에 삽입한다. (기본삽입)
	public void add(Object data) {
		addLast(data);
	}

	// //////////////////////////////////////////////////////
	// 데이터 삭제(추출)
	// //////////////////////////////////////////////////////

	/**
	 * 데이터의 삭제(추출)
	 * 
	 * -- 데이터의 삭제는 단순 연결 리스트와 마찬가지로 리스트의 처음 노드에서 효율적으로 이루어진다.
	 * 
	 * 헤더가 가리키는 처음 노드에 대한 참조를 두번째 노드로 바꿔 주면 된다. 또한 삭제된 노드가 마지막 노드였을 경우에는 헤더의 마지막
	 * 노드 참조값을 null로 바꿔준다.
	 * 
	 * -- 기본 코드는 단순 연결 리스트와 같지만 헤더의 firstNode 값이 null 일 경우 즉, 삭제한 노드가 리스트의 마지막
	 * 노드였을 경우 헤더의 lastNode를 null로 지정하는 부분이 추가된다.
	 * 
	 * -- 이중 말단 리스트는 lastNode 를 참조하고 있어 리스트의 마지막 노드에 삽입도 효율적이였지만, 삭제에서는 마지막 노드를
	 * 바로 삭제 할 수 없다. 마지막 노드를 삭제하면, 헤더의 lastNode를 마지막 -1 노드를 참조해야 되는데, 마지막 -1 노드를
	 * 찾으려면, 리스트의 앞에서 부터 탐색해 가야한다.
	 * 
	 * @return
	 */
	public Object removeFirst() {

		Node firstNode = getNode(0);
		header.firstNode = firstNode.nextNode;
		size--;

		if (header.firstNode == null) {
			header.lastNode = null;
		}

		return firstNode.data;

	}

	// index위치의 노드를 제거하고 데이터를 반환한다.
	public Object remove(int index) {

		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index : " + index
					+ ", Size : " + size);
		} else if (index == 0) {
			return removeFirst();
		}

		Node previousNode = getNode(index - 1);
		Node removeNode = previousNode.nextNode;
		Node nextNode = removeNode.nextNode;

		previousNode.nextNode = nextNode;
		size--;

		if (previousNode.nextNode == null) {
			header.lastNode = previousNode;
		}

		return removeNode.data;

	}

	// data가 있는 노드를 제거 한다.
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

	// 리스트의 데이터를 String으로 반환한다.
	public String toString() {

		StringBuffer result = new StringBuffer("[");
		Node node = header.firstNode;

		if (node != null) {
			result.append(node.data);
			node = node.nextNode;

			while (node != null) {
				result.append(", ");
				result.append(node.data);
				node = node.nextNode;
			}

		}

		result.append("]");

		return result.toString();

	}

}
