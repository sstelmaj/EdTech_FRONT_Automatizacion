package org.sstelmaj.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.sstelmaj.ui.CreateCourseModal;
import org.sstelmaj.ui.DashboardPage;
import org.sstelmaj.interactions.WaitForMilliseconds;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class CreateCourse implements Task {

    private final String courseName;

    public CreateCourse(String courseName) {
        this.courseName = courseName;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(DashboardPage.CREATE_COURSE_BUTTON, isVisible()).forNoMoreThan(10).seconds(),
                Click.on(DashboardPage.CREATE_COURSE_BUTTON),
                WaitUntil.the(CreateCourseModal.COURSE_NAME_FIELD, isVisible()).forNoMoreThan(10).seconds(),
                Enter.theValue(courseName).into(CreateCourseModal.COURSE_NAME_FIELD),
                Click.on(CreateCourseModal.SAVE_BUTTON),
                WaitUntil.the(CreateCourseModal.SUCCESS_ALERT, isVisible()).forNoMoreThan(10).seconds(),
                WaitForMilliseconds.of(1500)
        );
    }

    public static CreateCourse withName(String courseName) {
        return Tasks.instrumented(CreateCourse.class, courseName);
    }
}
