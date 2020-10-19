package pv826.web;

import pv826.entities.Gallery;
import pv826.repositories.GalleryRepository;
import pv826.services.StorageService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletContext;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.util.UUID;

@Controller
public class GalleryController {

    @Autowired
    ServletContext context;
    private final StorageService storageService;

    @Autowired
    private GalleryRepository galleryRepository;

    public GalleryController(StorageService storageService) {
        this.storageService = storageService;
    }


    @GetMapping("/gallery")
    public String home(Model model)
    {
        model.addAttribute("images", galleryRepository.findAll());
        return "gallery/index";
    }

    @GetMapping("/gallery/create")
    public String create()
    {
        return "gallery/create";
    }

    @PostMapping("/gallery/create")
    public String saveDocument(@RequestParam("images[]") MultipartFile[] files,
                               RedirectAttributes redirectAttributes, Gallery model)
    {
        for (int i = 0; i < files.length; i++) {
            MultipartFile file = files[i];
            String name = UUID.randomUUID().toString()+"." +
                    FilenameUtils.getExtension(file.getOriginalFilename());
            try {
                byte[] bytes = file.getBytes();

                Path f = storageService.load("");
                String rootPath= f.toUri().getPath();
                System.out.println("---------"+rootPath);
                File dir = new File(rootPath + File.separator );
                if (!dir.exists())
                    dir.mkdirs();
                // Create the file on server
                File serverFile = new File(dir.getAbsolutePath()
                        + File.separator + name);
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();

                Gallery gallery = new Gallery();
                gallery.setName(name);
                galleryRepository.save(gallery);
            } catch (Exception e) {
                return "You failed to upload " + name + " => " + e.getMessage();
            }
        }
        return "redirect:/gallery";
    }
    @GetMapping("/gallery/delete/{id}")
    public String delete(@PathVariable("id") int id, Model model)
    {
        model.addAttribute("gallery",galleryRepository.findById(id).get());
        return "gallery/delete";
    }

    @PostMapping("gallery/delete")
    public String DeleteProduct(Gallery model) {

        Gallery ddd = galleryRepository.findById(model.getId()).get();

        Path f = storageService.load("");
        String rootPath= f.toUri().getPath();
        File dir = new File(rootPath + File.separator );
        File serverFile = new File(dir.getAbsolutePath()
                + File.separator + ddd.getName());

        if(serverFile.delete())
        {
            System.out.println("File deleted successfully");
        }
        else
        {
            System.out.println("Failed to delete the file");
        }
        galleryRepository.delete(ddd);
        return "redirect:/gallery";
    }

}
