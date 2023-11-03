package love.broccolai.template.model.user;

import java.util.UUID;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.framework.qual.DefaultQualifier;

@DefaultQualifier(NonNull.class)
public final class PlayerUser implements User {

    private final UUID uuid;

    public PlayerUser(final UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public UUID uuid() {
        return this.uuid;
    }

}
