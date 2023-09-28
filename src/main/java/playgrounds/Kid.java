package playgrounds;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Kid {

    private Long id;
    private String name;
    private Integer age;
    private Long ticket;
}
