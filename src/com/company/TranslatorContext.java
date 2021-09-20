package com.company;

public class TranslatorContext {
    private TranslatorStrategy strategy;

    public TranslatorContext(TranslatorStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(TranslatorStrategy strategy) {
        this.strategy = strategy;
    }

    public String numberTranslate(String textFrom){
        return strategy.doNumberTranslate(textFrom);
    }
    public String wordTranslate(String textFrom){
        return strategy.doWordTranslate(textFrom);
    }

}
