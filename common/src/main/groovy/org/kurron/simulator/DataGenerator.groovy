package org.kurron.simulator

import java.util.concurrent.ThreadLocalRandom

trait DataGenerator {

    String randomString() {
        Integer.toHexString( ThreadLocalRandom.current().nextInt( Integer.MAX_VALUE ) ).toUpperCase( Locale.US )
    }
}