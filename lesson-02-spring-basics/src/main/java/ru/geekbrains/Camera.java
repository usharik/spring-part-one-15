package ru.geekbrains;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Camera {

    private final CameraRoll cameraRoll;

    @Autowired
    public Camera(CameraRoll cameraRoll) {
        this.cameraRoll = cameraRoll;
    }

    void doPhotography() {
        cameraRoll.processing();
    }
}
