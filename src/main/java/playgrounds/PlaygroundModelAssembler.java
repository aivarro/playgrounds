package playgrounds;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PlaygroundModelAssembler implements RepresentationModelAssembler<Playground, EntityModel<Playground>> {

    @Override
    public EntityModel<Playground> toModel(Playground playground) {
        return EntityModel.of(playground,
                linkTo(methodOn(PlaygroundController.class).one(playground.getId())).withSelfRel(),
                linkTo(methodOn(PlaygroundController.class).all()).withRel("playgrounds"));
    }
}
