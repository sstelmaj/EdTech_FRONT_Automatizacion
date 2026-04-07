package org.sstelmaj.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.sstelmaj.interactions.WaitForMilliseconds;
import org.sstelmaj.ui.AddStudentModal;
import org.sstelmaj.ui.CourseDetailPage;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class AddStudent implements Task {

    private final String studentId;
    private final String name;
    private final String email;

    public AddStudent(String studentId, String name, String email) {
        this.studentId = studentId;
        this.name = name;
        this.email = email;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(CourseDetailPage.ADD_STUDENT_BUTTON, isVisible()).forNoMoreThan(10).seconds(),
                Click.on(CourseDetailPage.ADD_STUDENT_BUTTON),
                WaitUntil.the(AddStudentModal.STUDENT_ID_FIELD, isVisible()).forNoMoreThan(10).seconds(),
                Enter.theValue(studentId).into(AddStudentModal.STUDENT_ID_FIELD),
                Click.on(AddStudentModal.SEARCH_BUTTON),
                WaitForMilliseconds.of(2000)
        );

        boolean studentFound = AddStudentModal.FOUND_ALERT.resolveFor(actor).isCurrentlyVisible();
        if (!studentFound) {
            actor.attemptsTo(
                    Enter.theValue(name).into(AddStudentModal.NAME_FIELD).thenHit(org.openqa.selenium.Keys.TAB),
                    Enter.theValue(email).into(AddStudentModal.EMAIL_FIELD).thenHit(org.openqa.selenium.Keys.TAB)
            );
        }

        actor.attemptsTo(
                Click.on(AddStudentModal.ADD_BUTTON),
                WaitUntil.the(AddStudentModal.SUCCESS_ALERT, isVisible()).forNoMoreThan(10).seconds(),
                WaitForMilliseconds.of(1500)
        );
    }

    public static AddStudent withDetails(String studentId, String name, String email) {
        return Tasks.instrumented(AddStudent.class, studentId, name, email);
    }
}
