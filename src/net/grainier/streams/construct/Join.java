package net.grainier.streams.construct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.grainier.streams.BType;
import net.grainier.streams.Frame;
import net.grainier.streams.StreamValue;

public class Join extends StreamFunction {

    private StreamValue streamToJoin;
    private Frame currentFrame = null;
    private Frame[] candidateFrames;
    private boolean populated;
    private int consumedIndex = -1;

    public Join(StreamValue stream) {
        this.streamToJoin = stream;
    }

    @Override
    public Frame next() {

        if (!populated) {
            populateCandidateFrames();
        }

        if (consumedIndex == candidateFrames.length - 1) {
            consumedIndex = -1;
            currentFrame = null;
        }

        int index = ++consumedIndex;
        if (index == 0 && currentFrame == null) {
            currentFrame = getPreviousFunction().next();
        }

        if (currentFrame != null) {
            Map<String, Object> data = new HashMap<>(2);
            data.put("streamA", currentFrame.getData());
            data.put("streamB", candidateFrames[index].getData());
            return new Frame(data);
        }

        return null;
    }

    @Override
    public BType getStreamConstraintType() {
        // TODO: What's the return type here?
        return null;
    }

    private void populateCandidateFrames() {
        List<Frame> frames = new ArrayList<>();
        Frame f = streamToJoin.next();
        while (f != null) {
            frames.add(f);
            f = streamToJoin.next();
        }
        candidateFrames = frames.toArray(new Frame[0]);
        populated = true;
    }

}
