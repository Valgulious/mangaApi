package manga.me.api.service;

import com.mongodb.MongoClient;
import manga.me.api.model.Manga;
import manga.me.api.model.Status;
import manga.me.api.repository.MangaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MangaService {

    @Autowired
    private MangaRepository mangaRepository;

    private MongoOperations mongoOps = new MongoTemplate(new SimpleMongoDbFactory(new MongoClient(), "test"));

    private String engToLink (String _eng) {

        String link = _eng.replaceAll(" ", "_").toLowerCase();

        return link;
    }

    //Create
    public Manga create(String russianTitle, String englishTitle, String author, String description, Status status,
                        String imgFileName) {



        return mangaRepository.save(new Manga(russianTitle, englishTitle, author, description, status, imgFileName,
                engToLink(englishTitle)));
    }

    //Retrieve
    public List<Manga> getAll() { return mangaRepository.findAll(); }
    public List<Manga> getByAthor(String author) { return mangaRepository.findByAuthor(author); }
    public Manga getByRussianTitle(String russianTitle) { return mangaRepository.findByRussianTitle(russianTitle); }
    public Manga getByLink(String link) {return mangaRepository.findByLink(link);}

    //Update
    public Manga update(String id, String russianTitle, String englishTitle, String author, String description, Status status,
                        String imgFileName) {
        Manga manga = mongoOps.findById(id, Manga.class);

        manga.setRussianTitle(russianTitle);
        manga.setEnglishTitle(englishTitle);
        manga.setAuthor(author);
        manga.setDescription(description);
        manga.setStatus(status);
        manga.setImgFileName(imgFileName);
        manga.setLink(engToLink(englishTitle));

        return mangaRepository.save(manga);
    }

    //Delete
    public void deleteAll() { mangaRepository.deleteAll(); }
    public void delete(String id) {
        Manga manga = mongoOps.findById(id, Manga.class);
        mangaRepository.delete(manga);
    }
}
