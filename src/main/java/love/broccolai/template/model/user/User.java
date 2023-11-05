package love.broccolai.template.model.user;

import java.util.UUID;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.framework.qual.DefaultQualifier;

@DefaultQualifier(NonNull.class)
public interface User {

    User CONSOLE = new ConsoleUser();

    UUID uuid();

    int data();

    void data(int data);

}
