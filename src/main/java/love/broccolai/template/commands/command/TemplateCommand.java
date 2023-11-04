package love.broccolai.template.commands.command;

import cloud.commandframework.Command;
import cloud.commandframework.CommandManager;
import cloud.commandframework.context.CommandContext;
import com.google.inject.Inject;
import love.broccolai.template.factory.CloudArgumentFactory;
import love.broccolai.template.service.message.MessageService;
import love.broccolai.template.service.user.UserService;
import org.bukkit.command.CommandSender;

public final class TemplateCommand implements PluginCommand {

    private final CloudArgumentFactory argumentFactory;
    private final MessageService messageService;
    private final UserService userService;

    @Inject
    public TemplateCommand(
        final CloudArgumentFactory argumentFactory,
        final MessageService messageService,
        final UserService userService
    ) {
        this.argumentFactory = argumentFactory;
        this.messageService = messageService;
        this.userService = userService;
    }

    @Override
    public void register(final CommandManager<CommandSender> commandManager) {
        Command.Builder<CommandSender> baseCommand = commandManager.commandBuilder("template");

        commandManager.command(baseCommand
            .literal("test")
            .handler(this::handleSelect)
        );
    }

    private void handleSelect(final CommandContext<CommandSender> context) {
        CommandSender sender = context.getSender();

        this.messageService.test(sender);
    }

}
