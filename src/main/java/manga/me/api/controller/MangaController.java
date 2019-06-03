package manga.me.api.controller;


import manga.me.api.model.Manga;
import manga.me.api.model.Status;
import manga.me.api.service.MangaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/manga")
public class MangaController {

    @Autowired
    private MangaService mangaService;

    @RequestMapping("/create")
    public String create(@RequestParam String russianTitle, @RequestParam String englishTitle, @RequestParam String author,
                         @RequestParam String description, @RequestParam String status) {

        Status _status;
        if (status.equals("cameout")) _status = Status.CAMEOUT;
        else _status = Status.COMINGOUT;
        Manga manga = mangaService.create(russianTitle, englishTitle, author, description, _status);
        return manga.toString();

    }

    @RequestMapping("/getByAuthor")
    public List<Manga> getMangaByAuthor(@RequestParam String author) {
        return mangaService.getByAthor(author);
    }

    @RequestMapping("/getAll")
    public List<Manga> getAll() { return mangaService.getAll(); }

    @RequestMapping("/getByRussianTitle")
    public String getByRussianTitle(@RequestParam String russianTitle) {
        Manga manga = mangaService.getByRussianTitle(russianTitle);

        return manga.toString();
    }

    @RequestMapping("/update")
    public String update(@RequestParam String id, @RequestParam String russianTitle, @RequestParam String englishTitle,
                         @RequestParam String author, @RequestParam String description, @RequestParam String status) {

        Status _status;
        if (status.equals("cameout")) _status = Status.CAMEOUT;
        else _status = Status.COMINGOUT;
        Manga manga = mangaService.update(id, russianTitle, englishTitle, author, description, _status);
        return manga.toString();

    }

    @RequestMapping("/delete")
    public String delete(@RequestParam String id) {
        mangaService.delete(id);
        return "Deleted";
    }

    @RequestMapping("/deleteAll")
    public String deleteAll() {
        mangaService.deleteAll();
        return "Deleted all records";
    }

}
