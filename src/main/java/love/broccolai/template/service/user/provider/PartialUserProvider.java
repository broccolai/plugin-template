package love.broccolai.template.service.user.provider;

import cloud.commandframework.services.types.PartialResultService;
import io.leangen.geantyref.TypeToken;
import java.util.UUID;
import love.broccolai.template.model.user.User;
import love.broccolai.template.service.user.UserServiceContext;

public interface PartialUserProvider extends PartialResultService<UUID, User, UserServiceContext> {

    TypeToken<PartialUserProvider> TYPE = TypeToken.get(PartialUserProvider.class);

}
