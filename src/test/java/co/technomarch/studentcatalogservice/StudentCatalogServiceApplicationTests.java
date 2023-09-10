package co.technomarch.studentcatalogservice;

import co.technomarch.studentcatalogservice.exception.StudentNotFoundException;
import co.technomarch.studentcatalogservice.models.Student;
import co.technomarch.studentcatalogservice.resources.StudentCatalogController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
class StudentCatalogServiceApplicationTests {

	@Autowired
	private StudentCatalogController studentCatalogController;

	// Set up the test data
	Student student1 = new Student("P1", "Pratham", LocalDate.parse("2002-08-14"), "Male", 5);
	Student student2 = new Student("B1", "Adi", LocalDate.parse("2002-04-01"), "Male", 4);
	Student student3 = new Student("P2", "Utk", LocalDate.parse("2002-08-05"), "Male", 5);
	Student student4 = new Student("B2", "Prarthana", LocalDate.parse("2005-11-20"), "Female", 1);
	Student student5 = new Student("P3", "Tanvi", LocalDate.parse("2002-08-05"), "Female", 5);


	// Set up test data
	public List<Student> ListOfStudents() {
		List<Student> students = new ArrayList<>();
		students.add(student1);
		students.add(student2);
		students.add(student3);
		students.add(student4);
		students.add(student5);

		return students;
	}

	@BeforeEach
	void setUp() {
		// Clear the database before each test
		studentCatalogController.removeAllStudents();
	}

	@Test
	// Add one student and get one student by id. Verify that the student is added and the student is returned.
	public void test1() throws StudentNotFoundException {

		// Add one student
		studentCatalogController.addStudent(student1);

		// Get one student by id
		Student student = studentCatalogController.getStudentById(student1.getId());

		// Verify that the student is added and the student is returned
		assertThat(student1).usingRecursiveComparison().isEqualTo(student);
	}

	@Test
	// Add one student and get all students. Check if the student is added and if the list of students is correct.
	public void test2() {
		// Add one student
		studentCatalogController.addStudent(student1);

		// Get all students
		List<Student> students = studentCatalogController.getAllStudents();

		// Verify that the student is added and if the list of students is correct
		assertEquals(1, students.size());

		// Verify that the students returned are the same as the expected students
		for (Student student : students) {
			assertThat(student1).usingRecursiveComparison().isEqualTo(student);
		}
	}

	@Test
	// Add many students and get all students. Check if the students are added and if the list of students is correct.
	public void test3() {
		List<Student> students = ListOfStudents();

		// call the addStudents method to test the StudentCatalogResource class
		studentCatalogController.addStudents(students);

		// call the getAllStudents method to test the StudentCatalogResource class
		List<Student> receivedListOfStudents = studentCatalogController.getAllStudents();

		// Verify that the students are added and if the list of students is correct
		for (Student student : students) {
			assertThat(student).usingRecursiveComparison().isEqualTo(receivedListOfStudents.get(students.indexOf(student)));
		}
	}

	@Test
	// add multiple students and get one by id. Check if the student is added and if the student is correct.
	public void test4() throws StudentNotFoundException {
		List<Student> students = ListOfStudents();

		// call the addStudents method to test the StudentCatalogResource class
		studentCatalogController.addStudents(students);

		// set the id of the student to be retrieved
		String id ="B2";

		// call the getStudentById method to test the StudentCatalogResource class
		Student receivedStudent = studentCatalogController.getStudentById(id);

		// find the student with the given name in list of students and get expected student
		Student expectedStudent = students.stream()
				.filter(student -> student.getId().equals(id))
				.findFirst()
				.get();

		// Check if the returned student is correct
		assertThat(expectedStudent).usingRecursiveComparison().isEqualTo(receivedStudent);
	}

	@Test
	// add multiple students and get one by name. Check if the student is added and if the student is correct.
	public void test5() {
		List<Student> students = ListOfStudents();

		// call the addStudents method to test the StudentCatalogResource class
		studentCatalogController.addStudents(students);

		// call the getStudentById method to test the StudentCatalogResource class
		String name = "Utk";
		Student receivedStudent = studentCatalogController.getStudentByName(name);

		// find the student with the given name in list of students and get expected student
		Student expectedStudent = students.stream()
				.filter(student -> student.getName().equals(name))
				.findFirst()
				.get();

		// Check if the returned student is correct
		assertThat(expectedStudent).usingRecursiveComparison().isEqualTo(receivedStudent);
	}

	@Test
	// add multiple students and remove one by id. Check if the student is removed and if the list of students is correct.
	public void test6() {
		List<Student> students = ListOfStudents();

		// call the addStudents method to test the StudentCatalogResource class
		studentCatalogController.addStudents(students);

		// set the id of the student to be removed
		String id = "P2";

		// call the removeStudentById method to test the StudentCatalogResource class
		studentCatalogController.removeStudentById(id);

		// call the getAllStudents method to test the StudentCatalogResource class
		List<Student> receivedListOfStudents = studentCatalogController.getAllStudents();

		// Verify that the size of the received list of students is one less than the size of the original list of students
		assertEquals(students.size() - 1, receivedListOfStudents.size());

		// Verify that the student is removed and if the list of students is correct
		for (Student student : students) {
			if (student.getId().equals(id)) {
				// if the student is removed, then the student should not be in the list of students
				assertThat(student).usingRecursiveComparison().isNotEqualTo(receivedListOfStudents.get(students.indexOf(student)));
				break;
			}
		}
	}

	@Test
	// add multiple students and return those with a given semester. Check if the students are added and if the list of students is correct.
	public void test7() {
		List<Student> students = ListOfStudents();

		// call the addStudents method to test the StudentCatalogResource class
		studentCatalogController.addStudents(students);

		String semester = "5";
		// call the getStudentsBySemester method to test the StudentCatalogResource class
		List<Student> receivedListOfStudents = studentCatalogController.findStudentsByGender(semester);

		// Verify that all the students with the given semester are in the list of students
		for (Student student : receivedListOfStudents) {
			if (Integer.toString(student.getSemester()).equals(semester)) {
				// Verify if the student with the given semester is in the list of students
				assertThat(student).usingRecursiveComparison().isEqualTo(
						students.stream()
								.filter(receivedStudent -> receivedStudent.getId().equals(student.getId()))
								.findFirst()
								.get()
				);
			}
		}
	}

	@Test
	// add multiple students and return those with a given gender. Check if the students are added and if the list of students is correct.
	public void test8()	{
		List<Student> students = ListOfStudents();

		// call the addStudents method to test the StudentCatalogResource class
		studentCatalogController.addStudents(students);

		String gender = "Female";
		// call the getStudentsBySemester method to test the StudentCatalogResource class
		List<Student> receivedListOfStudents = studentCatalogController.findStudentsByGender(gender);

		// Verify that all the students with the given semester are in the list of students
		for (Student student : receivedListOfStudents) {
			if (Integer.toString(student.getSemester()).equals(gender)) {
				// Verify if the student with the given semester is in the list of students
				assertThat(student).usingRecursiveComparison().isEqualTo(
						students.stream()
								.filter(receivedStudent -> receivedStudent.getId().equals(student.getGender()))
								.findFirst()
								.get()
				);
			}
		}
	}

}
