package classes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ArrayBlockingQueue;

public class DirtyTray {
    private static final int CAPACITY = 10;
    private final ArrayBlockingQueue<Plate> dirtyPlates = new ArrayBlockingQueue<>(CAPACITY);
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    public void addPlate(Plate p) throws InterruptedException {
        dirtyPlates.put(p);
    }

    public Plate extractPlate() throws InterruptedException {
        Plate p = dirtyPlates.take(); // Coge el primer plato y lo elimina de la bandeja
        System.out.printf("Scrubber extract the plate #%d from dirty tray %s\n", p.getId(),
                    LocalDateTime.now().format(formatter));
        return p;

    }
}
