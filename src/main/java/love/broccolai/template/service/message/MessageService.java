package love.broccolai.template.service.message;

import love.broccolai.template.service.message.annotations.Receiver;
import net.kyori.adventure.audience.Audience;
import net.kyori.moonshine.annotation.Message;

public interface MessageService {

    @Message("test")
    void test(@Receiver Audience receiver);

}
