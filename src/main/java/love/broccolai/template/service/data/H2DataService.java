package love.broccolai.template.service.data;

import java.util.Optional;
import java.util.UUID;
import love.broccolai.template.model.user.User;

public class H2DataService implements DataService {

    @Override
    public Optional<User> loadUser(final UUID uuid) {
        return Optional.empty();
    }

    @Override
    public void saveUser(final User user) {

    }

}
