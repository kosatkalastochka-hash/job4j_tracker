package ru.job4j.gs;

import ru.job4j.tracker.ConsoleInput;
import ru.job4j.tracker.ConsoleOutput;
import ru.job4j.tracker.MemTracker;

public class Running {
    public static void main(String[] args) {
        CreateManyItems create = new CreateManyItems(new ConsoleOutput());
        DeleteAllItems delete = new DeleteAllItems(new ConsoleOutput());
        create.execute(new ConsoleInput(),new MemTracker());
        delete.execute(new ConsoleInput(),new MemTracker());
    }
}