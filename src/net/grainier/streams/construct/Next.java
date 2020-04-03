package net.grainier.streams.construct;

import net.grainier.streams.BType;
import net.grainier.streams.FPValue;
import net.grainier.streams.Frame;

public class Next extends StreamFunction {

    private FPValue next;
    private BType type;

    public Next(FPValue next, BType type) {
        this.next = next;
        this.type = type;
    }

    @Override
    public Frame next() {
        Object data = this.next.call(null);
        if (data != null) {
            return new Frame(data);
        }
        return null;
    }

    @Override
    public BType getStreamConstraintType() {
        return this.type;
    }

}
