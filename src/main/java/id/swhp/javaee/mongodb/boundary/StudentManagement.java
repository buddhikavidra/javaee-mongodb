package id.swhp.javaee.mongodb.boundary;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import id.swhp.javaee.mongodb.entity.Address;
import id.swhp.javaee.mongodb.entity.Student;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.bson.Document;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.result.DeleteResult;
import org.bson.types.ObjectId;

/**
 *
 * @author Sukma Wardana
 * @since 1.0.0
 */
@Stateless
public class StudentManagement {

    public static final String DB_NAME = "demo";
    public static final String COLL_NAME = "students";

    protected MongoDatabase db;
    protected MongoCollection<Document> collection;

    @Inject
    MongoClient mongoClient;

    @PostConstruct
    public void init() {
        this.db = this.mongoClient.getDatabase(DB_NAME);
        this.collection = this.db.getCollection(COLL_NAME);
    }

    public List<Student> findAllStudent() {
        List<Student> students = new ArrayList<>();
        MongoCursor<Document> cursor = this.collection.find().iterator();

        try {

            while (cursor.hasNext()) {

                Document studentBson = cursor.next();
                Document addressBson = studentBson.get("address", Document.class);

                Student student = new Student();
                Address address = new Address();

                String objectId = studentBson.getObjectId("_id").toString();

                student.setId(objectId);
                student.setName(studentBson.getString("name"));
                student.setGender(studentBson.getString("gender"));

                address.setAddress1(addressBson.getString("address1"));
                address.setAddress2(addressBson.getString("address2"));
                address.setCity(addressBson.getString("city"));
                address.setState(addressBson.getString("state"));

                student.setAddress(address);

                students.add(student);
            }

        } finally {
            cursor.close();
        }

        return students;
    }

    public Student findBYName(String name) {
        Student student = new Student();
        Address address = new Address();

        Document studentBson = this.collection.find(eq("name", name)).first();
        Document addressBson = studentBson.get("address", Document.class);

        String objectId = studentBson.getObjectId("_id").toString();

        student.setId(objectId);
        student.setName(studentBson.getString("name"));
        student.setGender(studentBson.getString("gender"));

        address.setAddress1(addressBson.getString("address1"));
        address.setAddress2(addressBson.getString("address2"));
        address.setCity(addressBson.getString("city"));
        address.setState(addressBson.getString("state"));

        student.setAddress(address);
        
        return student;
    }
    
    public void createStudent(Student student) {
        Document doc = new Document();
        Document addressBson = new Document();
        ObjectId id = new ObjectId();
        
        Address address = student.getAddress();
        
        doc.append("name", student.getName());
        doc.append("gender", student.getGender());
        
        addressBson.append("address1", address.getAddress1());
        addressBson.append("address2", address.getAddress2());
        addressBson.append("city", address.getCity());
        addressBson.append("state", address.getState());
        
        doc.append("address", new Document(addressBson));
        
        this.collection.insertOne(doc);
    }
    
    public void updateStudentByName(String name, Student student) {
        Document doc = new Document();
        Document addressBson = new Document();
        
        Address address = student.getAddress();
        
        doc.append("name", student.getName());
        doc.append("gender", student.getGender());
        
        addressBson.append("address1", address.getAddress1());
        addressBson.append("address2", address.getAddress2());
        addressBson.append("city", address.getCity());
        addressBson.append("state", address.getState());
        
        doc.append("address", new Document(addressBson));
        
        // $set is necessary
        this.collection.findOneAndUpdate(eq("name", name), new Document("$set", doc));
    }
    
    public DeleteResult removeByName(String name) {
        DeleteResult deleteResult = this.collection.deleteOne(eq("name", name));
        return deleteResult;
    }
}
