package com.tommy.java;

import java.util.*;

public class Button implements Broadcaster {
    private final List<Listener> _listeners = new ArrayList<>();
    private final String _name;
    
    public Button(String name) { this._name = name; }
    public void registerListener(Listener o) {
        _listeners.add(o);
        System.out.println("Registered " + o + " to " + _name);
    }
    public void deRegisterListener(Listener o) {
        for (var i = 0; i < _listeners.size(); ++i) {
            if (o == _listeners.get(i)) {
                _listeners.remove(_listeners.get(i));
                System.out.println("Deregistered " + o + " from " + _name);
            }
        }
    }
    @Override
    public void notify(Object o, String msg) {
        for (var obs : _listeners) {
            obs.onEvent(o, msg);
        }
    }
    public void click() {
        notify(this, String.format("Button clicked: %s", _name));
    }
}