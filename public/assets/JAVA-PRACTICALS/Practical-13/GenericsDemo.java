class Box<T> {
    private T item;

    public void setItem(T item) {
        this.item = item;
    }

    public T getItem() {
        return item;
    }
}

public class GenericsDemo {
    public static <E> void printArray(E[] array) {
        for (E element : array) {
            System.out.print(element + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Box<Integer> intBox = new Box<>();
        intBox.setItem(1234);
        System.out.println("Box contains Integer: " + intBox.getItem());

        Box<String> strBox = new Box<>();
        strBox.setItem("Generics in Java");
        System.out.println("Box contains String: " + strBox.getItem());

        Integer[] intArray = {1, 2, 3, 4, 5};
        String[] strArray = {"Java", "Python", "C++"};

        System.out.print("Integer Array: ");
        printArray(intArray);

        System.out.print("String Array: ");
        printArray(strArray);
    }
}
