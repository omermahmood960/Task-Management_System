package app.com.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class DatabaseManager {
    @PersistenceContext
    private EntityManager entityManager;
    @Transactional
    public void dropTable(String tableName) {
        String sql = "Drop table if exists " + tableName;
        entityManager.createNativeQuery(sql).executeUpdate();
    }
}