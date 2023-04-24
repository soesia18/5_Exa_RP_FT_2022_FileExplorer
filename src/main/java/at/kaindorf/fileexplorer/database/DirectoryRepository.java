package at.kaindorf.fileexplorer.database;

import at.kaindorf.fileexplorer.pojos.Directory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * <h3>Created by IntelliJ IDEA.</h3><br>
 * <b>Project:</b> Exa_RP_FT_2022_FileExplorer<br>
 * <b>User:</b> Simon SchÃ¶ggler<br>
 * <b>Date:</b> 19. April 2023<br>
 * <b>Time:</b> 8:29 PM<br>
 */

public interface DirectoryRepository extends JpaRepository<Directory, Long> {
    Directory getDirectoryByName(String name);
    @Query("SELECT d FROM Directory d WHERE d.parent = null")
    Directory getRootDirectory();
}
