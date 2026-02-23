package ru.job4j.tracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MemTrackerTest {

    private Store store;

    @BeforeEach
    public void init() {
        store = new MemTracker();
    }

    @Test
    void whenAddNewItemThenTrackerHasSameItem() {
        Item item = new Item();
        item.setName("test1");
        store.add(item);
        Item result = store.findById(item.getId());
        assertThat(result.getName()).isEqualTo(item.getName());
    }

    @Test
    void whenTestFindById() {
        Item bug = new Item("Bug");
        Item item = store.add(bug);
        Item result = store.findById(item.getId());
        assertThat(result.getName()).isEqualTo(item.getName());
    }

    @Test
    void whenTestFindAll() {
        Item first = new Item("First");
        Item second = new Item("Second");
        store.add(first);
        store.add(second);
        Item result = store.findAll().get(0);
        assertThat(result.getName()).isEqualTo(first.getName());
    }

    @Test
    void whenTestFindByNameCheckArrayLength() {
        Item first = new Item("First");
        Item second = new Item("Second");
        store.add(first);
        store.add(second);
        store.add(new Item("First"));
        store.add(new Item("Second"));
        store.add(new Item("First"));
        List<Item> result = store.findByName(first.getName());
        assertThat(result).hasSize(3);
    }

    @Test
    void whenReplaceItemIsSuccessful() {
        Item item = new Item("Bug");
        store.add(item);
        int id = item.getId();
        Item updateItem = new Item("Bug with description");
        store.replace(id, updateItem);
        assertThat(store.findById(id).getName()).isEqualTo("Bug with description");
    }

    @Test
    void whenReplaceItemIsNotSuccessful() {
        Item item = new Item("Bug");
        store.add(item);
        Item updateItem = new Item("Bug with description");
        boolean result = store.replace(1000, updateItem);
        assertThat(store.findById(item.getId()).getName()).isEqualTo("Bug");
        assertThat(result).isFalse();
    }

    @Test
    void whenDeleteItemIsSuccessful() {
        Item item = new Item("Bug");
        store.add(item);
        int id = item.getId();
        store.delete(id);
        assertThat(store.findById(id)).isNull();
    }

    @Test
    void whenDeleteItemIsNotSuccessful() {
        Item item = new Item("Bug");
        store.add(item);
        store.delete(1000);
        assertThat(store.findById(item.getId()).getName()).isEqualTo("Bug");
    }
}