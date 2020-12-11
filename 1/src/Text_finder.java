import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.*;

public class Text_finder {

    private static String nowDate()
    {
        Date date = new Date();
        return date.toString();
    }

    private static String fileEnter(String n) {
        StringBuilder text = new StringBuilder();
        try(FileReader reader = new FileReader(n)) {
            Scanner scan = new Scanner(reader);
            while (scan.hasNextLine())
                text.append(scan.nextLine());
            reader.close();
            scan.close();
            return text.toString();

        }  catch (IOException e) {
            System.out.println("\n" + n + " Произошла ошибка считывания!");
            return "Произошла ошибка считывания!";
        }
    }


    public static void main(String[] args){

        System.out.print("Введите название фаила: ");
        Scanner in = new Scanner(System.in);
        String file_name = in.nextLine();
        in.close();

        Pattern pattern = Pattern.compile("[0-9]{3,4}");
        Matcher matcher = pattern.matcher(fileEnter(file_name));
        String output = nowDate() + "\n";
        while (matcher.find()) {
            for (int i = 0; i <= matcher.groupCount(); i++)
                output += "\n"  + matcher.group(i);
        }

        System.out.print(output);
    }
}