package net.grainier.streams.misc;

import net.grainier.streams.FPValue;

public class IsEvenFPValue implements FPValue {

    public Object call(Object arg) {
        int i = (int) arg;
        return (i % 2 == 0);
    }

}