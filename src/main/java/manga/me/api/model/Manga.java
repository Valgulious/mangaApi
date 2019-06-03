package manga.me.api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Manga {

    @Id
    private String id;

    private String russianTitle;
    private String englishTitle;
    private String author;
    private String description;
    private Status status;
    private String imgFileName;

    public Manga() {
    }

    public Manga(String russianTitle, String englishTitle, String author, String description, Status status, String imgFileName) {
        this.russianTitle = russianTitle;
        this.englishTitle = englishTitle;
        this.author = author;
        this.description = description;
        this.status = status;
        this.imgFileName = imgFileName;
    }

    public String getRussianTitle() {
        return russianTitle;
    }

    public void setRussianTitle(String russianTitle) {
        this.russianTitle = russianTitle;
    }

    public String getEnglishTitle() {
        return englishTitle;
    }

    public void setEnglishTitle(String englishTitle) {
        this.englishTitle = englishTitle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getImgFileName() {
        return imgFileName;
    }

    public void setImgFileName(String imgFileName) {
        this.imgFileName = imgFileName;
    }

    @Override
    public String toString() {
        return "Manga{" +
                "id='" + id + '\'' +
                ", russianTitle='" + russianTitle + '\'' +
                ", englishTitle='" + englishTitle + '\'' +
                ", author='" + author + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", imgFileName='" + imgFileName + '\'' +
                '}';
    }
}
