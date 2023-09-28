package playgrounds;

import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@AllArgsConstructor
@RestController
public class PlaygroundController {

    private final PlaygroundRepository repository;
    private final PlaygroundModelAssembler assembler;

    @GetMapping("/playgrounds")
    CollectionModel<EntityModel<Playground>> all() {

        List<EntityModel<Playground>> playgrounds = repository.getAllPlaygrounds().stream()
                .map(assembler::toModel)
                .collect(toList());

        return CollectionModel.of(playgrounds, linkTo(methodOn(PlaygroundController.class).all()).withSelfRel());
    }

    @PostMapping("/playgrounds")
    ResponseEntity<?> newPlayground(@RequestBody Playground newPlayground) {

        EntityModel<Playground> entityModel = assembler.toModel(repository.save(newPlayground));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @GetMapping("/playgrounds/{id}")
    EntityModel<Playground> one(@PathVariable Long id) {

        Playground playground = repository.findById(id);
        if (playground == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find playground " + id);
        }

        return assembler.toModel(playground);
    }

    @DeleteMapping("/playgrounds/{id}")
    void deletePlayground(@PathVariable Long id) {
        repository.deletePlayground(id);
    }

    @PostMapping("/playgrounds/{pgid}/playsite/{psid}")
    void addKidToPlaySite(@PathVariable Long pgid,
                                       @PathVariable Long psid,
                                       @RequestBody Kid kid) {

        repository.addKidToPlaySite(pgid, psid, kid);
    }
}
