package manga.me.api.controller;


import manga.me.api.model.Manga;
import manga.me.api.model.Status;
import manga.me.api.service.MangaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/manga")
public class MangaController {

    @Autowired
    private MangaService mangaService;

    @Value("${upload.path}")
    private String uploadPath;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@RequestParam String russianTitle,
                         @RequestParam String englishTitle,
                         @RequestParam String author,
                         @RequestParam String description,
                         @RequestParam String status,
                         @RequestParam("file") MultipartFile file
    ) throws IOException {

        Status _status;
        String resultFileName = "test.jpg";

        if (status.equals("cameout")) _status = Status.CAMEOUT;
        else _status = Status.COMINGOUT;

        if (file.getOriginalFilename().length() != 0) {

            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            resultFileName = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "covers/" + resultFileName));
        }

        Manga manga = mangaService.create(russianTitle, englishTitle, author, description, _status, resultFileName);
        return manga.toString();

    }

    @RequestMapping("/getByAuthor")
    public List<Manga> getMangaByAuthor(@RequestParam String author) {
        return mangaService.getByAthor(author);
    }

    @RequestMapping("/getAll")
    public List<Manga> getAll() { return mangaService.getAll(); }

    @RequestMapping("/standByRussianTitle")
    public boolean getByRussianTitle(@RequestParam String russianTitle) {
        Manga manga = mangaService.getByRussianTitle(russianTitle);

        return manga != null;
    }

    @RequestMapping("/standByEnglishTitle")
    public boolean getByEnglishTitle(@RequestParam String englishTitle) {
        Manga manga = mangaService.getByEnglishTitle(englishTitle);

        return manga != null;
    }

    @RequestMapping("/{link}")
    public Manga getByLink(@PathVariable String link) {return mangaService.getByLink(link);}

//    @RequestMapping("/update")
//    public String update(@RequestParam String id, @RequestParam String russianTitle,
//                         @RequestParam String englishTitle,
//                         @RequestParam String author,
//                         @RequestParam String description,
//                         @RequestParam String status) {
//
//        Status _status;
//        if (status.equals("cameout")) _status = Status.CAMEOUT;
//        else _status = Status.COMINGOUT;
//        Manga manga = mangaService.update(id, russianTitle, englishTitle, author, description, _status);
//        return manga.toString();
//
//    }

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
