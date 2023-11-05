package love.broccolai.template.factory;

import com.google.inject.assistedinject.Assisted;
import love.broccolai.template.commands.arguments.ProfileArgument;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.framework.qual.DefaultQualifier;

@DefaultQualifier(NonNull.class)
public interface CloudArgumentFactory {

    ProfileArgument profile(
        @Assisted("name") @NonNull String name,
        @Assisted("required") boolean required
    );

}
