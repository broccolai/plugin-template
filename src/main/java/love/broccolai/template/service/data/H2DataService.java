package love.broccolai.template.service.data;

import com.google.inject.Inject;
import java.util.Optional;
import java.util.UUID;
import love.broccolai.template.model.user.User;
import love.broccolai.template.utilities.QueriesLocator;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.framework.qual.DefaultQualifier;
import org.jdbi.v3.core.Jdbi;

@DefaultQualifier(NonNull.class)
public class H2DataService implements DataService {

    private final QueriesLocator locator = new QueriesLocator();

    private final Jdbi jdbi;

    @Inject
    public H2DataService(final Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    @Override
    public Optional<User> loadUser(final UUID uuid) {
        return this.jdbi.withHandle(handle -> {
            return handle.createQuery(this.locator.query("select-user"))
                .bind("uuid", uuid)
                .mapTo(User.class)
                .findFirst();
        });
    }

    @Override
    public void saveUser(final User user) {
        this.jdbi.useHandle(handle -> {
            handle.createUpdate(this.locator.query("save-user"))
                .bind("uuid", user.uuid())
                .bind("data", user.data())
                .execute();
        });
    }

}
