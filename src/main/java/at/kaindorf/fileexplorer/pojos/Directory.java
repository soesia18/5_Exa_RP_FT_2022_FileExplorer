package at.kaindorf.fileexplorer.pojos;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <h3>Created by IntelliJ IDEA.</h3><br>
 * <b>Project:</b> Exa_RP_FT_2022_FileExplorer<br>
 * <b>User:</b> Simon SchÃ¶ggler<br>
 * <b>Date:</b> 19. April 2023<br>
 * <b>Time:</b> 8:29 PM<br>
 */

@Entity
@DiscriminatorValue("DIR")
@NoArgsConstructor
@Data
public class Directory extends FileObject {

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "parent")
    private List<FileObject> content;

    public Directory(String name, LocalDateTime lastModified, List<FileObject> content) {
        super(name, lastModified);
        this.content = content;
    }

    public void addContent (FileObject fileObject) {
        if (content == null) {
            content = new ArrayList<>();
        }
        content.add(fileObject);
    }
}
