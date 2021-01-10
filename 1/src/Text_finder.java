import javafx.util.Pair;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Text_finder {

    private static final String parseInfo  = "#ParseInfo:";
    private static final String datePattern  = "dd.MM.yyyy HH:mm:ss";

    private static Pair<String, String> fileEnter(String n) throws IOException
    {
        File file = new File(n);
        return new Pair<>(file.getName(), fileEnter(file));
    }

    private static String fileEnter(File file) throws IOException
    {
        StringBuilder text = new StringBuilder();
        try (Scanner reader = new Scanner(file)){
            while (reader.hasNextLine()) {
                text.append(reader.nextLine()).append(System.lineSeparator());
            }
            return text.toString();

        }  catch (IOException e) {
            System.out.println("\n" + file.getName() + " Произошла ошибка считывания фаила!");
            throw e;
        }
    }

    private static String inputContainer () throws IOException
    {
        File file = new File("container.txt");
        if (file.exists())
        {
            return fileEnter(file);
        }
        return "";
    }

    private static String inputFormatter(String nowText, String name)
    {
        Pattern pattern = Pattern.compile("\\d{3,4}\\b");
        Matcher matcher = pattern.matcher(nowText);
        HashSet<String> years = new HashSet<>();
        SimpleDateFormat formatter = new SimpleDateFormat(datePattern);
        String formattedDate = formatter.format(new Date());
        String fileInfo = String.format("%s %s : %s", parseInfo, name, formattedDate);

        StringBuilder output = new StringBuilder(fileInfo);
        while (matcher.find()) {
            for (int i = 0; i <= matcher.groupCount(); i++)
                years.add(matcher.group(i));
        }
        for(String o : years) {
            output.append(System.lineSeparator()).append(o);
        }
        return output.toString();
    }

    private static void writer(String newText, String container)
    {
        String output = newText + System.lineSeparator() + container;
        try ( FileWriter writer = new FileWriter("container.txt"))
        {
            writer.write(output);
            writer.write(System.lineSeparator());
            writer.flush();
        } catch (IOException e) {
            System.out.println("\nЗапись не удалась!!!");
        }
    }

    private static Date minutesChanger (Date currentDate, int x)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);
        cal.add(Calendar.MINUTE, x);
        currentDate = cal.getTime();
        return currentDate;
    }

    private static Pair <String, Boolean> textContainer (String keyToFind, String text, Date currentDate) throws ParseException
    {
        String name = (" " + keyToFind);
        int x = text.indexOf(name);
        if (x > 0)
        {
            int parseInfoStart = x - parseInfo.length();
            x = x + name.length() + 3;
            String oldDate = text.substring(x, text.indexOf(System.lineSeparator(), x));
            SimpleDateFormat parser = new SimpleDateFormat(datePattern);
            Date date = parser.parse(oldDate);
            if (date.before(minutesChanger(currentDate, -10)))
            {
                String newContainer = text.substring(0, parseInfoStart);
                String addContainer = "";
                int poison = text.indexOf(parseInfo, x);
                if (poison > 0) addContainer = text.substring(poison);
                return new Pair<>(newContainer+addContainer, true);
            }
            return new Pair<>(null, false);
        }
        return new Pair<>(text, true);
    }



    public static void main(String[] args) throws IOException, ParseException {

        System.out.print("Введите название фаила: ");
        Scanner in = new Scanner(System.in);
        String filePath = in.nextLine();
        in.close();
        Pair<String, String> nowText = fileEnter(filePath);
        Pair<String, Boolean> newOutput = textContainer(nowText.getKey(), inputContainer(), new Date());
        if (newOutput.getValue())
        {
            writer(inputFormatter(nowText.getValue(), nowText.getKey()), newOutput.getKey());
        }
    }
}
