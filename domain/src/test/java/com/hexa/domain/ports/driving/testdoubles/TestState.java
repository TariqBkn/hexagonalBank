package com.hexa.domain.ports.driving.testdoubles;

import java.util.List;

public interface TestState<T> {
    void init(T... elements);
    void reset();
    List<T> getCurrentState();
}
