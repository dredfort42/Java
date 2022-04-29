package edu.school21;

import edu.school21.printer.PrinterWithDateTimeImpl;
import edu.school21.printer.PrinterWithPrefixImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
//        PreProcessor preProcessor = new PreProcessorToUpperImpl();
//        Renderer renderer = new RendererErrImpl(preProcessor);
//        PrinterWithPrefixImpl printer = new PrinterWithPrefixImpl(renderer);
//        printer.setPrefix("Prefix ");
//        printer.print("Hello!");
//
//        PrinterWithDateTimeImpl printer1 = new PrinterWithDateTimeImpl(renderer);
//        printer1.print("TEST");
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("context.xml");

        PrinterWithPrefixImpl printer0 = applicationContext.getBean("printerUpper", PrinterWithPrefixImpl.class);
        printer0.setPrefix("Prefix ");
        printer0.print("Hello!");
        PrinterWithPrefixImpl printer1 = applicationContext.getBean("printerLower", PrinterWithPrefixImpl.class);
        printer1.setPrefix("Prefix ");
        printer1.print("Hello!");

        PrinterWithDateTimeImpl printer2 = applicationContext.getBean("printerWithDateTimeLower", PrinterWithDateTimeImpl.class);
        printer2.print("Hello!");
        PrinterWithDateTimeImpl printer3 = applicationContext.getBean("printerWithDateTimeUpper", PrinterWithDateTimeImpl.class);
        printer3.print("Hello!");
    }
}
