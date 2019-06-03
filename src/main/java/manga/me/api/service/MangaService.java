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

    //Create
    public Manga create(String russianTitle, String englishTitle, String author, String description, Status status) {
        return mangaRepository.save(new Manga(russianTitle, englishTitle, author, description, status));
    }

    //Retrieve
    public List<Manga> getAll() { return mangaRepository.findAll(); }
    public List<Manga> getByAthor(String author) { return mangaRepository.findByAuthor(author); }
    public Manga getByRussianTitle(String russianTitle) { return mangaRepository.findByRussianTitle(russianTitle); }

    //Update
    public Manga update(String id, String russianTitle, String englishTitle, String author, String description, Status status) {
        Manga manga = mongoOps.findById(id, Manga.class);

        manga.setRussianTitle(russianTitle);
        manga.setEnglishTitle(englishTitle);
        manga.setAuthor(author);
        manga.setDescription(description);
        manga.setStatus(status);

        return mangaRepository.save(manga);
    }

    //Delete
    public void deleteAll() { mangaRepository.deleteAll(); }
    public void delete(String id) {
        Manga manga = mongoOps.findById(id, Manga.class);
        mangaRepository.delete(manga);
    }
}
