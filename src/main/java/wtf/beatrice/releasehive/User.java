package wtf.beatrice.releasehive;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name="users")
public class User
{

    @Id
    @Column
    private UUID uuid;

    @Column
    private String username;


    @Column
    private String password;

    public UUID getUuid() {
        return uuid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;


    }
}
