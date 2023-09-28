package playgrounds;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PlaygroundRepository {

    public List<Playground> playgrounds = new ArrayList<>();

    public void createPlaygrounds() {
        Kid firstKid = new Kid(1L, "Testkid Testo", 11, 1L);
        Kid secondKid = new Kid(2L, "Kiddo McTesto", 8, 2L);

        PlaySite playSite1 = new PlaySite(1L, PlaySiteType.DOUBLE_SWINGS, 10, List.of(firstKid), List.of(secondKid));
        PlaySite playSite2 = new PlaySite(1L, PlaySiteType.BALL_PIT, 10, List.of(firstKid), List.of(secondKid));

        playgrounds = List.of(
                new Playground(1L, List.of(playSite1)),
                new Playground(2L, List.of(playSite2)),
                new Playground(3L, List.of(playSite1, playSite2))
        );
    }

    public List<Playground> getAllPlaygrounds() {
        return playgrounds;
    }

    public Playground findById(Long id) {

        for (Playground playground : playgrounds) {
            if (playground.getId().equals(id)) {
                return playground;
            }
        }
        return null;
    }

    public Playground save(Playground p) {
        Playground playground = new Playground();
        playground.setId(p.getId());
        playground.setPlaySites(p.getPlaySites());
        playgrounds.add(playground);
        return playground;
    }

    public void deletePlayground(Long id) {
        Playground playground = findById(id);
        if (playground != null) {
            playgrounds.remove(playground);
        }
    }

    public Kid addKidToPlaySite(Long pgid, Long psid, Kid k) {
        Playground playground = findById(pgid);
        List<PlaySite> playSites = playground.getPlaySites();
        PlaySite playSite = null;
        for (PlaySite ps: playSites) {
            if (ps.getId().equals(psid)) {
                playSite = ps;
            }
        }

        Kid kid = new Kid();
        kid.setId(k.getId());
        kid.setAge(k.getAge());
        kid.setName(k.getName());
        kid.setTicket(k.getTicket());

        if (playSite != null) {

            List<Kid> kidsOnSite = playSite.getKidsOnSite();
            List<Kid> kidsInQueue = playSite.getKidsInQueue();

            if (playSite.getCapacity() > kidsOnSite.size()) {
                kidsOnSite.add(kid);
            } else {
                kidsInQueue.add(kid);
            }
        }

        return kid;
    }
}
