package by.nikita.externalAPI.exApiInit;

import by.nikita.externalAPI.exApiInit.model.CatImage;
import by.nikita.externalAPI.exApiInit.service.CatImageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ExApiInitApplication {
    public static void main(String[] args) throws IOException {

        CatImageService catImageService = new CatImageService();
        int num = scanNum();
        ObjectMapper mapper = new ObjectMapper();
        RestTemplate restTemplate = new RestTemplate();
        List<CatImage> images = new ArrayList<>();
        List response = restTemplate.getForObject("https://api.thecatapi.com/v1/images/search?limit=" + num, List.class);
        if (response != null && !response.isEmpty()) {
            response.forEach(o -> images.add(mapper.convertValue(o, CatImage.class)));
            for (CatImage image : images) {
                catImageService.downloadImage(image);
                System.out.println(catImageService.localAddressOfImage(image));
            }
        }
    }

    private static int scanNum() {
        Scanner scanner = new Scanner(System.in);
        int num;
        System.out.println("Input number of images you want to load:");
        while (true) {
            if (scanner.hasNextInt()) {
                num = scanner.nextInt();
                if (num > 0)
                    return num;
                else continue;
            } else continue;
        }
    }
}