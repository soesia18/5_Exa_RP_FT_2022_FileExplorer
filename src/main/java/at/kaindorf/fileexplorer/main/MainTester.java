package at.kaindorf.fileexplorer.main;

import at.kaindorf.fileexplorer.database.DirectoryRepository;
import at.kaindorf.fileexplorer.database.FileItemRepository;
import at.kaindorf.fileexplorer.database.LinkRepository;
import at.kaindorf.fileexplorer.pojos.Directory;
import at.kaindorf.fileexplorer.pojos.FileItem;
import at.kaindorf.fileexplorer.pojos.FileObject;
import at.kaindorf.fileexplorer.pojos.Link;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <h3>Created by IntelliJ IDEA.</h3><br>
 * <b>Project:</b> Exa_RP_FT_2022_FileExplorer<br>
 * <b>User:</b> Simon SchÃ¶ggler<br>
 * <b>Date:</b> 19. April 2023<br>
 * <b>Time:</b> 8:29 PM<br>
 */

@Component
@AllArgsConstructor
public class MainTester {

    private DirectoryRepository directoryRepository;
    private FileItemRepository fileItemRepository;
    private LinkRepository linkRepository;

    @PostConstruct
    public void tester () {
        /*FileItem fileItem = new FileItem("filesysdb.sql", LocalDateTime.now(), 100L);
        fileItemRepository.save(fileItem);*/
        List<Directory> directories = directoryRepository.findAll();
        List<FileItem> fileItems = fileItemRepository.findAll();
        List<Link> links = linkRepository.findAll();



        System.out.println();
    }
}
