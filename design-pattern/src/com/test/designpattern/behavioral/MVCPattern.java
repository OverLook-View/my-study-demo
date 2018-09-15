package com.test.designpattern.behavioral;

/**
 * @description: MVC模式
 * @author: OverlookView
 * @create: 2018-09-15 23:30
 **/
public class MVCPattern {
    /**
     * MVC 模式代表 Model-View-Controller（模型-视图-控制器） 模式。这种模式用于应用程序的分层开发。
     * <p>
     * Model（模型） - 模型代表一个存取数据的对象或 JAVA POJO。它也可以带有逻辑，在数据变化时更新控制器。
     * View（视图） - 视图代表模型包含的数据的可视化。
     * Controller（控制器） - 控制器作用于模型和视图上。它控制数据流向模型对象，并在数据变化时更新视图。它使视图与模型分离开。
     */
    static class Student {
        private String rollNo;
        private String name;

        public String getRollNo() {
            return rollNo;
        }

        public void setRollNo(String rollNo) {
            this.rollNo = rollNo;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    static class StudentView {
        public void printStudentDetails(String studentName, String studentRollNo) {
            System.out.println("Student: ");
            System.out.println("Name: " + studentName);
            System.out.println("Roll No: " + studentRollNo);
        }
    }

    static class StudentController {
        private Student model;
        private StudentView view;

        public StudentController(Student model, StudentView view) {
            this.model = model;
            this.view = view;
        }

        public String getRollNo() {
            return model.rollNo;
        }

        public void setRollNo(String rollNo) {
            model.rollNo = rollNo;
        }

        public String getName() {
            return model.name;
        }

        public void setName(String name) {
            model.name = name;
        }

        public void updateView() {
            view.printStudentDetails(model.getName(), model.getRollNo());
        }

    }

    public static Student retriveStudentFromDatabse() {
        Student student = new Student();
        student.setName("Robert");
        student.setRollNo("10");
        return student;
    }

    public static void main(String[] args) {
        Student model = retriveStudentFromDatabse();
        StudentView view = new StudentView();
        StudentController controller = new StudentController(model, view);
        controller.updateView();
        controller.setName("John");
        controller.updateView();
    }
}
