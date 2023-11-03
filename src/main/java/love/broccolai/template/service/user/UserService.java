package love.broccolai.template.service.user;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import love.broccolai.template.model.user.User;
import love.broccolai.template.service.Service;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.framework.qual.DefaultQualifier;

@DefaultQualifier(NonNull.class)
public interface UserService extends Service {

    User get(UUID uuid);

    User get(String username);

    Map<UUID, User> get(Collection<UUID> uuids);

    String name(User user);

    Collection<String> onlineNames();

}
