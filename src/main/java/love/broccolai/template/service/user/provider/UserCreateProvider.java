package love.broccolai.template.service.user.provider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import love.broccolai.template.model.user.PlayerUser;
import love.broccolai.template.model.user.User;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.framework.qual.DefaultQualifier;

/**
 * Final provider to use, creates the user.
 */
@DefaultQualifier(NonNull.class)
public final class UserCreateProvider implements PartialUserProvider {

    @Override
    public Map<UUID, User> handleRequests(final List<UUID> requests) {
        Map<UUID, User> results = new HashMap<>();

        for (UUID request : requests) {
            results.put(request, new PlayerUser(request));
        }

        return results;
    }

}
