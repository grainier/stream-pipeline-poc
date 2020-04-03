package net.grainier.streams.misc;

import net.grainier.streams.FPValue;

public class IntFPValue implements FPValue {

    private int i = 0;

    public Object call(Object args) {
        if (i < 10) {
            return i++;
        } else {
            return null;
        }
    }

}