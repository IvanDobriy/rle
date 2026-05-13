package ru.otus.danilchenko.cli;

import ru.otus.danilchenko.domain.DomainService;
import ru.otus.danilchenko.data.DataRepository;

public class CliApp {
    public static void main(String[] args) {
        DomainService domainService = new DomainService();
        DataRepository dataRepository = new DataRepository();

        System.out.println("CLI module started");
        System.out.println(domainService.getMessage());
        System.out.println(dataRepository.fetchData());
    }
}
