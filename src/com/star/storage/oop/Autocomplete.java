package com.star.storage.oop;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Autocomplete<Data> {
    private final ArrayList<Pair<Data>> l = new ArrayList<>();

    public List<Pair<Data>> list() {
        return Collections.unmodifiableList(l);
    }

    public void add(String s, Data d) {
        l.add(new Pair<>(s, d));
    }

    public ArrayList<Pair<Data>> get(String s) {
        ArrayList<Pair<Data>> r = new ArrayList<>();
        for (var v : l) {
            if (v.name.startsWith(s))
                r.add(v);
        }
        return r;
    }

    public record Pair<Data>(String name, Data data) {

    }
}
