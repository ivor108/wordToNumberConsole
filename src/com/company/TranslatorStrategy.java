package com.company;

import java.io.IOException;


public enum TranslatorStrategy {
    RU {
        @Override
        Translator getTranslator() throws IOException {
            return new RuTranslator();
        }
    },
    EN {
        @Override
        Translator getTranslator() throws IOException {
            return new RuTranslator();
        }
    };
    abstract Translator getTranslator() throws IOException;
}
