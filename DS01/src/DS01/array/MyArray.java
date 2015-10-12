package DS01.array;

/**
 * 배열 클래스
 * 
 * @author gruming-new
 *
 *         -- 2015.10.07
 */
public class MyArray {

	private Object[] data; // 데이터
	private int count; // 배열 위치

	public MyArray(int maxSize) {

		if (maxSize < 0) {
			throw new IllegalArgumentException("Illegal Capacity: " + maxSize);
		}

		// 객체 생성시 받은 maxSize 크기의 배열을 만든다.
		this.data = new Object[maxSize];
		this.count = 0;

	}

	/**
	 * 배열의 마지막에 obj를 삽입한다.
	 * 
	 * @param obj
	 */
	public void add(Object obj) {

	if (count >= data.length)
			throw new ArrayIndexOutOfBoundsException(count + " >= "
			+ data.length);

	// 배열의 마지막에 obj를 삽입한다.
	data[count++] = obj;

}

	/**
	 * index의 위치에 obj를 삽입한다. index 뒤의 데이터는 한칸씩 밀려난다.
	 * 
	 * @param index
	 * @param obj
	 */
	public void add(int index, Object obj) {

		if (count >= data.length)
			throw new ArrayIndexOutOfBoundsException(count + " >= "
					+ data.length);

		// index 뒤의 데이터는 한칸씩 밀려난다.
		for (int i = index; i < count; i++)
			data[i + 1] = data[i];

		count++;

		// index의 위치에 obj를 삽입한다.
		data[index] = obj;

	}

	/**
	 * index 위치의 데이터를 삭제한다. index 뒤의 데이터는 한칸씩 당겨진다.
	 * 
	 * @param index
	 */
	public void remove(int index) {

		if (index >= count)
			throw new ArrayIndexOutOfBoundsException(index + " >= " + count);
		else if (index < 0)
			throw new ArrayIndexOutOfBoundsException(index);

		// index 뒤의 데이터는 한칸씩 당겨진다.
		for (int i = index; i < count - 1; i++)
			data[i] = data[i + 1];

		count--;

		// 마지막 데이터는 널값을 대입한다.
		data[count] = null;

	}

	/**
	 * obj를 찾아서 제거한다.
	 * 
	 * @param obj
	 */
	public void remove(Object obj) {

		int index = find(obj);

		remove(index);

	}

	/**
	 * index의 데이터를 가져온다.
	 * 
	 * @param index
	 * @return
	 */
	public Object get(int index) {

		if (index >= count)
			throw new ArrayIndexOutOfBoundsException(index + " >= " + count);

		return data[index];

	}

	/**
	 * 배열에서 obj를 찾아서 위치 index를 반환한다.
	 * 
	 * @param obj
	 * @return
	 */
	public int find(Object obj) {

		int index;

		for (index = 0; index < count; index++) {

			if (data[index].equals(obj)) {
				break;
			} else if (index == count - 1) {
				index = -1;
				break;
			}

		}

		return index;

	}

	/**
	 * 배열의 데이터를 출력한다.
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {

		StringBuffer str = new StringBuffer("[");

		if (count > 0) {
			str.append(data[0]);
		}

		for (int i = 1; i < count; i++) {
			str.append(", ").append(data[i]);
		}

		str.append("]");

		return str.toString();

	}

}
