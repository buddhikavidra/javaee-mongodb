package id.swhp.javaee.mongodb;

import com.mongodb.MongoClient;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Singleton;

/**
 * MongoDB Connection Factory.
 *
 * @author Sukma Wardana
 * @since 1.0.0
 */
@Singleton
public class MongoConnection {

    @Produces
    @ApplicationScoped
    public MongoClient mongoClient() {
        MongoClient mongo = new MongoClient("localhost", 27017);
        return mongo;
    }
}
