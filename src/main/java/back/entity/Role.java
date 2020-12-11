package back.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
public class Role extends AbstractEntity {

    public static final Long ROLE_USER = 1L;
    public static final Long ROLE_ADMIN = 2L;

    private String name;

    @ManyToMany
    @JoinTable(name = "user2role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id",
                    referencedColumnName = "id"))
    private List<User> user;

    public Role(Long id){
        this.setId(id);
    }

}
