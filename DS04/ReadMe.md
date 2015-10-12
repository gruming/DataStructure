#Java 이중 연결 리스트 (doubly linked list) 정리

단순 연결 리스트의 노드는 다음 노드에 대한 참조만 가지고 있기 때문에 노드를 단방향으로 밖에 탐색하지 못한다.
이중 연결 리스트(doubly linked list)의 노드는 다음 노드 뿐만 아니라 이전 노드의 참조까지 추가하여 양방향으로 탐색이 가능하도록 만들어 검색속도를 향상시킬 수 있는 방법을 제공한다.

![ScreenShot](http://cfile25.uf.tistory.com/image/276F30345347E6B013C426)

단순 연결 리스트나 이중 말단 연결 리스트는 처음 노드에서 마지막 노드로의 방향밖에 탐색을 할 수 없으므로 검색하려는 데이터가 리스트의 뒷부분에 위치할 경우 전체 요소의 절반 이상을 순차 접근해야 하므로 검색에 필요한 평균적인 접근이 데이터수/2 가 된다.

이중 연결 리스트는 다음 노드의 참조와 이전 노드의 참조를 모두 가지고 있기 때문에 검색하려는 데이터가 전체 데이터의 앞부분에 있을 경우 순방향(forward)으로 탐색을 하면 되고 검색하려는 데이터가 전체 데이터의 뒷부분에 있을 경우에는 역방향(backward)으로 탐색을 하면 되기 때문에 순방향으로만 접근 가능한 단순 연결 리스트에 비해 두배 정도의 빠른 검색 효율을 가질 수 있다.

순수한 검색기능뿐만 아니라 지정한 위치에 데이터를 삽입하거나 삭제하는 경우에도 해당 위치의 앞, 뒤 노드를 꺼낸 후 참조 노드의 값을 수정해야 하기 때문에 노드의 검색작업이 먼저 이루어지므로 노드의 삽입, 삭제, 검색에 모두 좋은 효율을 가지게 된다.

##1. 이중 연결 리스트 (doubly linked list)의 기본 구조

	public class DoublyLinkedList {
	    
	    private Node header;
	    private int size;
	    public DoublyLinkedList(){
	        header = new Node(null);
	        size=0;
	    }
	    
	    
	    private  class Node{
	        
	        private Object data;
	        private Node previousNode;
	        private Node nextNode;
	        
	        Node(Object data){
	            
	            this.data = data;
	            this.previousNode = null;
	            this.nextNode = null;
	            
	        }
	    }
	
	}

Node 클래스는 데이터를 가지며, 처음 노드와 마지막 노드의 참조를 가리키고 있다.
단순 연결 리스트의 노드와 비교하여 이전 노드의 참조를 나타내는 previousNode가 추가되었다.
첫번째 노드일 경우는 previousNode 값이 null이 되고 마지막 노드일 경우는 nextNode 값이 null이 된다.

Header는 nextNode는 첫번째 노드를 가리키고 previousNode는 마지막 노드를 가리킨다.

##2. 데이터 탐색

	// index위치에서 얻은 노드의 데이터를 반환한다.
    public Object get(int index){
        return getNode(index).data;
    }

    private Node getNode(int index){
        
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException("Index : "+index+", Size : " + size);
        }
        
        Node node = header;
        
        // index 가 리스트 size의 중간 앞이면 순차적으로 탐색한다.
        if(index < (size/2)){
            
            for(int i =0; i<=index; i++){
                node = node.nextNode;
            }
            
        }else{
            // index가 리스트 size의 중간보다 뒤면 뒤에서부터 탐색한다.
            for(int i = size; i > index; i--){
                node = node.previousNode;
        
            }
        }
        
        return node;
    }

index 위치의 데이터를 가져온다.
단순 연결 리스트는 지정한 위치의 노드를 꺼내기 위해서 처음 노드부터 지정한 위치의 노드까지 index의 값만큼 for 구문을 반복하면서 순차접근을 한다.
하지만, 이중 연결 리스트는 지정한 위치의 노드를 꺼내기 위해서 index 값이 데이터의 앞부분에 있을 경우(index < size/2) 처음 노드부터 순방향으로 탐색을 하고 index 값이 데이터의 뒷부분에 있을 경우 (index >= size/2) 마지막 노드부터 역방향으로 탐색을 시도한다.

평균적으로 순방향의 경우나 역방향의 경우 모두 데이터수/4 만큼의 순차접근을 처리하므로 전체적인 효율은 단순 연결 리스트의 2배가 된다.

##3. 데이터의 삽입

###(1) 리스트의 첫번째에 데이터 삽입

