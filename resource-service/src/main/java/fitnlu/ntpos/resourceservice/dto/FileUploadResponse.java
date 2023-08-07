package fitnlu.ntpos.resourceservice.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FileUploadResponse {
    private String id;
    private String url;
    private String type;
    private long size;
}
