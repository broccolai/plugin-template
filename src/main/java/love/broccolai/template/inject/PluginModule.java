package love.broccolai.template.inject;

public final class PluginModule extends AbstractModule {

    @Provides
    @Singleton
    public @NonNull HikariDataSource provideDataSource(
            final @NonNull File folder,
            final @NonNull MainConfiguration configuration
    ) throws IOException {
        HikariConfig hikariConfig = new HikariConfig();

        //todo(josh): cleanup
        if (configuration.storage.storageMethod == StorageMethod.H2) {
            Path file = folder.toPath().resolve("storage.db");
            if (!Files.exists(file)) {
                Files.createFile(file);
            }
            hikariConfig.setDriverClassName("org.h2.Driver");
            hikariConfig.setJdbcUrl("jdbc:h2:" + file.toAbsolutePath() + ";MODE=MySQL;DATABASE_TO_LOWER=TRUE");
        }

        hikariConfig.setMaximumPoolSize(10);
        return new HikariDataSource(hikariConfig);
    }

    @Provides
    @Singleton
    public @NonNull Jdbi provideJdbi(
            final @NonNull HikariDataSource dataSource
    ) {
        return Jdbi.create(dataSource)
                .registerRowMapper(TagsUser.class, new UserMapper());
    }

}
