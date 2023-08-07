package fitnlu.ntpos.resourceservice.entities;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "image")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileEntities {
    @Id
    private String id;
    private String type;
    private long size;
    @Lob
    @Nullable
    @Column(name = "data",length = 20971520)
    private byte[] data;
}
