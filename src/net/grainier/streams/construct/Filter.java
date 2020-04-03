package net.grainier.streams.construct;

import net.grainier.streams.BType;
import net.grainier.streams.FPValue;
import net.grainier.streams.Frame;

public class Filter extends StreamFunction {

    private FPValue filter;

    public Filter(FPValue filterFunc) {
        this.filter = filterFunc;
    }

    @Override
    public Frame next() {
        Frame f;
        do {
            f = getPreviousFunction().next();
        } while (f != null && !(boolean) filter.call(f.getData()));
        return f;
    }

    @Override
    public BType getStreamConstraintType() {
        return getPreviousFunction().getStreamConstraintType();
    }

}
