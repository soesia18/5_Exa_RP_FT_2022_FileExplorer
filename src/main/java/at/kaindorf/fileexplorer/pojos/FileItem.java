package at.kaindorf.fileexplorer.pojos;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * <h3>Created by IntelliJ IDEA.</h3><br>
 * <b>Project:</b> Exa_RP_FT_2022_FileExplorer<br>
 * <b>User:</b> Simon SchÃ¶ggler<br>
 * <b>Date:</b> 19. April 2023<br>
 * <b>Time:</b> 8:29 PM<br>
 */

@Entity
@DiscriminatorValue("FILE")
@NoArgsConstructor
@Data
public class FileItem extends FileObject {
    private Long size;

    public FileItem(String name, LocalDateTime lastModified, Long size) {
        super(name, lastModified);
        this.size = size;
    }

    public String formattedSize () {
        return String.format("%-5d KB", size);
    }
}
