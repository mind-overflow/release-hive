package wtf.beatrice.releasehive.dtos;

import java.util.UUID;

public class EditUsernameAccountDto
{
    private String username;
    private UUID uuid;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}
