package pt.gmsgarcia.pi.gestor.user;

import java.util.UUID;

public class User {
    private UUID uuid;
    private String name;

    public User(UUID user, String name) {
        this.uuid = user;
        this.name = name;
    }

    public UUID getUUID() {
        return this.uuid;
    }

    public String getName() {
        return this.name;
    }
}
