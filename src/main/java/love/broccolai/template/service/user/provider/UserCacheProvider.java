package love.broccolai.template.service.user.provider;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.io.Closeable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import love.broccolai.template.model.user.ConsoleUser;
import love.broccolai.template.model.user.User;
import love.broccolai.template.service.data.DataService;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.DefaultQualifier;

@Singleton
@DefaultQualifier(NonNull.class)
public final class UserCacheProvider implements PartialUserProvider, Closeable {

    private final DataService dataService;

    private final Cache<UUID, User> uuidCache;

    @Inject
    public UserCacheProvider(final DataService dataService) {
        this.dataService = dataService;
        this.uuidCache = CacheBuilder.newBuilder()
                .maximumSize(100)
                .<UUID, User>removalListener(notification -> this.dataService.saveUser(notification.getValue()))
                .build();
    }

    @Override
    public Map<UUID, User> handleRequests(final List<UUID> requests) {
        Map<UUID, User> results = new HashMap<>();

        for (final UUID request : requests) {
            if (request.equals(ConsoleUser.FAKE_ID)) {
                results.put(request, User.CONSOLE);
                continue;
            }

            @Nullable User user = this.uuidCache.getIfPresent(request);

            if (user != null) {
                results.put(request, user);
            }
        }

        return results;
    }

    public void cache(final Map<UUID, User> entries) {
        this.uuidCache.putAll(entries);
    }

    @Override
    public void close() {
        this.uuidCache.asMap().values().forEach(this.dataService::saveUser);
    }

}
