package ru.job4j.action;

import ru.job4j.tracker.*;

public class Replace implements UserAction {
    private final Output out;

    public Replace(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Изменить заявку";
    }

    @Override
    public boolean execute(Input input, Store store) {
        out.println("=== Редактирование заявки ===");
        int id = input.askInt("Введите id: ");
        String name = input.askStr("Введите имя: ");
        Item item = new Item(name);
        if (store.replace(id, item)) {
            out.println("Заявка успешно изменена.");
        } else {
            out.println("Ошибка замены заявки.");
        }
        return true;
    }
}
