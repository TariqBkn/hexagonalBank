package com.hexa.domain.usecase;

public interface UseCase<C, R> {
    R process(C command);
}
