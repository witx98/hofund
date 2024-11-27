package dev.logchange.hofund.connection.spring.datasource.h2;

import dev.logchange.hofund.connection.spring.datasource.DatasourceConnection;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.Locale;

@Slf4j
public class H2Connection extends DatasourceConnection {

    private static final String TEST_QUERY = "SELECT 1";

    private String target;
    private String url;
    private String dbVendor;

    public H2Connection(DatabaseMetaData metaData, DataSource dataSource) {
        super(dataSource, TEST_QUERY);
        try {
            this.url = metaData.getURL();
            int colonIndex = url.lastIndexOf(':');
            this.target = url.substring(colonIndex + 1).toLowerCase(Locale.ROOT);
            this.dbVendor = metaData.getDatabaseProductName();
        } catch (SQLException e) {
            log.warn("Error getting db information", e);
            this.target = "ERROR";
            this.url = "ERROR";
            this.dbVendor = "ERROR";
        }
    }

    @Override
    protected String getTarget() {
        return target;
    }

    @Override
    protected String getUrl() {
        return url;
    }

    @Override
    protected String getDbVendor() {
        return dbVendor;
    }

}
