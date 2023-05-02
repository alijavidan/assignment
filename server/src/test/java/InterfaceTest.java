import com.internet.engineering.IECA8.domain.CourseEnrolment.CourseEnrolment;
import com.internet.engineering.IECA8.domain.CourseEnrolment.Student.Student;
import com.internet.engineering.IECA8.domain.CourseEnrolment.Student.WeeklyScheduleItem;
import com.internet.engineering.IECA8.domain.CourseEnrolment.System.Offering;
import com.internet.engineering.IECA8.repository.Repository;
import com.internet.engineering.IECA8.services.CourseEnrolmentService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InterfaceTest {
    @Before
    public void beforeInterfaceTest() {
//        System.out.println("before interface test");
    }

    @Test
    public void EnrolmentError() {
        try {

            Repository.getInstance().createAllTables();

            CourseEnrolmentService.getInstance().importCoursesFromWeb();
            CourseEnrolmentService.getInstance().importStudentsFromWeb();

            Student student = CourseEnrolment.getInstance().getStudent("01");

            CourseEnrolment.importStudentGradesFromWeb(student);

            CourseEnrolment.getInstance().addToWeeklySchedule("01", "02", "01");
            CourseEnrolment.getInstance().addToWeeklySchedule("01", "04", "01");
            CourseEnrolment.getInstance().addToWeeklySchedule("01", "05", "01");
            CourseEnrolment.getInstance().addToWeeklySchedule("01", "06", "01");

            CourseEnrolment.getInstance().finalizeWeeklySchedule(
                    "01"
            );

            Assert.assertEquals("EnrolmentErrorTestNotPassed",
                    Arrays.asList("02", "04", "05", "06"),
                    student.getSelectedCourses()
                            .stream()
                            .filter(weeklyScheduleItem -> weeklyScheduleItem.getStatus().equals("finalized"))
                            .map(WeeklyScheduleItem::getOffering)
                            .map(Offering::getCode)
                            .collect(Collectors.toList())
            );

        } catch (Exception e) {
            System.out.println(e.getMessage());
            Assert.fail();
        }
    }

    @After
    public void afterInterfaceTest() {
//        System.out.println("after interface test");
    }
}
