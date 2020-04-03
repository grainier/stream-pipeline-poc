package net.grainier.streams;

public class Frame {
    private Object data;

    public Frame(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void updateData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return String.format("{ value: %s }", this.data);
    }
}
