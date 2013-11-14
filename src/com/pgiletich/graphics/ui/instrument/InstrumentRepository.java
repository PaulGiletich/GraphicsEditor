package com.pgiletich.graphics.ui.instrument;

import com.pgiletich.graphics.ui.MainWindow;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class InstrumentRepository implements Iterable<Map.Entry <String, InstrumentStrategy>> {
    private Map<String, InstrumentStrategy> map = new LinkedHashMap<>();

    public InstrumentRepository(MainWindow window) {
        map.put("CDA", new CDALineInstrument(window));
        map.put("Bresenham", new BresenhamLineInstrument(window));
        map.put("Antialiased", new AntialiasedLineInstrument(window));
        map.put("Hand", new HandInstrument(window));
    }

    public InstrumentStrategy get(String name) {
        return map.get(name);
    }

    @Override
    public Iterator<Map.Entry<String, InstrumentStrategy>> iterator() {
        return map.entrySet().iterator();
    }

    public Set<String> keySet() {
        return map.keySet();
    }
}
