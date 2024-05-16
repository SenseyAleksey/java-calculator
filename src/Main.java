import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите выражение: ");
        String input = scanner.nextLine();

        try {
            System.out.println(Calculator.calc(input));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
