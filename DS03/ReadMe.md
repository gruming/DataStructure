#Java 이중 말단 연결 리스트(double ended linked list) 정리

단순 연결 리스트는 첫번째 노드에 대해 삽입, 삭제 작업을 할 경우 빠른 처리능력을 보여주지만 마지막에 데이터를 삽입, 삭제 할 경우 처음부터 끝까지 순차 검색을 하여 마지막 노드를 찾아야 하기 때문에 저장된 데이터의 수가 많아 질수록 그 효율이 떨어진다.

![ScreenShot](http://cfile4.uf.tistory.com/image/22398C4B5340B33D23B518)

반면에 이중 말단 연결 리스트(double ended linked list)는 헤더에 처음 노드의 참조와 함께 마지막 노드에 대한 참조도 같이 저장함으로써 마지막 노드에 대한 접근을 빠르게 처리할 수 있다는 장점을 가진다.

단순 연결 리스트는 리스트의 처음 노드에 데이터를 저장하고 처음 노드의 데이터를 꺼내는 작업에 좋은 효율을 가지므로 마지막에 저장한 데이터를 먼저 꺼내는 LIFO(last in first out) 구조인 스택(stack)을 구현하기 좋은 방법이고 이중 말단 연결 리스트(double ended linked list)는 리스트의 마지막 노드에 데이터를 저장하고 처음 노드에서 데이터를 꺼내는 작업에 좋은 효율을 가지므로 처음에 저장한 데이터를 먼저 꺼내는 FIFO(first in first out) 구조인 큐(queue)를 구현하기 좋은 방법이다.

리스트에 새로운 데이터를 삽입, 삭제할 경우 단순 연결 리스트와 동일한 방식으로 처리하면 되지만 삽입 또는 삭제되는 노드가 마지막 노드가 되는 경우 헤더의 마지막 노드에 대한 참조를 수정해 주는 부분을 추가적으로 고려하여 작성하면 된다.

##1. 이중 말단 연결 리스트의 기본 구조

	public class MyLinkedList {
    
	    private Header header;
	    private int size;
	    
	    public MyLinkedList(){
	        header = new Header();
	        size = 0;
	    }
	    
	    private class Header{
	        
	        private Node nextNode;
	        private Node lastNode;
	        
	        Header(){
	            nextNode = null;
	            lastNode = null;
	        }
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

Node는 단순 연결 리스트와 구조가 같다. (data와 nextNode를 가진다.)
단순 연결 리스트의 경우 Node 클래스를 헤더와 겸용으로 사용했지만 이중 말단 연결 리스트는 헤더가 마지막 노드를 참조해야 하므로 위와 같이 Header 클래스를 별도로 작성하였다.

Header 클래스는 이중 말단 연결 리스트의 처음 노드를 참조하는 nextNode 와 마지막 노드를 참조하는 lastNode 로 구성된다.

##2. 데이터의 삽입

데이터의 삽입은 리스트의 첫번째 노드나 마지막 노드에서 효율적으로 이루어진다.
삽입한 노드가 리스트의 처음 노드일 경우 헤더의 nextNode 값을 삽입한 노드로 수정하면 되고, 마지막 노드일 경우 헤더의 lastNode 값을 삽입한 노드로 수정하면 된다.

###(1) 리스트의 처음에 삽입

![ScreenShot](http://cfile21.uf.tistory.com/image/234102395340B8BA3710D9)

	public void addFirst(Object data){
        
        Node newNode = new Node(data);
        newNode.nextNode = header.nextNode;
        header.nextNode = newNode;
        size++;
        
        if(newNode.nextNode == null){
            header.lastNode = newNode;
        }
    }

기본 코드는 단순 연결 리스트와 같지만 새로운 노드의 nextNode 값이 null 일 경우
즉, 새로운 노드가 리스트의 마지막 노드일 경우 헤더의 lastNode 를 새로운 노드로 지정하는 부분이 추가되었다.

###(2) 리스트의 마지막에 삽입

![ScreenShot](http://cfile8.uf.tistory.com/image/2657F4355340BAC322D538)

	public void addLast(Object data){
        
        if(header.lastNode == null){
            
            addFirst(data);
        
        }else{
            
            Node newNode = new Node(data);
            header.lastNode.nextNode = newNode;
            header.lastNode = newNode;
            size++;
        }
    }

헤더의 마지막 노드 참조값이 null일 경우 즉, 삽입된 노드가 하나도 없을 경우 addFirst()를 호출하여, 데이터를 삽입한다.
기존에 데이터가 있을 경우 마지막 위치에 삽입하기 위해서 삽입할 새로운 노드를 생성한 후 기존의 마지막 노드의 다음 노드 참조값과 헤더의 마지막 노드 참조값을 새로운 노드로 지정하고 크기를 하나 증가시킨다.

> 단순 연결 리스트에서는 마지막 노드를 찾기 위해 노드의 처음부터 끝까지 순차검색을 했지만 이중 말단 연결 리스트는 헤더에 마지막 노드의 참조가 저장되어 있기 때문에 헤더의 lastNode를 이용하여 직접 접근이 가능하므로 마지막 노드의참조작업이 빠르게 처리된다.


##3. 데이터의 검색

단순 연결 리스트와 동일하다.



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


##4. 데이터의 삭제(추출)

데이터의 삭제는 단순 연결 리스트와 마찬가지로 리스트의 처음 노드에서 효율적으로 이루어진다.
헤더가 가리키는 처음 노드에 대한 참조를 두번째 노드로 바꿔 주면 된다. 또한 삭제된 노드가 마지막 노드였을 경우에는 헤더의 마지막 노드 참조값을 null로 바꿔준다.

![ScreenShot](http://cfile29.uf.tistory.com/image/214F864B5340C48711AB7D)

	public Object removeFirst(){
         
         Node firstNode = getNode(0);
         header.nextNode = firstNode.nextNode;
         size--;
         
         if(header.nextNode == null){
             header.lastNode = null;
         }
         
         return firstNode.data;
     }

기본 코드는 단순 연결 리스트와 같지만 헤더의 nextNode 값이 null일 경우 즉, 삭제한 노드가 리스트의 마지막 노드였을 경우 헤더의 lastNode를 null로 지정하는 부분이 추가된다.

이중 말단 리스트는 lastNode를 참조하고 있어 리스트의 마지막 노드에 삽입도 효율적이었지만, 삭제에서는 마지막 노드를 바로 삭제할 수 없다.
마지막 노드를 삭제하면, 헤더의 lastNode를 마지막-1 노드를 참조해야 되는데, 마지막-1 노드를 찾으려면, 리스트의 앞에서 부터 탐색해 가야한다.