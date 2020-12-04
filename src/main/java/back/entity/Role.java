package back.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "role")
public class Role extends AbstractEntity {

    private String name;

    @ManyToMany(mappedBy = "roles")
    @JoinColumn(name = "user_id")
    private List<User> user;

}
