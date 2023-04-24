package at.kaindorf.fileexplorer.pojos;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <h3>Created by IntelliJ IDEA.</h3><br>
 * <b>Project:</b> Exa_RP_FT_2022_FileExplorer<br>
 * <b>User:</b> Simon SchÃ¶ggler<br>
 * <b>Date:</b> 19. April 2023<br>
 * <b>Time:</b> 8:29 PM<br>
 */

@Entity
@DiscriminatorColumn(name = "type")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NoArgsConstructor
@Data
public abstract class FileObject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private LocalDateTime lastModified;
    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "parent")
    private Directory parent;

    public FileObject(String name, LocalDateTime lastModified) {
        this.name = name;
        this.lastModified = lastModified;
    }

    public boolean isDir () {
        return this instanceof Directory;
    }

    public FileItem getFileItem () {
        return this instanceof FileItem ? (FileItem) this : null;
    }

    public String formattedModifiedDateTime () {
        return lastModified.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
    }
}
