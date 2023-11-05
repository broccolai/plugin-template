package love.broccolai.template.commands.arguments;

import cloud.commandframework.arguments.CommandArgument;
import cloud.commandframework.arguments.parser.ArgumentParseResult;
import cloud.commandframework.arguments.parser.ArgumentParser;
import cloud.commandframework.context.CommandContext;
import cloud.commandframework.exceptions.parsing.NoInputProvidedException;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;
import java.util.List;
import java.util.Queue;
import java.util.regex.Pattern;
import love.broccolai.template.model.user.User;
import love.broccolai.template.service.user.UserService;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.DefaultQualifier;

@DefaultQualifier(NonNull.class)
public class UserArgument extends CommandArgument<CommandSender, User> {

    @AssistedInject
    public UserArgument(
            final UserService userService,
            final @Assisted("name") String name,
            final @Assisted("required") boolean required
    ) {
        super(required, name, new UserParser(userService), User.class);
    }

    public static final class UserParser implements ArgumentParser<CommandSender, User> {

        private static final Pattern USERNAME_PATTERN = Pattern.compile("^[a-zA-Z0-9_]{2,16}$");

        private final UserService userService;

        public UserParser(final UserService userService) {
            this.userService = userService;
        }

        @Override
        public ArgumentParseResult<User> parse(
                final CommandContext<CommandSender> commandContext,
                final Queue<String> inputQueue
        ) {
            @Nullable String input = inputQueue.peek();

            if (input == null || !USERNAME_PATTERN.matcher(input).matches()) {
                return ArgumentParseResult.failure(new NoInputProvidedException(UserArgument.class, commandContext));
            }

            User user = this.userService.get(input);

            inputQueue.remove();
            return ArgumentParseResult.success(user);
        }

        @Override
        public List<String> suggestions(
                final CommandContext<CommandSender> commandContext,
                final String input
        ) {
            return Bukkit.getOnlinePlayers()
                .stream()
                .map(Player::getName)
                .toList();
        }

    }

}
