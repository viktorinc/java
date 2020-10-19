package pv826.web;

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
import pv826.entities.Category;
import pv826.repositories.CategoryRepository;
import pv826.services.StorageService;

import javax.servlet.ServletContext;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.util.UUID;

@Controller
public class CategoryController {

    @Autowired
    ServletContext context;
    private final StorageService storageService;

    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryController(StorageService storageService) {
        this.storageService = storageService;
    }


    @GetMapping("/category")
    public String home(Model model)
    {
        model.addAttribute("categories", categoryRepository.findAll());
        return "category/index";
    }

    @GetMapping("/category/create")
    public String create()
    {
        return "category/create";
    }

    @PostMapping("/category/create")
    public String saveDocument(@RequestParam("images[]") MultipartFile[] files,
                               RedirectAttributes redirectAttributes, Category model)
    {
        for (int i = 0; i < files.length; i++) {
            MultipartFile file = files[i];
            String imageName = UUID.randomUUID().toString()+"." +
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
                        + File.separator + imageName);
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();

                Category category = new Category();
                category.setName(model.getName());
                category.setImage(imageName);
                categoryRepository.save(category);
            } catch (Exception e) {
                return "You failed to upload " + imageName + " => " + e.getMessage();
            }
        }
        return "redirect:/category";
    }
    @GetMapping("/category/delete/{id}")
    public String delete(@PathVariable("id") int id, Model model)
    {
        model.addAttribute("category",categoryRepository.findById(id).get());
        return "category/delete";
    }

    @PostMapping("category/delete")
    public String DeleteProduct(Category model) {

        Category category = categoryRepository.findById(model.getId()).get();

        Path f = storageService.load("");
        String rootPath= f.toUri().getPath();
        File dir = new File(rootPath + File.separator );
        File serverFile = new File(dir.getAbsolutePath()
                + File.separator + category.getImage());

        if(serverFile.delete())
        {
            System.out.println("File deleted successfully");
        }
        else
        {
            System.out.println("Failed to delete the file");
        }
        categoryRepository.delete(category);
        return "redirect:/category";
    }

}
