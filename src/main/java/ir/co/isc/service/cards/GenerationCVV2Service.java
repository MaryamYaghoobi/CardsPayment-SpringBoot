package ir.co.isc.service.cards;


import org.apache.logging.log4j.core.Logger;
import org.springframework.stereotype.Service;


import java.util.Random;
@Service
public class GenerationCVV2Service {
    Logger logger = Logger.getRootLogger();

    public int generateCVV2() {
        int cvv2 = 000;
        Random rand = new Random();
        for (int i = 1; i <= 100; i++) {
            int randomNum = rand.nextInt((999 - 100) + 1) + 100;

            logger.info("has been created : " + randomNum);

        }
        return cvv2;
    }
}
