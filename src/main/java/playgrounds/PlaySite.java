package playgrounds;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlaySite {

    private Long id;
    private PlaySiteType type;
    private Integer capacity;
    private List<Kid> kidsOnSite;
    private List<Kid> kidsInQueue;
}
