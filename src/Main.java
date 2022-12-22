import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int positionLength, amountMnoj;
        ArrayList<HashSet<Integer>> list;
        HashSet<Integer> result;

        amountMnoj = getAmountMnoj(scanner);
        positionLength = getPositionLength(scanner);

        list = input(scanner, amountMnoj, positionLength);

        result = algoritmSet(amountMnoj, positionLength, list);

        output(result, scanner);

        scanner.close();
    }

    public static void output (HashSet<Integer> result, Scanner scanner){
        int mode;
        mode = getMode(scanner);
        if (mode == 1){
            writeResultToConsole(result);
        } else {
            PrintWriter printWriter = openFileForWriteLine(scanner);
            for (int element : result) {
                printWriter.printf("%d ", element);
            }
            printWriter.close();
        }
    }


    static PrintWriter openFileForWriteLine(Scanner scr) {

        boolean isIncorrect;
        String path;
        PrintWriter printWriter = null;

        do {
            isIncorrect = false;

            path = getPathToFile(scr);

            try {
                printWriter = new PrintWriter(path);
            } catch (Exception ex) {
                System.out.print("Ошибка: " + ex.getMessage());
                isIncorrect = true;
            }

            if (!isIncorrect) {
                System.out.println("Ошибка открытия файла! Повторите попытку.");
            }

        } while (!isIncorrect);

        System.out.println("Файл успешно открыт.");

        return printWriter;
    }

    public static void writeResultToConsole (HashSet<Integer> result){
        if (!result.isEmpty()) {
            for (int element : result) {
                System.out.print(element + " ");
            }
        } else{
            System.out.println("Элементы не были найдены.");
        }
    }


    public static int getMode (Scanner scanner){
        int mode;
        mode = 0;
        boolean isIncorrect;
        do{
            isIncorrect = false;
            System.out.print("Выберите режим работы:\n1. Консоль\n2.Файл\nВвод: ");
            try{
                mode = Integer.parseInt(scanner.nextLine());
            }catch (Exception ex){
                System.out.println("Введено некорректное значение.");
                isIncorrect = true;
            }

            if(!isIncorrect && mode != 1 && mode != 2){
                System.out.println("Выбран неправильный режим. Повторите ввод.");
            }

        } while (isIncorrect);

        return mode;
    }

    public static ArrayList<HashSet<Integer>> input (Scanner scanner, int amountMnoj, int positionLength) {
        int mode;
        mode = getMode(scanner);
        return generateListSet(amountMnoj, positionLength, mode, scanner);
    }

    public static int getPositionLength(Scanner scanner) {
        int positionLength = 0;
        boolean isIncorrect;

        do {
            isIncorrect = false;

            System.out.print("Введите размер множества: ");

            try {
                positionLength = Integer.parseInt(scanner.nextLine());
            } catch (Exception ex){
                System.out.println("Некорректное значение. Повторите ввод.");
                isIncorrect = true;
            }

            if (!isIncorrect && positionLength < 3){
                System.out.println("Размер множества не может быть меньше 3.");
                isIncorrect = true;
            }

        } while (isIncorrect);

        return positionLength;
    }

    public static int getAmountMnoj(Scanner scanner) {
        int amount;
        boolean isIncorrect;

        amount = 0;

        do {
            isIncorrect = false;
            System.out.print("Введите кол-во множеств: ");

            try{
                amount = Integer.parseInt(scanner.nextLine());
            } catch (Exception ex){
                System.out.println("Введено некорректное значение.");
                isIncorrect = true;
            }

            if (!isIncorrect && amount < 2){
                System.out.println("Кол-во множеств должно быть больше 2.");
                isIncorrect = true;
            }

        } while (isIncorrect);
        return amount;
    }

    public static HashSet<Integer> generateSet(int positionLength, Scanner scanner) {
        int i, element;
        boolean isIncorrect;
        element = 0;
        HashSet<Integer> mnIntegerHashSet = new HashSet<Integer>();
        for (i = 0; i < positionLength; i++) {
            do {
                isIncorrect = false;
                System.out.print("Введите элемент: ");
                try {
                    element = Integer.parseInt(scanner.nextLine());
                } catch (Exception ex){
                    System.out.println("Введено некорректное значение.");
                    isIncorrect = true;
                }
            } while (isIncorrect);

            mnIntegerHashSet.add(element);
        }

        return mnIntegerHashSet;
    }

    public static HashSet<Integer> generateSetFromFile(int positionLength, Scanner scannerForFile) {
        int i, element;
        boolean isIncorrect;
        element = 0;
        HashSet<Integer> mnIntegerHashSet = new HashSet<Integer>();

        for (i = 0; i < positionLength; i++) {

            if (scannerForFile.hasNextInt()) {
                try {
                    element = scannerForFile.nextInt();
                } catch (Exception ex) {
                    System.out.println("Ошибка: " + ex.getMessage() + ". Установлено по умолчанию: 0.");
                    element = 0;
                }

            } else {
                element = 0;
                scannerForFile.next();
            }

            mnIntegerHashSet.add(element);
        }

        return mnIntegerHashSet;
    }

    public static String getPathToFile(Scanner scanner) {
        boolean isIncorrect;
        String pathToFile;
        isIncorrect = false;
        pathToFile = "";

        do {

            if (isIncorrect) {
                System.out.println("Ошибка открытия файла! Повторите попытку.");
            }

            System.out.print("Введите путь к файлу: ");

            try {
                pathToFile = scanner.nextLine();
            } catch (Exception exception) {
                System.out.println("Ошибка : " + exception.getMessage());
                scanner.next();
                isIncorrect = true;
            }

        } while (isIncorrect);

        return pathToFile;
    }

    static Scanner openFileForRead(Scanner scr) {

        boolean isIncorrect;
        String path;
        File file;
        Scanner scannerForFile = null;

        do {

            isIncorrect = false;

            if (isIncorrect) {
                System.out.println("Ошибка открытия файла! Повторите попытку.");
            }

            path = getPathToFile(scr);

            try {
                file = new File(path);
                scannerForFile = new Scanner(file);
            } catch (FileNotFoundException ex) {
                System.out.println("Ошибка: " + ex.getMessage());
                isIncorrect = true;
            }

        } while (isIncorrect);

        System.out.println("Файл успешно открыт.");

        return scannerForFile;
    }

    public static ArrayList<HashSet<Integer>> generateListSet(int amountMnoj, int positionLength, int mode, Scanner scanner) {
        int i;

        ArrayList<HashSet<Integer>> list = new ArrayList<HashSet<Integer>>();

        if (mode == 1) {
            for (i = 0; i < amountMnoj; i++) {
                System.out.println("Заполнение множества: " + (i + 1));
                HashSet<Integer> integerHashSet = generateSet(positionLength, scanner);
                list.add(integerHashSet);
            }
        }
        else {
            Scanner scannerForfile;
            scannerForfile = openFileForRead(scanner);
            for (i = 0; i < amountMnoj; i++){
                System.out.println("Заполнение множества: " + (i + 1));
                HashSet<Integer> integerHashSet = generateSetFromFile(positionLength, scannerForfile);
                list.add(integerHashSet);
            }
        }

        System.out.println("Заполнение множества окончено.");
        return list;
    }

    public static boolean checkOnExistInSet(int itter, HashSet<Integer> set) {

        for (var element : set) {
            if (element == itter)
                return true;
        }

        return false;
    }

    public static HashSet<Integer> algoritmSet(int amountMnoj, int positionLength, ArrayList<HashSet<Integer>> list) {
        HashSet<Integer> result = new HashSet<Integer>();

        int i, j, counter, element;
        boolean isExist, isExistInFirstSet;
        isExist = false;
        isExistInFirstSet = false;
        for (i = 1; i < amountMnoj; i++) {

            Iterator<Integer> it = list.get(i).iterator();
            counter = 1;

            while (it.hasNext()) {

                if (counter % 3 == 0 && counter != 0) {

                    element = it.next();
                    isExistInFirstSet = checkOnExistInSet(element, list.get(0));
                    for (j = 1; j < amountMnoj; j++) {
                        if (j != i) {
                            isExist = checkOnExistInSet(element, list.get(j));

                        }
                    }
                    if (isExist && !isExistInFirstSet){
                        result.add(element);
                    }

                } else {
                    it.next();
                }

                counter++;
            }

        }


        return result;
    }

}

