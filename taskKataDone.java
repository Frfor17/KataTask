import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.ArrayList;
import java.lang.Exception;

public class Main {
    
    public static void main(String[] args) throws MyException {
        Scanner eye = new Scanner(System.in); // создание сканера
        System.out.println("input:");                   // ввод
        String task = eye.nextLine();
        String[] parts = task.split(" ");               // деление строки 
        
        String countsArab = "\\b([1-9]|10)\\b";
        String countsRoman = "[IXCVL]+";
        
        Pattern arab = Pattern.compile(countsArab); // создание паттерна регулярок для арабских
        Pattern roman = Pattern.compile(countsRoman);
        
        if (arab.matcher(parts[0]).matches() && arab.matcher(parts[2]).matches() ) { // проверка числа на арабских
            
            switch(parts[1]) { // проводит подсчёт для арабских
                case "+":
                    int summa = Integer.parseInt(parts[0])+Integer.parseInt(parts[2]);
                    System.out.println("output:");
                    System.out.println(summa);
                    break;
                case "-":
                    int summa2 = Integer.parseInt(parts[0])-Integer.parseInt(parts[2]);
                    System.out.println("output:");
                    System.out.println(summa2);
                    break;
                case "*":
                    int summa3 = Integer.parseInt(parts[0])*Integer.parseInt(parts[2]);
                    System.out.println("output:");
                    System.out.println(summa3);
                    break;
                case "/":
                    int summa4 = Integer.parseInt(parts[0])/Integer.parseInt(parts[2]);
                    System.out.println("output:");
                    System.out.println(summa4);
                    break;
            }
            
        } else {
        if ((Pattern.matches(countsRoman,parts[0])) && (Pattern.matches(countsRoman,parts[2]))) { //проверка на римские цифры, но можно обмануть
            char[] romanPartsFirstNumber = parts[0].toCharArray();
            char[] romanPartsSecondNumber = parts[2].toCharArray(); // Разобрали цифры римские на элементы, теперь надо попробовать сложить
            
            
            switch(parts[1]) { // проводит подсчёт для конвертнутых римских чисел и сразу конвертит обратно
                case "+":
                    int summa = convertToArabic(romanPartsFirstNumber)+convertToArabic(romanPartsSecondNumber);
                    System.out.println("output:");
                    System.out.println(convertToRoman(summa));
                    break;
                case "-":
                    int summa2 = convertToArabic(romanPartsFirstNumber)-convertToArabic(romanPartsSecondNumber);
                    System.out.println("output:");
                    System.out.println(convertToRoman(summa2));
                    break;
                case "*":
                    int summa3 = convertToArabic(romanPartsFirstNumber)*convertToArabic(romanPartsSecondNumber);
                    System.out.println("output:");
                    System.out.println(convertToRoman(summa3));
                    break;
                case "/":
                    int summa4 = convertToArabic(romanPartsFirstNumber)/convertToArabic(romanPartsSecondNumber);
                    System.out.println("output:");
                    System.out.println(convertToRoman(summa4));
                    break;
            }
            
        } else {
            throw new MyException("Неподходящий формат!");
        }
        }
        
            
        
            
    }
    public static String convertToRoman(int number) { // конвертит в рим на выходе
        String[] romanSymbols = {"I", "IV", "V", "IX", "X", "XL",};
        String finalRomanString = " ";
        if (number < 1 || number > 100) {
            throw new IllegalArgumentException("Неподходящие числа!");
        }
        
        while(number > 0){
            if(number == 100) {
                finalRomanString = "C";
                number %= 100;
                
            } else if(number >= 90) {
                finalRomanString += "XC";
                number %= 90;
            } else if(number >= 50) {
                finalRomanString += "L";
                number %= 50;
            } else if(number >= 40) {
                finalRomanString += romanSymbols[5];
                number %= 40;
            } else if(number >= 10) {
                finalRomanString += romanSymbols[4];
                number %= 10;
            } else if(number >= 9) {
                finalRomanString += romanSymbols[3];
                number %= 9;
            } else if(number >= 5) {
                finalRomanString += romanSymbols[2];
                number %= 5;
            } else if(number >= 4) {
                finalRomanString += romanSymbols[1];
                number %= 4;
            } else if(number >= 1) {
                finalRomanString += romanSymbols[0];
                number %= 1;
            }
            
            
            
        }
        finalRomanString = finalRomanString.trim();
        return finalRomanString;
    }
    public static int convertToArabic(char[] romanNum) { // конвертит в араб на входе
        int result = 0;

        for (int i = 0; i < romanNum.length; i++) {
            if (i > 0 && convert(romanNum[i]) > convert(romanNum[i - 1])) {
                // Если текущая цифра больше предыдущей, то нужно вычесть значения предыдущей цифры
                result += convert(romanNum[i]) - 2 * convert(romanNum[i - 1]);
            } else {
                result += convert(romanNum[i]);
            }
        }

        return result;
    }

    public static int convert(char roman) {
        switch (roman) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            default:
                return 0;
        }
    }
}

class MyException extends Exception {
   public MyException(String message) {
      super(message);
   }
}