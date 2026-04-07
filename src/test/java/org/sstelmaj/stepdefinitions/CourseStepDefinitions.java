package org.sstelmaj.stepdefinitions;

import io.cucumber.java.es.Cuando;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.sstelmaj.tasks.CreateCourse;
import org.sstelmaj.tasks.NavigateToCourse;
import org.sstelmaj.ui.CourseDetailPage;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class CourseStepDefinitions {

    @Cuando("crea el curso {string}")
    public void creaElCurso(String courseName) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                CreateCourse.withName(courseName),
                NavigateToCourse.withName(courseName),
                WaitUntil.the(CourseDetailPage.COURSE_NAME, isVisible()).forNoMoreThan(10).seconds()
        );
    }
}
