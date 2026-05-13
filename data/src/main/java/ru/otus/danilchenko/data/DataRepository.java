package ru.otus.danilchenko.data;

import ru.otus.danilchenko.lib.LibUtil;

public class DataRepository {
    public String fetchData() {
        return "Data from repository. Lib says: " + LibUtil.hello();
    }
}
