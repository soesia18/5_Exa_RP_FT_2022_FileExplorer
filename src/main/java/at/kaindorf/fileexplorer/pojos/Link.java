package at.kaindorf.fileexplorer.pojos;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * <h3>Created by IntelliJ IDEA.</h3><br>
 * <b>Project:</b> Exa_RP_FT_2022_FileExplorer<br>
 * <b>User:</b> Simon SchÃ¶ggler<br>
 * <b>Date:</b> 19. April 2023<br>
 * <b>Time:</b> 8:29 PM<br>
 */

@Entity
@DiscriminatorValue("LINK")
@NoArgsConstructor
@Data
public class Link extends FileObject {
    private String source;

    public Link(String name, LocalDateTime lastModified, String source) {
        super(name, lastModified);
        this.source = source;
    }
}
