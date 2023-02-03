package ru.hogwarts.school.model;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Data
public class Avatar {

    @Id
    @GeneratedValue
    private Long id;

    private String filePath;
    private long fileSize;
    private String mediaType;

    @Lob
    @Type(type = "org.hibernate.type.ImageType")
    private byte[] data;

    @OneToOne(mappedBy = "avatar")
    private Student student;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Avatar avatar = (Avatar) o;
        return id.equals(avatar.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
