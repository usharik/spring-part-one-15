package ru.geekbrains;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BlockOfCameras {

    @Autowired
    private Camera camera1;

    @Autowired
    private Camera camera2;

    @Autowired
    private Camera camera3;

    public void doPhotos() {
        camera1.doPhotography();
        camera2.doPhotography();
        camera3.doPhotography();
    }

}
