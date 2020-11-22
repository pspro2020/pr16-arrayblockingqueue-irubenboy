package classes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ArrayBlockingQueue;

public class DryTray {
    private static final int CAPACITY = 10;
    private final ArrayBlockingQueue<Plate> dryPlates = new ArrayBlockingQueue<>(CAPACITY);
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    public void addDryPlate(Plate p) throws InterruptedException {
        dryPlates.put(p);
    }

    public Plate extractDryPlate() throws InterruptedException {
        Plate p = dryPlates.take(); // Coge el primer plato
        System.out.printf("Organizer extract a plate #%d from dry plate %s\n", p.getId(),
                    LocalDateTime.now().format(formatter));
        return p;
    }
}
