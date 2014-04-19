package net.claflan.CheDul;

public class Main {
    
    public static void main(String[] args) {
        EventHandler eH = new EventHandler();
        InterfaceHandler iH = new InterfaceHandler(eH);
        iH.show("MAIN_WINDOW");
    }
}
