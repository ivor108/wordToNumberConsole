package com.company;

import java.io.IOException;

public enum TranslatorStrategy {
    RU {
        RuNumberTranslator ruNumberTranslator;{
            try {
                ruNumberTranslator = new RuNumberTranslator(PATH);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        RuWordTranslator ruWordTranslator;{
            try {
                ruWordTranslator = new RuWordTranslator(PATH);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        String doNumberTranslate(String textFrom){
            return ruNumberTranslator.translate(textFrom);
        }

        @Override
        String doWordTranslate(String textFrom){
            return ruWordTranslator.translate(textFrom);
        }
    },
    EN {
        @Override
        String doNumberTranslate(String textFrom){
            return "enToNumber";
        }

        @Override
        String doWordTranslate(String textFrom){
            return "enToWord";
        }
    };

    abstract String doNumberTranslate(String textFrom);
    abstract String doWordTranslate(String textFrom);
    private static final String PATH = "D:\\dict\\";
}
