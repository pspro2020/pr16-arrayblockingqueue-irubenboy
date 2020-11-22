package classes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

public class CleanTray {

    private static final int CAPACITY = 10;
    private ArrayBlockingQueue<Plate> cleanPlates = new ArrayBlockingQueue<>(CAPACITY);
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    public void addCleanPlate(Plate p) throws InterruptedException {
        cleanPlates.put(p);
    }

    public Plate extractCleanPlate() throws InterruptedException {
        Plate p = cleanPlates.take(); // coge el primer plato limpio
        System.out.printf("Dyer extract a plate #%d from clean tray %s\n", p.getId(),
                LocalDateTime.now().format(formatter));
        return p;
    }
}
