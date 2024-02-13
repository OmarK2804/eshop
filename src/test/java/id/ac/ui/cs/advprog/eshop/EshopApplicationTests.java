package id.ac.ui.cs.advprog.eshop;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SeleniumJupiter.class)
class EshopApplicationTests {

    @Test
    void contextLoads() {
        assertDoesNotThrow(() -> EshopApplication.main(new String[] {}));
    }

}
