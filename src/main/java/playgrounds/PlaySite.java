package playgrounds;

import lombok.*;

import java.util.List;

import static playgrounds.PlaySiteType.DOUBLE_SWINGS;

@NoArgsConstructor
@Data
public class PlaySite {

    private Long id;
    private PlaySiteType type;
    private Integer capacity;
    private List<Kid> kidsOnSite;
    private List<Kid> kidsInQueue;
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private Integer utilization;

    public PlaySite(Long id, PlaySiteType type, Integer capacity, List<Kid> kidsOnSite, List<Kid> kidsInQueue) {
        this.id = id;
        this.type = type;
        this.capacity = capacity;
        this.kidsOnSite = kidsOnSite;
        this.kidsInQueue = kidsInQueue;
    }

    public Integer getUtilization() {
        if (kidsOnSite == null || kidsOnSite.isEmpty()) {
            return 0;
        } else {

            int percentage = 100 * kidsOnSite.size() / capacity;

            if (type.equals(DOUBLE_SWINGS)) {
                if (kidsOnSite.size() == 1) {
                    return 0;
                } else {
                    return percentage;
                }
            } else {
                return percentage;
            }
        }
    }
}
