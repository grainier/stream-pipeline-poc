package net.grainier;

import net.grainier.streams.BType;
import net.grainier.streams.FPValue;
import net.grainier.streams.Frame;
import net.grainier.streams.StreamValue;
import net.grainier.streams.construct.Filter;
import net.grainier.streams.construct.Join;
import net.grainier.streams.misc.IsEvenFPValue;
import net.grainier.streams.misc.IntFPValue;

public class Main {

    public static void main(String[] args) {
        //    stream<int> evenStream = stream from int x in intStream
        //    where x % 2 == 0
        //    select {
        //          value: x
        //    }
        //    evenStream.next();

        FPValue intNextA = new IntFPValue();
        BType IntType = new BType();

        // stream from int x in intStream
        StreamValue evenStream = new StreamValue(IntType, intNextA);

        // where x % 2 == 0
        Filter intFilter = new Filter(new IsEvenFPValue());
        evenStream.addStreamFunction(intFilter);


        //    stream<int> oddStream = stream from int x in intStream
        //    where x % 2 == 1
        //    select {
        //          value: x
        //    }
        //    oddStream.next();

        FPValue intNextB = new IntFPValue();

        // stream from int x in intStream
        StreamValue oddStream = new StreamValue(IntType, intNextB);

        // where x % 2 == 1
        Filter oddFilter = new Filter(new IsEvenFPValue());
        oddStream.addStreamFunction(oddFilter);


        //    stream<int> mergedStream = stream from int x in evenStream
        //    from int y in oddStream
        //    select {
        //          value: { "StreamA": x, "StreamB": y}
        //    }
        //    mergedStream.next();

        // stream from int x in evenStream
        StreamValue mergedStream = evenStream; // TODO, same ref issue???

        //    from int y in oddStream
        Join join = new Join(oddStream);
        mergedStream.addStreamFunction(join);

        Frame f = mergedStream.next();
        while (f != null) {
            System.out.println(f);
            f = mergedStream.next();
        }
    }
}
