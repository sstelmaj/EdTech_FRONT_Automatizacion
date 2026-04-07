package org.sstelmaj.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Clear;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.sstelmaj.interactions.WaitForMilliseconds;
import org.sstelmaj.ui.CourseDetailPage;
import org.sstelmaj.ui.EvaluationProgramModal;

import java.util.List;
import java.util.Map;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class EditEvaluationProgram implements Task {

    private final List<Map<String, String>> activities;
    private final String activityToRemove;

    public EditEvaluationProgram(List<Map<String, String>> activities, String activityToRemove) {
        this.activities = activities;
        this.activityToRemove = activityToRemove;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(CourseDetailPage.DEFINE_PROGRAM_BUTTON, isVisible()).forNoMoreThan(10).seconds(),
                Click.on(CourseDetailPage.DEFINE_PROGRAM_BUTTON),
                WaitUntil.the(EvaluationProgramModal.TITLE, isVisible()).forNoMoreThan(10).seconds()
        );

        if (activityToRemove != null && !activityToRemove.isEmpty()) {
            actor.attemptsTo(
                    Click.on(EvaluationProgramModal.deleteActivityButtonByName(activityToRemove)),
                    WaitForMilliseconds.of(500)
            );
        }

        for (int i = 0; i < activities.size(); i++) {
            int index = i + 1;
            Map<String, String> activity = activities.get(i);

            actor.attemptsTo(
                    Clear.field(EvaluationProgramModal.activityNameFieldByIndex(index)),
                    Enter.theValue(activity.get("nombre")).into(EvaluationProgramModal.activityNameFieldByIndex(index)),
                    Clear.field(EvaluationProgramModal.activityPercentageFieldByIndex(index)),
                    Enter.theValue(activity.get("porcentaje")).into(EvaluationProgramModal.activityPercentageFieldByIndex(index))
            );
        }

        actor.attemptsTo(
                Click.on(EvaluationProgramModal.SAVE_BUTTON),
                WaitUntil.the(EvaluationProgramModal.SUCCESS_ALERT, isVisible()).forNoMoreThan(10).seconds(),
                WaitForMilliseconds.of(1500)
        );
    }

    public static EditEvaluationProgram withActivities(List<Map<String, String>> activities) {
        return Tasks.instrumented(EditEvaluationProgram.class, activities, null);
    }

    public static EditEvaluationProgram removingActivityAndUpdating(String activityToRemove, List<Map<String, String>> activities) {
        return Tasks.instrumented(EditEvaluationProgram.class, activities, activityToRemove);
    }
}
