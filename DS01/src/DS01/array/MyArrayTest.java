package DS01.array;

public class MyArrayTest {

    public static void main(String[] args) {

        // 크기 5의 배열 객체를 생성한다.
        MyArray arr = new MyArray(5);

        // 배열에 데이터를 입력한다.
        for (int i = 1; i <= 5; i++) {
            arr.add("데이터 - " + i);
        }

        // 현재 배열의 입력된 데이터 전체를 출력한다.
        System.out.println("01. 현재 배열의 입력된 데이터 전체를 출력한다. - " + arr);

        // 배열의 0번째 3번째 데이터를 가져와서 출력한다.
        System.out.println("02-1. 배열의 0번째 데이터 출력. - " + arr.get(0));
        System.out.println("02-2. 배열의 3번째 데이터 출력. - " + arr.get(3));

        // index == 3 의 데이터를 제거한다.
        arr.remove(3);
        // 배열의 데이터를 출력한다.
        System.out.println("03. 배열의 3번째 데이터를 제거 한 후 데이터를 출력. - " + arr);

        // index == 3 의 위치에 "데이터 - 4"를 삽입한다.
        arr.add(3, "데이터 - 4");
        System.out.println("04. 배열의 3번째에 '데이터 - 4' 를 삽입한 후 데이터를 출력. - " + arr);

        // 배열의 크기를 오버 하면 에러가 발생한다.!! (단점)
//		arr.add("데이터 - 오버");
//		System.out.println("05. 배열의 마지막에 '데이터 - 오버' 를 삽입한 후 데이터를 출력. - " + arr);

    }
}
