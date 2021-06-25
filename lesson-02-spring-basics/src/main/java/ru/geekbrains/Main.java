package ru.geekbrains;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {
//        CameraRoll roll1 = new ColorCameraRoll();
//        CameraRoll roll2 = new BlackAndWhiteCameraRoll();
//        Camera camera = new Camera(roll2);
//        camera.doPhotography();

        //ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        Camera camera1 = context.getBean("camera", Camera.class);
        Camera camera2 = context.getBean("camera", Camera.class);
        Camera camera3 = context.getBean("camera", Camera.class);

        camera1.doPhotography();

        BlockOfCameras blockOfCameras = context.getBean("blockOfCameras", BlockOfCameras.class);
        blockOfCameras.doPhotos();
    }
}
