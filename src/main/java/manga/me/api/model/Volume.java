package manga.me.api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class Volume {

    @Id
    private String id;

    private String mangaLink;
    private int number;
    private List<String> images;

    public Volume(String mangaLink, int number, List<String> images) {
        this.mangaLink = mangaLink;
        this.number = number;
        this.images = images;
    }

    public String getMangaLink() {
        return mangaLink;
    }

    public void setMangaLink(String mangaLink) {
        this.mangaLink = mangaLink;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
