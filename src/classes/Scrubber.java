package classes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Scrubber implements Runnable{

    private final Random time = new Random();
    private final DirtyTray dirtyTray;
    private final CleanTray cleanTray;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    public Scrubber(DirtyTray dirtyTray, CleanTray cleanTray){
        Objects.requireNonNull(dirtyTray);
        Objects.requireNonNull(cleanTray);
        this.dirtyTray = dirtyTray;
        this.cleanTray = cleanTray;
    }


    @Override
    public void run() {
        Plate p;


        while(!Thread.currentThread().isInterrupted()){
            try {
                p = dirtyTray.extractPlate();
                TimeUnit.SECONDS.sleep(time.nextInt(5)+1);
            } catch (InterruptedException e) {
                System.out.printf("Scrubber has been interrupted while trying to extrat a dirty plate %s\n",
                        LocalDateTime.now().format(formatter));
                return;
            }

            try {
                p.clean(cleanTray);
            } catch (InterruptedException e) {
                System.out.println("Scrubber has been interrupted while clean");
            }
            System.out.printf("Scrubber has adding a clean plate %s\n",
                    LocalDateTime.now().format(formatter));
        }

    }
}
