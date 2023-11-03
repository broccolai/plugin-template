package love.broccolai.template.service.user.provider;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import love.broccolai.template.model.user.User;
import love.broccolai.template.service.data.DataService;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.framework.qual.DefaultQualifier;

@Singleton
@DefaultQualifier(NonNull.class)
public final class UserDataProvider implements PartialUserProvider {

    private final DataService dataService;

    @Inject
    public UserDataProvider(final DataService dataService) {
        this.dataService = dataService;
    }

    @Override
    public Map<UUID, User> handleRequests(final List<UUID> requests) {
        Map<UUID, User> results = new HashMap<>();

        for (final UUID uuid : requests) {
            this.dataService
                .loadUser(uuid)
                .ifPresent(user -> results.put(uuid, user));
        }

        return results;
    }

}
