package love.broccolai.template.model.user;

import java.util.UUID;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.framework.qual.DefaultQualifier;

@DefaultQualifier(NonNull.class)
public final class PlayerUser implements User {

    private final UUID uuid;
    private int data;

    public PlayerUser(final UUID uuid, final int data) {
        this.uuid = uuid;
        this.data = data;
    }

    @Override
    public UUID uuid() {
        return this.uuid;
    }

    @Override
    public int data() {
        return this.data;
    }

    @Override
    public void data(final int data) {
        this.data = data;
    }

}
