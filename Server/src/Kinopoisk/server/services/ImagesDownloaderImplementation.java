package Kinopoisk.server.services;

import Kinopoisk.api.services.ImagesDownloader;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.UUID;

public class ImagesDownloaderImplementation extends HorseHessianServlet implements ImagesDownloader {
    @Override
    public Image loadImage(String URL) {
        try {
            String fileName = UUID.randomUUID ().toString () + ".png";
            BufferedImage img = ImageIO.read(new URL(URL));
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            ImageIO.write(img, "png", file);
            return img;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
