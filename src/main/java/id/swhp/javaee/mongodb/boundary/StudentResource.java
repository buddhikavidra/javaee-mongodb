package id.swhp.javaee.mongodb.boundary;

import com.mongodb.client.result.DeleteResult;
import id.swhp.javaee.mongodb.entity.Student;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Sukma Wardana
 * @since 1.0.0
 */
@Stateless
@Path("students")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class StudentResource {
    
    @Inject
    StudentManagement studentManagement;
    
    @GET
    public List<Student> findAll() {
        return this.studentManagement.findAllStudent();
    }
    
    @GET
    @Path("{name}")
    public Student findByName(@PathParam("name") String name) {
        return this.studentManagement.findBYName(name);
    }
    
    @POST
    public void createStudent(Student student) {
        this.studentManagement.createStudent(student);
    }
    
    @PUT
    @Path("{name}")
    public void updateAccount(@PathParam("name") String name, Student student) {
        this.studentManagement.updateStudentByName(name, student);
    }
    
    @DELETE
    @Path("{name}")
    public Response deleteStudent(@PathParam("name") String name) {
        DeleteResult result = this.studentManagement.removeByName(name);
        
        System.out.println(result.getDeletedCount());
        
        return Response.ok().build();
    }
 
}
