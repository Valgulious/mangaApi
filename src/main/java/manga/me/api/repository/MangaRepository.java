package manga.me.api.repository;

import manga.me.api.model.Manga;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MangaRepository extends MongoRepository<Manga, String> {

    public List<Manga> findByAuthor (String author);
    public Manga findByRussianTitle (String russianTitle);
}
