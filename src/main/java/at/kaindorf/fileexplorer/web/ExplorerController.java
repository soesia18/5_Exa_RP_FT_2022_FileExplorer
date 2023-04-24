package at.kaindorf.fileexplorer.web;

import at.kaindorf.fileexplorer.database.DirectoryRepository;
import at.kaindorf.fileexplorer.database.FileItemRepository;
import at.kaindorf.fileexplorer.database.LinkRepository;
import at.kaindorf.fileexplorer.pojos.Directory;
import at.kaindorf.fileexplorer.pojos.FileItem;
import at.kaindorf.fileexplorer.pojos.FileObject;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <h3>Created by IntelliJ IDEA.</h3><br>
 * <b>Project:</b> Exa_RP_FT_2022_FileExplorer<br>
 * <b>User:</b> Simon SchÃ¶ggler<br>
 * <b>Date:</b> 19. April 2023<br>
 * <b>Time:</b> 8:29 PM<br>
 */

@Controller
@RequestMapping("/explorer")
@Slf4j
@SessionAttributes({"dir", "url"})
public class ExplorerController {

    private DirectoryRepository directoryRepository;
    private FileItemRepository fileItemRepository;
    private LinkRepository linkRepository;

    private final Stack<String> pathUrl = new Stack<>();
    private final Directory rootDirectory;

    public ExplorerController(DirectoryRepository directoryRepository, FileItemRepository fileItemRepository, LinkRepository linkRepository) {
        this.directoryRepository = directoryRepository;
        this.fileItemRepository = fileItemRepository;
        this.linkRepository = linkRepository;
        pathUrl.add("C:");
        rootDirectory = directoryRepository.getRootDirectory();
    }

    @ModelAttribute("url")
    public String url () {
        return rootDirectory.getName();
    }

    @ModelAttribute("dir")
    public Directory directory () {
        return rootDirectory;
    }

    private void updateModel (Model model, Directory dir) {
        //String dir = url.substring(url.lastIndexOf("\\") == -1 ? 0 : url.lastIndexOf("\\"));
        //dir.getContent().sort(Comparator.comparing(fileObject -> fileObject.getClass().getSimpleName()).thenComparing(o -> ((FileObject)o).getName()));
        dir.getContent().sort(Comparator.comparing(FileObject::getName));
        dir.getContent().sort(Comparator.comparing(fileObject -> !(fileObject instanceof Directory)));
        model.addAttribute("dir", dir);
        model.addAttribute("url", String.join("\\", pathUrl));
    }

    @GetMapping("")
    public ModelAndView getDirContent (@RequestParam(required = false) Directory dir,  Model model) {
        log.debug("/explorer ... GET REQUEST");

        if (dir != null) {
            pathUrl.push(dir.getName());
        } else {
            dir = directory();
        }
        updateModel(model, dir);
        return new ModelAndView("ExplorerView");
    }

    @PostMapping("/up")
    public ModelAndView getUpDirContent (@RequestParam Directory dir, Model model) {
        log.debug("/explorer/up ... POST REQUEST");

        if (pathUrl.size() > 1) {
            pathUrl.pop();
        }

        Directory parent = dir.getParent() == null ? directory() : (Directory) dir.getParent();
        updateModel(model, parent);
        return new ModelAndView("ExplorerView");
    }

    @PostMapping(value = "/create", params = "inputType=dir")
    public ModelAndView createDirContent (@RequestParam String name, @SessionAttribute Directory dir, Model model) {
        Directory directory = new Directory(name, LocalDateTime.now(), new ArrayList<>());

        directory.setParent(dir);
        dir.getContent().add(directory);

        directoryRepository.save(directory);

        updateModel(model, dir);
        return new ModelAndView("ExplorerView");
    }

    @PostMapping(value = "/create", params = "inputType=file")
    public ModelAndView createFileContent (@RequestParam String name, @SessionAttribute Directory dir, Model model) {
        Random random = new Random();
        FileItem fileItem = new FileItem(name, LocalDateTime.now(), random.nextLong(100, 10000+1));

        fileItem.setParent(dir);
        dir.addContent(fileItem);

        fileItemRepository.save(fileItem);

        updateModel(model, dir);
        return new ModelAndView("ExplorerView");
    }

}
