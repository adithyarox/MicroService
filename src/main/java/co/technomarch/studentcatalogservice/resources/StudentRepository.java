package co.technomarch.studentcatalogservice.resources;

import co.technomarch.studentcatalogservice.models.Student;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Qualifier("studentRepository")
public interface StudentRepository extends MongoRepository<Student, String> {

    @Query("{id:'?0'}")
    Student findStudentById(String name);

    @Query("{name:'?0'}")
    Student findStudentByName(String name);

    @Query(value="{category:'?0'}")
    List<Student> findAll(String category);

    @Query(value="{semester:?0}")
    List<Student> findAllBySemester(int semester);

    @Query(value="{gender:'?0'}")
    List<Student> findAllByGender(String gender);

    public long count();

}
