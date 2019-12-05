import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration
@SpringBootApplication(scanBasePackages = {"com.app"})
public class RestaurantApp {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(RestaurantApp.class,args);
    }

}
