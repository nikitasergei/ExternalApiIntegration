package by.nikita.externalAPI.exApiInit.service;

import by.nikita.externalAPI.exApiInit.model.CatImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;

@Service
public class CatImageService {

    @Autowired
    private CatImage catImage;

    public void downloadImage(CatImage catImage) {
        URL url = null;
        try {
            url = new URL(catImage.getUrl());
            InputStream in = new BufferedInputStream(url.openStream());
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int n = 0;
            while (-1 != (n = in.read(buf))) {
                out.write(buf, 0, n);
            }
            out.close();
            in.close();
            byte[] picResponse = out.toByteArray();

            FileOutputStream fos = new FileOutputStream(CatImage.getLocalAddress() + catImage.hashCode() + ".jpg ");
            fos.write(picResponse);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String localAddressOfImage(CatImage catImage) {
        return CatImage.getLocalAddress() + catImage.hashCode() + ".jpg";
    }
}
