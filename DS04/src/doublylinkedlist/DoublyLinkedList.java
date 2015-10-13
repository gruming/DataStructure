package doublylinkedlist;

/**
 * 이중 연결 리스트 (doubly linked list) 정리
 * <p/>
 * -- 단순 연결 리스트의 노드는 다음 노드에 대한 참조만 가지고 있기 때문에 노드를 단방향으로 밖에 탐색하지 못한다.
 * 이중 연결 리스트(doubly linked list)의 노드는 다음 노드 뿐만 아니라 이전 노드의 참조까지 추가하여 양방향으로
 * 탐색이 가능하도록 만들어 검색속도를 향상시킬 수 있는 방법을 제공한다.
 * <p/>
 * -- 단순 연결 리스트나 이중 말단 연결 리스트는 처음 노드에서 마지막 노드로의 방향밖에 탐색을 할 수 없으므로 검색하려는
 * 데이터가 리스트의 뒷부분에 위치할 경우 전체 요소의 절반 이상을 순차 접근해야 하므로 검색에 필요한 평균적인 접근이
 * 데이터수/2 가 된다.
 * <p/>
 * -- 이중 연결 리스트는 다음 노드의 참조와 이전 노드의 참조를 모두 가지고 있기 때문에 검색하려는 데이터가 전체 데이터의 앞부분에
 * 있을 경우 순방향(forward)으로 탐색을 하면 되고 검색하려는 데이터가 전체 데이터의 뒷부분에 있을 경우에는 역방향(backward)으로
 * 탐색을 하면 되기 때문에 순방향으로만 접근 가능한 단순 연결 리스트에 비해 두배 정도의 빠른 검색 효율을 가질 수 있다.
 * <p/>
 * -- 순수한 검색기능 뿐만 아니라 지정한 위치에 데이터를 삽입하거나 삭제하는 경웅에도 해당 위치의 앞, 뒤 노드를 꺼낸 후 참조 노드의
 * 값을 수정해야 하기 때문에 노드의 검색 작업이 먼저 이루어지므로 노드의 삽입, 삭제, 검색에 모두 좋은 효율을 가지게 된다.
 */
public class DoublyLinkedList {

    private Node header;
    private int size;


    /**
     * Instantiates a new Doubly linked list.
     */
    public DoublyLinkedList() {

        header = new Node(null);
        size = 0;

    }

    private class Node {

        private Object data;
        private Node previousNode;
        private Node nextNode;

        /**
         * Instantiates a new Node.
         *
         * @param data the data
         */
        public Node(Object data) {
            this.data = data;
            this.previousNode = null;
            this.nextNode = null;
        }

    }
}
