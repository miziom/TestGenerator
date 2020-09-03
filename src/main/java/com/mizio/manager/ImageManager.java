package com.mizio.manager;

import lombok.SneakyThrows;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;

public class ImageManager {

    private static String getExtension(String fileName) {
        String[] fileNameSplit = fileName.split("\\.");
        return fileNameSplit[fileNameSplit.length - 1];
    }

    @SneakyThrows
    public static byte[] getByteFromFile(File file) {
        BufferedImage bufferedImage = ImageIO.read(file);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, getExtension(file.getName()), out);
        return out.toByteArray();
    }
}