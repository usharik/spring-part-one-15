package ru.geekbrains;

import org.springframework.context.annotation.*;

@Configuration
@ComponentScan("ru.geekbrains")
@PropertySource("classpath:application.properties")
public class AppConfig {

    @Bean
    @Scope("prototype")
    public Camera camera(CameraRoll cameraRoll) {
        return new Camera(cameraRoll);
    }
}
