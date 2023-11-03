package love.broccolai.template.service.user;

import cloud.commandframework.services.ChunkedRequestContext;
import java.util.Collection;
import java.util.UUID;
import love.broccolai.template.model.user.User;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.framework.qual.DefaultQualifier;

@DefaultQualifier(NonNull.class)
public final class UserServiceContext extends ChunkedRequestContext<UUID, User> {

    UserServiceContext(final Collection<UUID> requests) {
        super(requests);
    }

}
