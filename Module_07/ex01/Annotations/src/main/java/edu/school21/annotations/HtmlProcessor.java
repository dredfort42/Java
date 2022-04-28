package edu.school21.annotations;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;
import java.util.Set;

@SupportedAnnotationTypes({"edu.school21.annotations.HtmlForm", "edu.school21.annotations.HtmlInput"})
public class HtmlProcessor extends AbstractProcessor {
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element userForm : roundEnv.getElementsAnnotatedWith(HtmlForm.class)) {
            HtmlForm htmlFormAnn = userForm.getAnnotation(HtmlForm.class);
            String line = "<form action = \"" + htmlFormAnn.action() + "\" method = \"" + htmlFormAnn.method() + "\">\n";
            List<? extends Element> userFormElements = userForm.getEnclosedElements();
            for (Element field : roundEnv.getElementsAnnotatedWith(HtmlInput.class)) {
                if (!userFormElements.contains(field)) {
                    continue;
                }
                HtmlInput htmlInputAnn = field.getAnnotation(HtmlInput.class);
                line += "\t<input type = " + htmlInputAnn.type() + "\" name = \"" +
                        htmlInputAnn.name() + "\" placeholder = \"" + htmlInputAnn.placeholder() + "\">\n";
            }
            line += "\t<input type = \"submit\" value = \"Send\">\n</form>";
            try (BufferedWriter writter = new BufferedWriter(new FileWriter("target/classes/" + htmlFormAnn.fileName()))) {
                writter.write(line);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }
}
