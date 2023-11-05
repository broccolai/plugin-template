package love.broccolai.template.factory;

import com.google.inject.assistedinject.Assisted;
import love.broccolai.template.commands.arguments.UserArgument;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.framework.qual.DefaultQualifier;

@DefaultQualifier(NonNull.class)
public interface CloudArgumentFactory {

    UserArgument user(
        @Assisted("name") @NonNull String name,
        @Assisted("required") boolean required
    );

}
