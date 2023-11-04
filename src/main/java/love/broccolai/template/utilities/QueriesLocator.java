package love.broccolai.template.utilities;

import com.google.common.base.Splitter;
import java.util.List;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.framework.qual.DefaultQualifier;
import org.jdbi.v3.core.locator.ClasspathSqlLocator;

@DefaultQualifier(NonNull.class)
public final class QueriesLocator {

    private static final Splitter SPLITTER = Splitter.on(';');
    private final ClasspathSqlLocator locator = ClasspathSqlLocator.create();

    public String query(final String name) {
        return this.locator.locate("queries/" + name);
    }

    public List<String> queries(final String name) {
        return SPLITTER.splitToList(this.locator.locate("queries/" + name));
    }

}
