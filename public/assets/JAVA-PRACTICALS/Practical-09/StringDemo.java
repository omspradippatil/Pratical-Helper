public class StringDemo {
    public static void main(String[] args) {
        String str = "Hello Java World";

        System.out.println("Original String: " + str);
        System.out.println("Length: " + str.length());
        System.out.println("Char at index 6: " + str.charAt(6));
        System.out.println("Substring (6, 10): " + str.substring(6, 10));
        System.out.println("To Upper Case: " + str.toUpperCase());
        System.out.println("Contains 'Java': " + str.contains("Java"));
        System.out.println("Replace 'World' with 'Developers': " + str.replace("World", "Developers"));

        StringBuilder sb = new StringBuilder("Programming");
        sb.append(" in Java");
        System.out.println("After append: " + sb);
        sb.insert(11, " Core");
        System.out.println("After insert: " + sb);
        sb.reverse();
        System.out.println("After reverse: " + sb);
        sb.reverse();
        sb.delete(11, 16);
        System.out.println("After delete: " + sb);
    }
}
