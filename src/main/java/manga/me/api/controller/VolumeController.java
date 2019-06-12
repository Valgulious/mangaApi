package manga.me.api.controller;

import manga.me.api.model.Volume;
import manga.me.api.service.VolumeService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import net.lingala.zip4j.core.ZipFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/volumes")
public class VolumeController {

    @Autowired
    VolumeService volumeService;

    @Value("${upload.path}")
    private String uploadPath;

    @RequestMapping("/create")
    public void create (@RequestParam String mangaLink,
                        @RequestParam String number,
                        @RequestParam MultipartFile file) throws IOException {

        File zip = File.createTempFile(UUID.randomUUID().toString(), "temp");
        FileOutputStream o = new FileOutputStream(zip);
        IOUtils.copy(file.getInputStream(), o);
        o.close();

        /**
         * unizp file from temp by zip4j
         */
        String destination = uploadPath + mangaLink + "/" + number;
        try {
            ZipFile zipFile = new ZipFile(zip);
            zipFile.extractAll(destination);
        }catch (net.lingala.zip4j.exception.ZipException e) {
            e.printStackTrace();
        } finally {
            /**
             * delete temp file
             */
            zip.delete();
        }

        List<String> images = new ArrayList<String>();

        File folder = new File(destination);
        File[] files = folder.listFiles();

        for (File f: files) {
            images.add(f.getName());
        }

        Collections.sort(images);

        Volume volume = volumeService.create(mangaLink, Integer.parseInt(number), images);

    }

    @RequestMapping("/{mangaLink}")
    public List<Volume> getByMangaLinkOrderByNumber (@PathVariable String mangaLink, @RequestParam String order) {

        if (order.equals("up")) {
            return volumeService.getByMangaLinkOrderByNumberAsc(mangaLink);
        } else return volumeService.getByMangaLinkOrderByNumberDesc(mangaLink);

    }

    @RequestMapping("/standByNumber")
    public boolean standByNumber (@RequestParam String mangaLink, @RequestParam String number) {
        Volume volume = volumeService.getByMangaLinkAndNumber(mangaLink, Integer.parseInt(number));

        return volume != null;
    }

}
