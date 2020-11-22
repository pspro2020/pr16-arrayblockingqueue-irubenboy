package classes;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

public class Cupboard {
    private static final int CAPACITY = 10;
    private ArrayBlockingQueue<Plate> finalPlates = new ArrayBlockingQueue<>(CAPACITY);

    public void addToCupboard(Plate p) throws InterruptedException {
        finalPlates.put(p);
    }
}
