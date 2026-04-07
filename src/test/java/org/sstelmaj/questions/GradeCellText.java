package org.sstelmaj.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import org.sstelmaj.ui.GradesTable;

public class GradeCellText implements Question<String> {

    private final String studentName;
    private final String activityName;

    public GradeCellText(String studentName, String activityName) {
        this.studentName = studentName;
        this.activityName = activityName;
    }

    @Override
    public String answeredBy(Actor actor) {
        return GradesTable.gradeCellDiv(studentName, activityName).resolveFor(actor).getText().trim();
    }

    public static GradeCellText forStudentAndActivity(String studentName, String activityName) {
        return new GradeCellText(studentName, activityName);
    }
}
