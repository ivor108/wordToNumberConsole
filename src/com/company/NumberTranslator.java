package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class NumberTranslator implements Translator {

    public NumberTranslator(Languages language, String path) throws IOException {
        try(BufferedReader br = new BufferedReader(new FileReader(path + language.toString() + ".csv"))){
            String line;
            while ((line = br.readLine()) != null){
                String[] keyVal = line.split(", ");
                dictionary.put(keyVal[0], keyVal[1]);
            }
        }
    }

    public String[] rankSplit(String string){
        int targetLength = string.length();
        if (string.length()%3 != 0)
            targetLength = (string.length()/3 + 1)*3;

        String formatString = String.format("%0" + targetLength + "d", Long.parseLong(string));
        return formatString.split("(?<=\\G...)");
    }

    public String simpleTranslate(String textFrom) {
        StringBuilder result = new StringBuilder();
        String[] ranks = textFrom.split("");

        if(ranks.length == 3 && ranks[1].equals("1")){
            ranks[1] = ranks[1] + ranks[2];
            ranks[2] = "";
            int number = Integer.parseInt(ranks[0]) * 100;
            ranks[0] = Integer.toString(number);
        }
        else if(ranks.length == 2 && ranks[0].equals("1")){
            ranks[0] = ranks[0] + ranks[1];
            ranks[1] = "";
        }
        else{
            for (int i = 0; i < ranks.length; i++) {
                int rank = (int) Math.pow(10, i);
                int number = Integer.parseInt(ranks[ranks.length - i - 1]) * rank;
                ranks[ranks.length - i - 1] = Integer.toString(number);
            }
        }

        for (int i = 0; i < ranks.length; i++) {
            if((i != ranks.length - 1 && ranks[i].equals("0")) || ranks[i].equals(""))
                continue;

            result.append(dictionary.get(ranks[i])).append(" ");
        }

        return result.deleteCharAt(result.length() - 1).toString();
    }

    @Override
    public String translate(String textFrom) {
        StringBuilder result = new StringBuilder();
        String[] ranks = rankSplit(textFrom);

        if(ranks[ranks.length - 1].length() != 3)
            ranks[ranks.length - 1] = String.format("%03d", Integer.parseInt(ranks[ranks.length - 1]));


        for (int i = 0; i < ranks.length; i++) {
            int index = ranks.length - i - 1;
            int endNumber;
            switch (index) {
                case 0:
                    result.append(simpleTranslate(ranks[i]));
                    break;
                case 1:
                    endNumber = Integer.parseInt(ranks[i].substring(ranks[i].length() - 2));
                    if(endNumber == 1)
                        result.append("одна тысяча ");
                    else if(endNumber%10 > 1 && endNumber%10 < 5 && endNumber/10 != 1)
                        result.append(simpleTranslate(ranks[i])).append(" тысячи ");
                    else
                        result.append(simpleTranslate(ranks[i])).append(" тысяч ");
                    break;
                case 2:
                    endNumber = Integer.parseInt(ranks[i].substring(ranks[i].length() - 2));
                    if(endNumber == 1)
                        result.append("один миллион ");
                    else if(endNumber%10 > 1 && endNumber%10 < 5 && endNumber/10 != 1)
                        result.append(simpleTranslate(ranks[i])).append(" миллиона ");
                    else
                        result.append(simpleTranslate(ranks[i])).append(" миллионов ");
                    break;
                case 3:
                    endNumber = Integer.parseInt(ranks[i].substring(ranks[i].length() - 2));
                    if(endNumber == 1)
                        result.append("один миллиард ");
                    else if(endNumber%10 > 1 && endNumber%10 < 5 && endNumber/10 != 1)
                        result.append(simpleTranslate(ranks[i])).append(" миллиарда ");
                    else
                        result.append(simpleTranslate(ranks[i])).append(" миллиардов ");
                    break;

            }
        }

        return result.toString();
    }

    @Override
    public HashMap<String, String> getDictionary() {
        return dictionary;
    }
}
