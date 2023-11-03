package love.broccolai.template.model.user;

import java.util.UUID;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.framework.qual.DefaultQualifier;

@DefaultQualifier(NonNull.class)
public final class ConsoleUser implements User {

    public static final UUID FAKE_ID = new UUID(0, 0);

    @Override
    public UUID uuid() {
        return FAKE_ID;
    }

}
