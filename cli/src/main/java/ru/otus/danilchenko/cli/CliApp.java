package ru.otus.danilchenko.cli;

import ru.otus.danilchenko.domain.DomainService;
import ru.otus.danilchenko.data.DataRepository;
import ru.otus.danilchenko.lib.v1.LibV1;

public class CliApp {
    public static void main(String[] args) {
        DomainService domainService = new DomainService();
        DataRepository dataRepository = new DataRepository();

        System.out.println("CLI module started");
        System.out.println(domainService.getMessage());
        System.out.println(dataRepository.fetchData());
        System.out.println(LibV1.helloV1());
    }
}
