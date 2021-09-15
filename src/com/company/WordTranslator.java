package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class WordTranslator implements Translator {

    public WordTranslator(Languages language, String path) throws IOException {
        try(BufferedReader br = new BufferedReader(new FileReader(path + language.toString() + ".csv"))){
            String line;
            while ((line = br.readLine()) != null){
                String[] keyVal = line.split(", ");
                dictionary.put(keyVal[1], keyVal[0]);
            }
        }
    }

    public String[] rankSplit(String string){
        return string.split("( миллиард.*?\\s| миллион.*?\\s| тысяч.*?\\s)");
    }

    public Integer simpleTranslate(String textFrom) {
        int result = 0;
        String[] ranks = textFrom.split(" ");
        for (String rank:ranks)
            result += Integer.parseInt(dictionary.get(rank));

        return result;
    }

    @Override
    public String translate(String textFrom) {
        int result = 0;
        String[] ranks = rankSplit(textFrom);

        for (int i = 0; i < ranks.length; i++) {
            int index = ranks.length - i - 1;
            switch (index) {
                case 0 -> result += simpleTranslate(ranks[i]);
                case 1 -> result += simpleTranslate(ranks[i]) * 1000;
                case 2 -> result += simpleTranslate(ranks[i]) * 1000000;
                case 3 -> result += simpleTranslate(ranks[i]) * 1000000000;
            }
        }

        return Integer.toString(result);
    }

    @Override
    public HashMap<String, String> getDictionary() {
        return dictionary;
    }
}
