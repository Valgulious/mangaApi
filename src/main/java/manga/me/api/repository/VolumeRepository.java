package manga.me.api.repository;

import manga.me.api.model.Volume;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VolumeRepository extends MongoRepository<Volume, String> {

    public List<Volume> findByMangaLinkOrderByNumberAsc(String mangaLink);
    public List<Volume> findByMangaLinkOrderByNumberDesc(String mangaLink);
    public Volume findByMangaLinkAndNumber(String mangaLink, int number);

}
