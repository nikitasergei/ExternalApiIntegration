package by.nikita.externalAPI.exApiInit.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CatImage {

    private static String localAddress = "C://Users/User/Desktop/exApiInit/forLoadImg/image";


    private String id;
    private String url;

    public static String getLocalAddress() {
        return localAddress;
    }

    @Override
    public String toString() {
        return "CatImage{" +
                "id='" + id + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
