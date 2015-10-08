#Java 단순 연결 리스트(simple linked list) 정리


단순 연결 리스트(simple linked list, singly linked list)는 가장 단순한 연결 리스트의 형태로 각 노드들은 다음 노드를 가리키는 하나의 참조만을 갖는다. 다음 노드의 참조밖에 가지고 있지 않으므로 노드의 접근은 한 방향으로만 가능하다.

헤더는 처음 노드의 참조만 가지고 있으며 처음 노드는 두번째 노드, 두번째 노드는 세번째 노드를 가리키고 있으며 마지막 노드가 가리키는 참조값은 null이 된다.
즉, 헤더가 가리키는 노드가 처음 노드며 참조값이 null인 노드가 마지막 노드가 되는 것이다.


##1. 단순 연결 리스트의 기본 구조

![ScreenShot](http://cfile3.uf.tistory.com/image/2115D641533FDD5E3A3CAC)

	public class MyLinkedList {
    
	    private Node header;
	    private int size;
	    
	    public MyLinkedList(){
	        
	        header = new Node(null);
	        size = 0;
	    }
	    
	    private class Node{
	        
	        private Object data;
	        private Node nextNode;
	        
	        Node(Object data){
	            
	            this.data = data;
	            this.nextNode = null;
	        }
	        
	    }

	}
	
Node 클래스는 내부에서만 사용하기 때문에 inner class에 private로 작성되었고, 단순 연결 리스트는 데이터를 담는 Object 객체와 다음 노드를 가리키는 nextNode 참조 변수를 가진다.

MyLinkedList 는 첫번째 노드를 가리키는 header 변수와 저장된 노드의 수를 관리하는 size 변수로 구성되어 있다.

##2. 데이터의 삽입

단순 연결 리스트의 삽입은 리스트의 첫번째 노드에 삽입이 효율적이다. 
(마지막 노드 뒤에 삽입하려면 header에서 마지막 노드까지 따라간 후에 삽입을 해야 한다.)

![ScreenShot](http://cfile27.uf.tistory.com/image/253AEE3C533FDE3929B756)


	public void addFirst(Object data){
        
        Node newNode = new Node(data);
        newNode.nextNode = header.nextNode;
        header.nextNode = newNode;
        size++;
        
    }

addFirst() 메소드는 리스트의 첫번째 위치에 데이터를 삽입하는 메소드이다.
먼저 인자로 받은 데이터를 가지고 Node 를 생성하며 생성한 노드는 삽입되기 이전에 첫번째 노드인 header.nextNode 를 다음 노드로 가리킨다. 그리고 헤더의 가리키는 첫번째 노드를 새로 생성한 노드를 참조하도록 지정한다.
새로운 노드가 삽입되었으므로 크기를 하나 증가시킨다.

##3. 데이터의 검색

데이터의 검색은 지정한 순번의 노드를 리스트의 처음부터 순차적으로 접근하여 찾은 후 해당 노드의 data 값을 반환하도록 작성한다.

	public Object get(int index){
	        return getNode(index).data;
	}
    
    private Node getNode(int index){
        
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException("Index : " + index + ", Size : " + size);
        }
        
        Node node = header.nextNode;
        for(int i =0; i < index; i++){
            node = node.nextNode;
        }
        
        return node;
    }

지정한 위치의 데이터를 가져오기 위해서는 get() 메소드를 사용한다.
get() 메소드에서는 getNode() 메소드를 사용해서 지정한 index 의 노드를 가져와서 해당 노드의 data 를 반환한다.

getNode() 메소드는 인자의 index 값이 정상 범위의 값인지 확인한 후, header에서 부터 해당 index의 수만큼 nextNode 로 이동한다. index 번째의 nextNode를 반환한다.

##4. 데이터의 삭제(추출)

데이터의 삭제는 리스트의 첫번째 노드에서 삭제시 효율적으로 이루어진다. 첫번째 노드의 데이터를 삭제시 헤더가 가리크는 처음 노드에 대한 참조를 두번째 노드로 바꿔 주면 된다.

![ScreenShot](http://cfile23.uf.tistory.com/image/23676D34533FE6E030A7F4)

	public Object removeFirst(){
        
        Node firstNode = getNode(0);
        header.nextNode = firstNode.nextNode;
        size--;
        return firstNode.data;
        
    }

먼저 삭제할 노드인 처음 노드를 꺼낸 뒤 헤더의 처음 노드를 가리키는 nextNode 값을 두번째 노드로 지정한다.
데이터를 한건 삭제했으므로 리스트의 크기를 하나 감소 시키고 삭제한 노드의 데이터를 반환한다.





