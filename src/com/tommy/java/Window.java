package com.tommy.java;

public class Window {
    public String getName() {
        return _name;
    }
    public void setName (String name) {
        if (!name.isBlank())
            _name = name;
    }
    private String _name = "Main Window";
    private final int[] _size = new int[]{600, 800};
    public int[] getSize() {
        return _size;
    }
    public void setSize(int height, int width) {
        _size[0] = width;
        _size[1] = height;
    }
    private int _counter = 0;
    private boolean _running = true;
    public boolean isRunning() { return _running; }

    private final Listener okButtonListener = (Object o, String msg) -> {
        if (!_running) return;
        System.out.println(msg);
        _counter++;
        System.out.println("Counter: " + _counter);
    };

    /**
     * Instead of a lambda expression, as above, the listeners could also be implemented
     * as inner classes, which implement the Listener (functional) interface by overriding
     * the onEvent method, see exitButtonListener below. This was the main idiom before Java 8.
     */
    private final Listener exitButtonListener = new Listener() {
        @Override
        public void onEvent(Object o, String msg) {
            _running = false;
            System.out.println(msg);
            System.out.println("Exiting application, goodbye...");
        }
    };

    private final Button okButton = new Button("OK");
    private final Button exitButton = new Button("EXIT");
    public Window() {
        okButton.registerListener(okButtonListener);
        exitButton.registerListener(exitButtonListener);
    }
    public void clickOk() {
        okButton.click();
    }
    public void clickExit() {
        exitButton.click();
        deregisterListeners();
    }
    public void deregisterListeners() {
        okButton.deRegisterListener(okButtonListener);
        exitButton.deRegisterListener(exitButtonListener);
    }
    
    
    /**
     * The button listeners could also be implemented by making the Window class itself implement
     * the Listener interface, and adding an overridden onEvent method, as shown below.
     * However, this is probably less readable and more error-prone.
     */
    /*
    @Override
    public void onEvent(Object o, String msg) {
        if (!running) return;
        if (o.equals(okButton)) {
           // implementation
        } else if (o.equals(exitButton)) {
           // implementation
        } else {
            throw new UnknownButtonException();
        }
    }*/
}