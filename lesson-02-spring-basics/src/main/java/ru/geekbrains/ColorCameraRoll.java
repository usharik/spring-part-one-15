package ru.geekbrains;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ColorCameraRoll implements CameraRoll {

    @Value("${role.frameCount}")
    private int frameCount;

    @Override
    public void processing() {
        System.out.println("-1 цветной кадр. Всего кадров " + --frameCount);
    }
}
