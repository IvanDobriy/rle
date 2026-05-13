package ru.otus.danilchenko.domain;

import ru.otus.danilchenko.lib.api.LibUtil;

public class DomainService {
    public String getMessage() {
        return "Hello from domain. Lib says: " + LibUtil.hello();
    }
}
