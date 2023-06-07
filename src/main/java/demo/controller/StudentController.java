package demo.controller;

import demo.config.AmazonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class StudentController {
    private static final Logger LOG = Logger.getLogger(StudentController.class.getName());
    @Autowired
    private AmazonConfig amazonConfig;
    @GetMapping("/test")
    public String test() {
        LOG.log(Level.INFO, "Index API is calling");
        return "Hello" + amazonConfig.getHost();
    }

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/")
    public String helloWorld() {
        LOG.log(Level.INFO, "Index Time");
        String response = restTemplate.getForObject("http://localhost:8082", String.class);
        return "<h1>Hello from the " + response + "!</h1>";
    }

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
