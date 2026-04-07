package org.sstelmaj.stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.sstelmaj.questions.GradeCellText;
import org.sstelmaj.questions.WeightedAverageOf;
import org.sstelmaj.tasks.RegisterGrades;
import org.sstelmaj.interactions.WaitForMilliseconds;

import java.util.List;
import java.util.Map;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static org.hamcrest.Matchers.equalTo;
import org.sstelmaj.ui.GradesTable;

public class GradesStepDefinitions {

    @Cuando("registra las siguientes notas para el estudiante {string}:")
    public void registraLasSiguientesNotasParaElEstudiante(String studentName, DataTable dataTable) {
        List<Map<String, String>> grades = dataTable.asMaps(String.class, String.class);
        OnStage.theActorInTheSpotlight().attemptsTo(
                WaitUntil.the(GradesTable.TABLE, isVisible()).forNoMoreThan(10).seconds(),
                RegisterGrades.forStudent(studentName, grades)
        );
    }

    @Entonces("el promedio ponderado del estudiante {string} debe ser {string}")
    public void elPromedioPonderadoDelEstudianteDebeSer(String studentName, String expectedAverage) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                WaitForMilliseconds.of(2000)
        );
        OnStage.theActorInTheSpotlight().should(
                seeThat(WeightedAverageOf.student(studentName), equalTo(expectedAverage))
        );
    }

    @Entonces("la celda de la actividad {string} del estudiante {string} muestra {string}")
    public void laCeldaDeLaActividadDelEstudianteMuestra(String activityName, String studentName, String expectedText) {
        OnStage.theActorInTheSpotlight().should(
                seeThat(GradeCellText.forStudentAndActivity(studentName, activityName), equalTo(expectedText))
        );
    }
}
