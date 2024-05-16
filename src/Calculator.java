import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Calculator {
    private static final Map<Character, Integer> romanToIntMap = new HashMap<>();
    private static final TreeMap<Integer, String> intToRomanMap = new TreeMap<>();

    static {
        romanToIntMap.put('I', 1);
        romanToIntMap.put('V', 5);
        romanToIntMap.put('X', 10);
        romanToIntMap.put('L', 50);
        romanToIntMap.put('C', 100);
        romanToIntMap.put('D', 500);
        romanToIntMap.put('M', 1000);

        intToRomanMap.put(1000, "M");
        intToRomanMap.put(900, "CM");
        intToRomanMap.put(500, "D");
        intToRomanMap.put(400, "CD");
        intToRomanMap.put(100, "C");
        intToRomanMap.put(90, "XC");
        intToRomanMap.put(50, "L");
        intToRomanMap.put(40, "XL");
        intToRomanMap.put(10, "X");
        intToRomanMap.put(9, "IX");
        intToRomanMap.put(5, "V");
        intToRomanMap.put(4, "IV");
        intToRomanMap.put(1, "I");
    }

    public static String calc(String input) {
        String[] tokens = input.split(" ");

        if (tokens.length != 3) {
            return "Некорректный формат ввода. Ожидается два операнда и один оператор.";
        }

        String num1 = tokens[0];
        String operator = tokens[1];
        String num2 = tokens[2];

        boolean isRoman = isRomanNumeral(num1) && isRomanNumeral(num2);
        boolean isArabic = isArabicNumeral(num1) && isArabicNumeral(num2);

        if (!isRoman && !isArabic) {
            return "Числа должны быть либо римскими, либо арабскими.";
        }

        if (isRoman && isArabic) {
            return "Нельзя использовать обе системы счисления одновременно.";
        }

        int number1 = isRoman ? romanToInt(num1) : Integer.parseInt(num1);
        int number2 = isRoman ? romanToInt(num2) : Integer.parseInt(num2);

        if (number1 < 1 || number1 > 10 || number2 < 1 || number2 > 10) {
            return "Числа должны быть в диапазоне от 1 до 10 включительно.";
        }

        int result;
        switch (operator) {
            case "+":
                result = number1 + number2;
                break;
            case "-":
                result = number1 - number2;
                break;
            case "*":
                result = number1 * number2;
                break;
            case "/":
                if (number2 == 0) {
                    return "Деление на ноль невозможно.";
                }
                result = number1 / number2;
                break;
            default:
                return "Некорректная операция: " + operator;
        }

        return isRoman ? intToRoman(result) : String.valueOf(result);
    }

    private static boolean isRomanNumeral(String s) {
        return s.matches("[IVXLCDM]+");
    }

    private static boolean isArabicNumeral(String s) {
        return s.matches("\\d+");
    }

    private static int romanToInt(String roman) {
        int result = 0;
        int prevValue = 0;

        for (int i = roman.length() - 1; i >= 0; i--) {
            int value = romanToIntMap.get(roman.charAt(i));

            if (value < prevValue) {
                result -= value;
            } else {
                result += value;
            }
            prevValue = value;
        }

        return result;
    }

    private static String intToRoman(int num) {
        if (num <= 0) {
            return "Римские числа не могут быть меньше или равны нулю.";
        }

        StringBuilder roman = new StringBuilder();
        for (Integer key : intToRomanMap.descendingKeySet()) {
            while (num >= key) {
                roman.append(intToRomanMap.get(key));
                num -= key;
            }
        }

        return roman.toString();
    }
}
