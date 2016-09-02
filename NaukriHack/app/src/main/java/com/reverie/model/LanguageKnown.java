package com.reverie.model;

/**
 * Created by NayanJyoti on 02-09-2016.
 */
public class LanguageKnown {
    public String langName;
    public boolean read;
    public boolean write;
    public boolean speak;
    public LanguageKnown(String name, boolean read, boolean write,boolean speak){
        this.langName = name;
        this.read = read;
        this.write = write;
        this.speak = speak;
    }
}
