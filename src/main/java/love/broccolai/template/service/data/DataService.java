package love.broccolai.template.service.data;

import java.util.Optional;
import java.util.UUID;
import love.broccolai.template.model.user.User;
import love.broccolai.template.service.Service;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.framework.qual.DefaultQualifier;

@DefaultQualifier(NonNull.class)
public interface DataService extends Service {

    Optional<User> loadUser(UUID uuid);

    void saveUser(User user);

}
