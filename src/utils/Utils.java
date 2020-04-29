package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Utils
{

    public static String loadFileAsString(String path)
    {
        StringBuilder builder = new StringBuilder();
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            while((line = br.readLine()) != null) 
                builder.append((new StringBuilder(String.valueOf(line))).append("\n").toString());
            br.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        return builder.toString();
    }

    public static int parseInt(String number)
    {
        try
        {
            return Integer.parseInt(number);
        }
        catch(NumberFormatException e)
        {
            e.printStackTrace();
        }
        return 0;
    }

    public static double solveForY(double x)
    {
        double y = Math.log10(57 * x);
        return y;
    }

    public static double solveForX(double y)
    {
        double x = Math.pow(10, y) / 57;
        return x;
    }
}