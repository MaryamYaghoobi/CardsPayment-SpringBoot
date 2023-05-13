package ir.co.isc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

@SpringBootApplication
@EnableScheduling
public class CardPaymentApplication {

    public static void main(String[] args) {
        SpringApplication.run(CardPaymentApplication.class, args);
    }

    @Scheduled(fixedRate = 10000)
    public void execute() {
        Runtime runtime = Runtime.getRuntime();
        long prevTotal = 0;
        long prevFree = runtime.freeMemory();
        long total = runtime.totalMemory();
        long free = runtime.freeMemory();
        if (total != prevTotal || free != prevFree) {
            System.out.println(String.format("#%s, Total:%s, Free: %s,Diff:%s",
                    LocalDateTime.now().toString(), total, free, prevFree - free));
            prevTotal = total;
            prevFree = free;
        }
    }
}
