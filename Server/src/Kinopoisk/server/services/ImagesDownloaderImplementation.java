package Kinopoisk.server.services;

import Kinopoisk.api.services.ImagesDownloader;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.net.URL;
import java.util.UUID;

public class ImagesDownloaderImplementation extends HorseHessianServlet implements ImagesDownloader {
    @Override
    public byte[] loadImage(String URL) {
        try {
            BufferedImage img = ImageIO.read(new URL("https://getfile.dokpub.com/yandex/get/" + URL));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(img, "jpg", baos);
            byte[] bytes = baos.toByteArray();
            return bytes;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
