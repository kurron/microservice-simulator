package org.kurron.simulator

import java.util.concurrent.ThreadLocalRandom

trait DataGenerator {

    String randomString() {
        Integer.toHexString( ThreadLocalRandom.current().nextInt( Integer.MAX_VALUE ) ).toUpperCase( Locale.US )
    }

    boolean randomFlag() {
        ThreadLocalRandom.current().nextBoolean()
    }

    Tuple2<Integer,Integer> randomLatency( int floor = 100, int ceiling = 250 ) {
        int max = ThreadLocalRandom.current().nextInt( floor, ceiling ) + 1
        new Tuple2<Integer,Integer>( floor, max )
    }
}