package edu.school21.printer;

import edu.school21.renderer.Renderer;

public class PrinterWithPrefixImpl implements Printer {
    private String prefix;
    private Renderer renderer;

    public PrinterWithPrefixImpl(Renderer renderer) {
        this.renderer = renderer;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void print(String message) {
        renderer.print(this.prefix + message);
    }
}
