package app.com.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;
import java.sql.Connection;

@Component
public class DatabaseCheckRunner implements CommandLineRunner {

    private final DataSource dataSource;

    public DatabaseCheckRunner(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void run(String... args) throws Exception {
        try (Connection connection = dataSource.getConnection()) {
            System.out.println("==================================================");
            System.out.println("CONNECTED DB URL: " + connection.getMetaData().getURL());
            System.out.println("CONNECTED DB USER: " + connection.getMetaData().getUserName());
            System.out.println("==================================================");
        }
    }
}