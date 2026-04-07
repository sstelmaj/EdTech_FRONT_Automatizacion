package org.sstelmaj.stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Y;
import net.serenitybdd.screenplay.actors.OnStage;
import org.sstelmaj.tasks.DefineEvaluationProgram;
import org.sstelmaj.tasks.EditEvaluationProgram;

import java.util.List;
import java.util.Map;

public class EvaluationProgramStepDefinitions {

    @Y("define el programa evaluativo con las actividades:")
    public void defineElProgramaEvaluativoConLasActividades(DataTable dataTable) {
        List<Map<String, String>> activities = dataTable.asMaps(String.class, String.class);
        OnStage.theActorInTheSpotlight().attemptsTo(
                DefineEvaluationProgram.withActivities(activities)
        );
    }

    @Cuando("edita el programa evaluativo con las actividades:")
    public void editaElProgramaEvaluativoConLasActividades(DataTable dataTable) {
        List<Map<String, String>> activities = dataTable.asMaps(String.class, String.class);
        OnStage.theActorInTheSpotlight().attemptsTo(
                EditEvaluationProgram.withActivities(activities)
        );
    }

    @Cuando("edita el programa evaluativo eliminando {string} y redistribuyendo:")
    public void editaElProgramaEvaluativoEliminandoYRedistribuyendo(String activityToRemove, DataTable dataTable) {
        List<Map<String, String>> activities = dataTable.asMaps(String.class, String.class);
        OnStage.theActorInTheSpotlight().attemptsTo(
                EditEvaluationProgram.removingActivityAndUpdating(activityToRemove, activities)
        );
    }
}
