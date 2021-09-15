package com.company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {

    private static final String PATH = "C:\\Users\\pepega\\IdeaProjects\\wordToNumberConsole\\src\\";

    public static String[] rankSplit(String string){
        int targetLength = string.length();
        if (string.length()%3 != 0)
            targetLength = (string.length()/3 + 1)*3;

        String formatString = String.format("%0" + targetLength + "d", Long.parseLong(string));
        return formatString.split("(?<=\\G...)");
    }

    public static String[] rankSplit2(String string){
        String[] ranks = new String[4];
        Arrays.fill(ranks, "");

        if(string.matches(".*миллиард.*")){
            String[] tmp = string.split(" миллиард.*?\\s");
            ranks[0] = tmp[0];
            if(tmp.length > 1)
                string = string.split(" миллиард.*?\\s")[1];
            else
                string = "";
        }
        if(string.matches(".*миллион.*")){
            String[] tmp = string.split(" миллион.*?\\s");
            ranks[1] = string.split(" миллион.*?\\s")[0];
            if(tmp.length > 1)
                string = string.split(" миллион.*?\\s")[1];
            else
                string = "";
        }
        if(string.matches(".*тысяч.*")){
            String[] tmp = string.split(" тысяч.*?\\s");
            ranks[2] = string.split(" тысяч.*?\\s")[0];
            if(tmp.length > 1)
                string = string.split(" тысяч.*?\\s")[1];
            else
                string = "";
        }

        ranks[3] = string;
        return ranks;
        //return string.split("( миллиард.*?\\s| миллион.*?\\s| тысяч.*?\\s| единиц.*?\\s)");
    }

    public static void main(String[] args) throws IOException {
        NumberTranslator numberTranslator = new NumberTranslator(Languages.RU, PATH);
        WordTranslator wordTranslator = new WordTranslator(Languages.RU, PATH);

        String textFrom = "1000";
        System.out.println(numberTranslator.translate(textFrom));
        System.out.println(Arrays.toString(rankSplit(textFrom)));

        String textFrom2 = numberTranslator.translate(textFrom);
        System.out.println(Arrays.toString(rankSplit2(textFrom2)));
        //System.out.println(textFrom2.matches(".*тысяч.*"));
        //System.out.println(Arrays.toString(textFrom2.split("( миллиард.*?\\s| миллион.*?\\s| тысяч.*?\\s)")));
        System.out.println(wordTranslator.translate(textFrom2));
    }
}
