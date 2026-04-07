package org.sstelmaj.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.sstelmaj.interactions.WaitForMilliseconds;
import org.sstelmaj.ui.GradesTable;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class RegisterGrades implements Task {

    private final String studentName;
    private final List<Map<String, String>> grades;

    public RegisterGrades(String studentName, List<Map<String, String>> grades) {
        this.studentName = studentName;
        this.grades = grades;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(GradesTable.TABLE, isVisible()).forNoMoreThan(10).seconds()
        );

        JavascriptExecutor js = (JavascriptExecutor) BrowseTheWeb.as(actor).getDriver();

        for (Map<String, String> gradeEntry : grades) {
            String activityName = gradeEntry.get("actividad");
            String gradeValue = gradeEntry.get("nota");

            actor.attemptsTo(
                    WaitUntil.the(GradesTable.gradeCell(studentName, activityName), isVisible()).forNoMoreThan(10).seconds(),
                    Click.on(GradesTable.gradeCell(studentName, activityName)),
                    WaitUntil.the(GradesTable.gradeCellInput(studentName, activityName), isVisible()).forNoMoreThan(5).seconds()
            );

            // JavaScript injection avoids partial values from character-by-character Selenium typing in React inputs
            WebElement input = GradesTable.gradeCellInput(studentName, activityName).resolveFor(actor);
            js.executeScript(
                "var el = arguments[0]; var val = arguments[1];" +
                "var nativeInputValueSetter = Object.getOwnPropertyDescriptor(window.HTMLInputElement.prototype, 'value').set;" +
                "nativeInputValueSetter.call(el, val);" +
                "el.dispatchEvent(new Event('input', { bubbles: true }));" +
                "el.dispatchEvent(new Event('change', { bubbles: true }));",
                input, gradeValue
            );

            input.sendKeys(Keys.TAB); // blur triggers save in the app

            actor.attemptsTo(WaitForMilliseconds.of(1500));
        }
    }

    public static RegisterGrades forStudent(String studentName, List<Map<String, String>> grades) {
        return Tasks.instrumented(RegisterGrades.class, studentName, grades);
    }
}
