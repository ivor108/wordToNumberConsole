package com.company;

import java.io.IOException;
import java.util.Arrays;

public class Main {

    private static final String PATH = "C:\\Users\\pepega\\IdeaProjects\\wordToNumberConsole\\src\\";

    public static String[] rankSplit(String string){
        int targetLength;
        if (string.length()%3 == 0)
            targetLength = string.length();
        else
            targetLength = (string.length()/3 + 1)*3;

        String formatString = String.format("%0" + targetLength + "d", Long.parseLong(string));
        return formatString.split("(?<=\\G...)");
    }

    public static void main(String[] args) throws IOException {
        NumberTranslator numberTranslator = new NumberTranslator(Languages.RU, PATH);

        String textFrom = "12312312312";
        System.out.println(numberTranslator.translate(textFrom));
        System.out.println(Arrays.toString(rankSplit(textFrom)));

        //System.out.println(String.format("%03d", 93));

        //String test = "003";
        //System.out.println(test.substring(test.length() - 2));
    }
}
