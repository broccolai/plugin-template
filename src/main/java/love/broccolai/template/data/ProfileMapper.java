package love.broccolai.template.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import love.broccolai.template.model.profile.Profile;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.framework.qual.DefaultQualifier;
import org.jdbi.v3.core.mapper.ColumnMapper;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

@DefaultQualifier(NonNull.class)
public final class ProfileMapper implements RowMapper<Profile> {

    @Override
    public Profile map(final ResultSet rs, final StatementContext ctx) throws SQLException {
        ColumnMapper<UUID> uuidMapper = ctx.findColumnMapperFor(UUID.class).orElseThrow(IllegalStateException::new);

        UUID uniqueId = uuidMapper.map(rs, "uuid", ctx);
        int data = rs.getInt("data");

        return new Profile(uniqueId, data);
    }

}
