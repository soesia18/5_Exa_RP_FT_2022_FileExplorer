package at.kaindorf.fileexplorer.database;

import at.kaindorf.fileexplorer.pojos.FileItem;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <h3>Created by IntelliJ IDEA.</h3><br>
 * <b>Project:</b> Exa_RP_FT_2022_FileExplorer<br>
 * <b>User:</b> Simon SchÃ¶ggler<br>
 * <b>Date:</b> 19. April 2023<br>
 * <b>Time:</b> 8:29 PM<br>
 */

public interface FileItemRepository extends JpaRepository<FileItem, Long> {
}
