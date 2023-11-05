package love.broccolai.template.utilities;

import com.google.common.base.Splitter;
import java.util.List;
import love.broccolai.template.TemplatePlugin;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.framework.qual.DefaultQualifier;
import org.jdbi.v3.core.locator.ClasspathSqlLocator;
import org.jdbi.v3.core.locator.internal.ClasspathBuilder;

@DefaultQualifier(NonNull.class)
public final class QueriesLocator {

    private static final String QUERY_FOLDER = "queries/";
    private static final String SQL_EXTENSION = "sql";

    private static final Splitter SPLITTER = Splitter.on(';');
    private final ClasspathSqlLocator locator = ClasspathSqlLocator.create();

    public String query(final String name) {
        return this.locate(name);
    }

    public List<String> queries(final String name) {
        return SPLITTER.splitToList(this.locate(name));
    }

    private String locate(final String name) {
        return this.locator.getResource(
            TemplatePlugin.class.getClassLoader(),
            new ClasspathBuilder()
                .appendDotPath(QUERY_FOLDER + name)
                .setExtension(SQL_EXTENSION)
                .build()
        );
    }

}
