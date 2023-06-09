package com.internet.engineering.IECA8.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.internet.engineering.IECA8.domain.CourseEnrolment.CourseEnrolment;
import com.internet.engineering.IECA8.domain.CourseEnrolment.Student.Student;
import com.internet.engineering.IECA8.domain.CourseEnrolment.System.Day;
import com.internet.engineering.IECA8.domain.CourseEnrolment.System.Offering;
import com.internet.engineering.IECA8.domain.CourseEnrolment.System.Prerequisite;
import com.internet.engineering.IECA8.utils.HTTPRequestHandler.HTTPRequestHandler;
import com.internet.engineering.IECA8.repository.Repository;
import com.internet.engineering.IECA8.utils.StringUtils;

import java.util.List;

public class CourseEnrolmentService {

    private static CourseEnrolmentService instance;


    private CourseEnrolmentService() {}

    public static CourseEnrolmentService getInstance() {
        if (instance == null) {
            instance = new CourseEnrolmentService();
        }
        return instance;
    }

    public static void importCoursesFromWeb() throws Exception {
//        String CoursesJsonString = HTTPRequestHandler.getRequest("http://138.197.181.131:5200/api/courses");
        String CoursesJsonString = "[\n" +
                "    {\n" +
                "        \"code\": \"01\",\n" +
                "        \"classCode\": \"01\",\n" +
                "        \"name\": \"Math 1\",\n" +
                "        \"instructor\": \"Mr. \",\n" +
                "        \"units\": 3,\n" +
                "        \"type\": \"Fundamental\",\n" +
                "        \"classTime\": {\n" +
                "            \"days\": [\n" +
                "                \"Saturday\",\n" +
                "                \"Monday\"\n" +
                "            ],\n" +
                "            \"time\": \"07:30 - 09:00\"\n" +
                "        },\n" +
                "        \"examTime\": {\n" +
                "            \"start\": \"1402/04/01 07:00\",\n" +
                "            \"end\": \"1402/04/01 08:00\"\n" +
                "        },\n" +
                "        \"capacity\": 10,\n" +
                "        \"signedUp\": 5,\n" +
                "        \"prerequisites\": [\n" +
                "\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"code\": \"02\",\n" +
                "        \"classCode\": \"01\",\n" +
                "        \"name\": \"Differential Equations\",\n" +
                "        \"instructor\": \"Mr. \",\n" +
                "        \"units\": 3,\n" +
                "        \"type\": \"Fundamental\",\n" +
                "        \"classTime\": {\n" +
                "            \"days\": [\n" +
                "                \"Saturday\",\n" +
                "                \"Monday\"\n" +
                "            ],\n" +
                "            \"time\": \"09:00 - 10:30\"\n" +
                "        },\n" +
                "        \"examTime\": {\n" +
                "            \"start\": \"1402/04/01 08:00\",\n" +
                "            \"end\": \"1402/04/01 09:00\"\n" +
                "        },\n" +
                "        \"capacity\": 10,\n" +
                "        \"signedUp\": 5,\n" +
                "        \"prerequisites\": [\n" +
                "            \"01\"\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"code\": \"03\",\n" +
                "        \"classCode\": \"01\",\n" +
                "        \"name\": \"Physics 1\",\n" +
                "        \"instructor\": \"Mr. \",\n" +
                "        \"units\": 3,\n" +
                "        \"type\": \"Fundamental\",\n" +
                "        \"classTime\": {\n" +
                "            \"days\": [\n" +
                "                \"Saturday\",\n" +
                "                \"Monday\"\n" +
                "            ],\n" +
                "            \"time\": \"10:30 - 12:00\"\n" +
                "        },\n" +
                "        \"examTime\": {\n" +
                "            \"start\": \"1402/04/01 09:00\",\n" +
                "            \"end\": \"1402/04/01 10:00\"\n" +
                "        },\n" +
                "        \"capacity\": 10,\n" +
                "        \"signedUp\": 5,\n" +
                "        \"prerequisites\": [\n" +
                "            \"01\"\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"code\": \"04\",\n" +
                "        \"classCode\": \"01\",\n" +
                "        \"name\": \"Math 2\",\n" +
                "        \"instructor\": \"Mr. \",\n" +
                "        \"units\": 3,\n" +
                "        \"type\": \"Fundamental\",\n" +
                "        \"classTime\": {\n" +
                "            \"days\": [\n" +
                "                \"Saturday\",\n" +
                "                \"Monday\"\n" +
                "            ],\n" +
                "            \"time\": \"14:00 - 15:30\"\n" +
                "        },\n" +
                "        \"examTime\": {\n" +
                "            \"start\": \"1402/04/01 10:00\",\n" +
                "            \"end\": \"1402/04/01 11:00\"\n" +
                "        },\n" +
                "        \"capacity\": 10,\n" +
                "        \"signedUp\": 5,\n" +
                "        \"prerequisites\": [  \n" +
                "            \"01\"\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"code\": \"05\",\n" +
                "        \"classCode\": \"01\",\n" +
                "        \"name\": \"Physics 2\",\n" +
                "        \"instructor\": \"Mr. \",\n" +
                "        \"units\": 3,\n" +
                "        \"type\": \"Fundamental\",\n" +
                "        \"classTime\": {\n" +
                "            \"days\": [\n" +
                "                \"Saturday\",\n" +
                "                \"Monday\"\n" +
                "            ],\n" +
                "            \"time\": \"16:00 - 17:30\"\n" +
                "        },\n" +
                "        \"examTime\": {\n" +
                "            \"start\": \"1402/04/01 11:00\",\n" +
                "            \"end\": \"1402/04/01 12:00\"\n" +
                "        },\n" +
                "        \"capacity\": 10,\n" +
                "        \"signedUp\": 5,\n" +
                "        \"prerequisites\": [  \n" +
                "            \"01\", \"03\"\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"code\": \"06\",\n" +
                "        \"classCode\": \"01\",\n" +
                "        \"name\": \"Chemistry\",\n" +
                "        \"instructor\": \"Mr. \",\n" +
                "        \"units\": 3,\n" +
                "        \"type\": \"Fundamental\",\n" +
                "        \"classTime\": {\n" +
                "            \"days\": [\n" +
                "                \"Sunday\",\n" +
                "                \"Tuesday\"\n" +
                "            ],\n" +
                "            \"time\": \"07:30 - 09:00\"\n" +
                "        },\n" +
                "        \"examTime\": {\n" +
                "            \"start\": \"1402/04/01 12:00\",\n" +
                "            \"end\": \"1402/04/01 13:00\"\n" +
                "        },\n" +
                "        \"capacity\": 10,\n" +
                "        \"signedUp\": 5,\n" +
                "        \"prerequisites\": [  \n" +
                "        ]\n" +
                "    }\n" +
                "]";
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Offering> courses = gson.fromJson(CoursesJsonString, new TypeToken<List<Offering>>() {
        }.getType());
        for (Offering offering : courses) {
            offering.setTime(offering.getClassTime().getTime());

            for (String prerequisite : offering.getPrerequisites()) {
                offering.addToPrerequisites(
                    new Prerequisite(offering.getCode(),
                        offering.getClassCode(),
                        prerequisite
                    )
                );
            }

            for (String day : offering.getClassTime().getDays()) {
                offering.addToDays(
                    new Day(offering.getCode(),
                        offering.getClassCode(),
                        day
                    )
                );
            }
        }

        for (Offering course : courses) {
            try {
                CourseEnrolment.getInstance().addOffering(course);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            for (Prerequisite prerequisite : course.getPrerequisitesObj()) {
                try {
                    Repository.getInstance().insertPrerequisite(prerequisite);
                } catch (Exception e) {

                }
            }
            for (Day day : course.getDays()) {
                try {
                    Repository.getInstance().insertDay(day);
                } catch (Exception e) {

                }
            }
        }
    }

    public static void importStudentsFromWeb() throws Exception {
//        String StudentsJsonString = HTTPRequestHandler.getRequest("http://138.197.181.131:5200/api/students");
        String StudentsJsonString = "[\n" +
                "    {\n" +
                "        \"id\": \"01\",\n" +
                "        \"name\": \"Ali\",\n" +
                "        \"secondName\": \"Javidan\",\n" +
                "        \"birthDate\": \"1378/01/31\",\n" +
                "        \"field\": \"Engineering Science\",\n" +
                "        \"faculty\": \"Engineering\",\n" +
                "        \"level\": \"Undergraduate\",\n" +
                "        \"status\": \"\",\n" +
                "        \"img\": \"\",\n" +
                "        \"email\": \"alijavidan@email.com\",\n" +
                "        \"password\": \"\"\n" +
                "    }\n" +
                "]";
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Student> students = gson.fromJson(StudentsJsonString, new TypeToken<List<Student>>() {
        }.getType());
        for (Student student : students) {
            try {
                student.setPassword(StringUtils.hashString(student.getPassword()));
                CourseEnrolment.getInstance().addStudent(student);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