![ScreenShot](http://cfile7.uf.tistory.com/image/27029F355347E6342A7AF9)

	// 리스트의 첫번째에 데이터를 삽입한다.
	public void addFirst(Object data){
	        
		// 데이터를 담은 새로운 노드 생성
		Node newNode = new Node(data);    
		
		// 새로운 노드가 다음 노드로 첫번째 노드를 가리킨다.
		newNode.nextNode = header.nextNode;    
		
		// 리스트가 비어있지 않으면
		if(header.nextNode != null){
		    
		    // 첫 노드가 자신의 앞 노드로 새로운 노드를 가리킨다..
		    header.nextNode.previousNode = newNode;
		
		}else{    // 리스트가 비어있으면
		    
		    // 헤더가 마지막 노드를 새로운 노드로 가리키도록 한다.
		    header.previousNode = newNode;
		
		}
		
		// 헤더가 첫번째 노드로 새로운 노드를 가리키도록 한다.
		header.nextNode = newNode;
		size++;
		
	}

신규 데이터를 이중 연결 리스트의 제일 처음에 대입한다.
언뜻 보기에 prev 와 next 참조를 모두 바꿔줘야 하기에 복잡해 보일 수 있다.

###(2) index 위치에 데이터 삽입

![ScreenShot](http://cfile6.uf.tistory.com/image/2544464C5347DFB11A6F37)

	public void add(int index, Object data){
        
        // index가 0 이면 addFirst() 함수를 호출한다.
        if(index ==0){
            
            addFirst(data);
            return;
        }
        
        // 삽입할 index 위치의 앞 노드를 가져온다.
        Node previous = getNode(index-1);
        
        // 삽입할 index의 위치의 다음 노드를 가져온다.
        Node next = previous.nextNode;
        
        // data로 새로운 노드 생성
        Node newNode = new Node(data);
        
        // 앞노드가 새로운 노드를 다음노드로 가리킨다.
        previous.nextNode = newNode;
        
        // 새로운 노드가 앞노드를 이전노드로 가리킨다.
        newNode.previousNode = previous;
        
        //새로운 노드의 다음 노드에 다음노드를 지정한다.
        newNode.nextNode = next;
        
        // 삽입 위치가 마지막 위치가 아니면
        if(newNode.nextNode != null){
            
            // 다음 노드가 새로운 노드를 앞노드로 지정한다.
            next.previousNode = newNode;
        
        }else{ // 삽입 위치가 마지막 이면
            
            // 헤더가 가리키는 마지막 노드가 새로운 노드가 된다..
            header.previousNode = newNode;
        }
        
        size++;
    }

새로운 노드를 삽입할 위치의 이전 노드(previous)와 다음 노드(next)를 꺼낸다.
새로운 노드를 생성한 후 이전 노드의 nextNode와 다음 노드의 previousNode 값을 생성한 노드로 지정한다.

단 이전 노드의 nextNode가 null이 아닐 경우 즉, 삽입할 노드가 마지막 위치가 아닐 경우에만 다음 노드가 존재하므로 다음 노드의 previousNode 값을 지정하고 삽입할 노드가 마지막 노드일 경우에는 헤더의 이전 노드값이 마지막 노드를 가리켜야 하므로 생성한 노드를 지정한다.

그리고 생성한 노드의 previousNode와 nextNode를 각각 previous와 next로 지정한다.
마지막으로 리스트의 사이즈를 하나 증가시킨다.

##4. 데이터의 삭제(추출)

###(1) 첫번째 데이터 삭제

![ScreenShot](http://cfile7.uf.tistory.com/image/25671A4F5347E55326ADA4)

	public Object removeFirst(){
        
        // 첫번째 노드를 가져온다.
        Node firstNode = getNode(0);
        
        // 헤더가 첫 노드로 두번째 노드를 가리킨다.
        header.nextNode = firstNode.nextNode;
        
        // 리스트가 비어있지 않을 때
        if(header.nextNode != null){
            
            // 두번째 노드가 가리키는 앞 노드는 없다.
            firstNode.nextNode.previousNode = null;
        
        }else{ // 리스트가 비게 되면
            
            // 헤더가 가리키는 마지막 노드가 없다.
            header.previousNode = null;
            
        }
        
        size--;
       // 첫번째 노드의 데이터를 반환
        return firstNode.data;
    }

첫번째 노드 삭제는 쉽다.
헤더의 다음 노드를 두번째 노드를 가리키게 하고, 두번째 노드가 앞노드를 아무것도 가리키지 않게 하면 자동적으로 첫번째 노드는 연결에서 끊어져 리스트에서 제거된다.

###(2) index 위치의 데이터 삭제

![ScreenShot](http://cfile27.uf.tistory.com/image/2603A0355347EA330E6351)

	public Object remove(int index){
        
        if(index<0 || index>=size){
            throw new IndexOutOfBoundsException("Index : " + index + ", Size : " + size);
        }else if(index==0){
            return removeFirst();    // index가 0 이면 첫번째 데이터 제거
        }
        
        // 제거할 index 위치의 노드를 가져온다.
        Node removeNode = getNode(index);
        // 제거할 노드의 앞노드를 가져온다.
        Node previous = removeNode.previousNode;
        // 제거할 노드의 뒷노드를 가져온다.
        Node next = removeNode.nextNode;
        
        // 앞노드가 다음노드를 다음으로 가리킨다.
        previous.nextNode = next;
        
        // 제거되는 노드가 마지막 노드가 아니면
        if(next!=null){
            
            // 제거 노드의 뒷노드가 앞노드로  제거 노드 앞 노드를 가리킨다.
            next.previousNode = previous;
            
        }else{ // 제거 노드가 마지막 노드면
            
            // 헤더가 마지막 노드로 앞노드를 가리킨다.
            header.previousNode = previous;
            
        }
        
        size--;
        
        // 제거노드의 데이터를 반환
        return removeNode.data;
    }

먼저 삭제할 노드를 검색하여 꺼낸 뒤 previousNode와 nextNode 속성을 이용하여 이전 노드(previous)와 다음 노드(next)를 꺼낸다. 그리고 이전 노드의 nextNode 값을 next로 지정하고 다음 노드의 previousNode 값을 previous로 지정한다.

단 next 노드가 null이 아닌 경우 즉, 삭제할 노드가 마지막 노드가 아닐 경우에만 다음 노드가 존재하므로 다음 노드의 previousNode 값을 지정하고 삽입할 노드가 마지막 노드일 경우에는 헤더의 이전 노드값이 마지막 노드를 가리켜야 하므로 previous를 지정한다.

마지막으로 리스트의 크기를 하나 감소시키고 삭제한 노드의 데이터를 반환한다.