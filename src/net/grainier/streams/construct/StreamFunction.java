package net.grainier.streams.construct;

import net.grainier.streams.BType;
import net.grainier.streams.Frame;

public abstract class StreamFunction {

    /**
     * Pointer to the previous stream function.
     */
    private StreamFunction previousFunction;

    /**
     * Get the next data frame.
     *
     * @return resulting data {@link Frame}.
     */
    public abstract Frame next();

    /**
     * Get return type of resulting resulting frame data.
     *
     * @return resulting frame data {@link BType}.
     */
    public abstract BType getStreamConstraintType();

    /**
     * Get pointer to the previous stream function.
     *
     * @return pointer to the previous {@link StreamFunction}.
     */
    public StreamFunction getPreviousFunction() {
        return previousFunction;
    }

    /**
     * Set pointer to the previous stream function.
     *
     * @param previousFunction pointer to the previous {@link StreamFunction}.
     */
    public void setPreviousFunction(StreamFunction previousFunction) {
        this.previousFunction = previousFunction;
    }

}
