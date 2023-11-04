package love.broccolai.template.service.user;

import cloud.commandframework.services.ServicePipeline;
import com.google.inject.Inject;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import love.broccolai.template.model.user.User;
import love.broccolai.template.service.user.provider.PartialUserProvider;
import love.broccolai.template.service.user.provider.UserCacheProvider;
import love.broccolai.template.service.user.provider.UserCreateProvider;
import love.broccolai.template.service.user.provider.UserDataProvider;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.framework.qual.DefaultQualifier;

@DefaultQualifier(NonNull.class)
public class PipelineUserService implements UserService {

    private final ServicePipeline pipeline = ServicePipeline.builder().build();
    private final UserCacheProvider cacheProvider;

    @Inject
    public PipelineUserService(
        final UserCreateProvider createProvider,
        final UserDataProvider dataProvider,
        final UserCacheProvider cacheProvider
    ) {
        this.cacheProvider = cacheProvider;

        this.pipeline
            .registerServiceType(PartialUserProvider.TYPE, createProvider)
            .registerServiceImplementation(
                PartialUserProvider.TYPE,
                dataProvider,
                Collections.emptyList()
            )
            .registerServiceImplementation(
                PartialUserProvider.TYPE,
                cacheProvider,
                Collections.emptyList()
            );
    }

    @Override
    public final User get(final UUID uniqueId) {
        return this.get(Collections.singletonList(uniqueId)).get(uniqueId);
    }

    @Override
    public final Map<UUID, User> get(final Collection<UUID> uniqueIds) {
        Map<UUID, User> results = this.pipeline.pump(new UserServiceContext(uniqueIds))
            .through(PartialUserProvider.TYPE)
            .getResult();

        this.cacheProvider.cache(results);

        return results;
    }

    @Override
    public User get(final String username) {
        return this.get(Bukkit.getOfflinePlayer(username).getUniqueId());
    }

    @Override
    public String name(final User user) {
        OfflinePlayer player = Bukkit.getOfflinePlayer(user.uuid());
        return Objects.requireNonNull(player.getName());
    }

}
