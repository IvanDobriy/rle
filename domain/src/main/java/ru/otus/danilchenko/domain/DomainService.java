package ru.otus.danilchenko.domain;

import ru.otus.danilchenko.lib.LibUtil;

public class DomainService {
    public String getMessage() {
        return "Hello from domain. Lib says: " + LibUtil.hello();
    }
}
