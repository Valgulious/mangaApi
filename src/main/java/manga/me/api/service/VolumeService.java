package manga.me.api.service;

import manga.me.api.model.Volume;
import manga.me.api.repository.VolumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VolumeService {

    @Autowired
    VolumeRepository volumeRepository;

    public Volume create (String mangaLink, int number, List<String> images) {

        return volumeRepository.save(new Volume(mangaLink, number, images));

    }

    public List<Volume> getByMangaLinkOrderByNumberAsc (String mangaLink) {
        return volumeRepository.findByMangaLinkOrderByNumberAsc(mangaLink);
    }
    public List<Volume> getByMangaLinkOrderByNumberDesc (String mangaLink) {
        return volumeRepository.findByMangaLinkOrderByNumberDesc(mangaLink);
    }

    public Volume getByMangaLinkAndNumber (String mangaLink, int number) {
        return volumeRepository.findByMangaLinkAndNumber(mangaLink, number);
    }

}
