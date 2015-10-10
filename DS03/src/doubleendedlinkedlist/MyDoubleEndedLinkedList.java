package doubleendedlinkedlist;

public class MyDoubleEndedLinkedList {

	private Header header;
	private int size;

	public MyDoubleEndedLinkedList() {
		header = new Header();
		size = 0;
	}

	private class Header {

		private Node nextNode;
		private Node lastNode;

		Header() {
			nextNode = null;
			lastNode = null;
		}

	}

	private class Node {
		private Object data;
		private Node nextNode;

		Node(Object data) {
			this.data = data;
			this.nextNode = null;
		}

	}

}
