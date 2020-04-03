package net.grainier.streams;

import java.util.UUID;

import net.grainier.streams.construct.Next;
import net.grainier.streams.construct.StreamFunction;

public class StreamValue {

    private BType type;
    private String streamId;
    private StreamFunction streamFunction;

    public StreamValue(BType type, FPValue next) {
        this.streamFunction = new Next(next, type);
        this.type = type;
        this.streamId = UUID.randomUUID().toString();
    }

    public void addStreamFunction(StreamFunction func) {
        StreamFunction existingFunc = this.streamFunction;
        func.setPreviousFunction(existingFunc);
        this.streamFunction = func;
        this.type = func.getStreamConstraintType();
    }

    public Frame next() {
        return this.streamFunction.next();
    }

    public Object getIterator() {
        return null;
    }

    public String getStreamId() {
        return streamId;
    }
}
