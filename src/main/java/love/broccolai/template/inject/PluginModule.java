package love.broccolai.template.inject;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.sql.DataSource;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.framework.qual.DefaultQualifier;
import org.jdbi.v3.core.Jdbi;

@DefaultQualifier(NonNull.class)
public final class PluginModule extends AbstractModule {

    @Provides
    @Singleton
    public DataSource provideDataSource(final File folder) throws IOException {
        HikariConfig hikariConfig = new HikariConfig();

        Path file = folder.toPath().resolve("storage.db");

        if (!Files.exists(file)) {
            Files.createFile(file);
        }

        hikariConfig.setDriverClassName("org.h2.Driver");
        hikariConfig.setJdbcUrl("jdbc:h2:" + file.toAbsolutePath() + ";MODE=MySQL;DATABASE_TO_LOWER=TRUE");

        hikariConfig.setMaximumPoolSize(10);
        return new HikariDataSource(hikariConfig);
    }

    @Provides
    @Singleton
    public Jdbi provideJdbi(final HikariDataSource dataSource) {
        return Jdbi.create(dataSource);
    }

}
