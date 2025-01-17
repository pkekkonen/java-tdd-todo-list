package com.booleanuk.extension;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

class TodoListTest {
    @Test
    public void exampleTest() {
        String hello = "Hello";
        Assertions.assertEquals("Hello", hello);
        Assertions.assertNotEquals("Goodbye", hello);
    }

    @Test
    public void addNewTaskTest() {
        TodoList todoList = new TodoList();
        Assertions.assertTrue(todoList.addTask("clean", "123"));
    }

    @Test
    public void addAlreadyExistingTaskTest() {
        TodoList todoList = new TodoList();
        todoList.addTask("call bob", "123");
        Assertions.assertFalse(todoList.addTask("clean", "123"));
    }

    @Test
    public void seeListTest() {
        TodoList todoList = new TodoList();
        todoList.addTask("book dentist appointment", "123");
        todoList.addTask("clean", "456");
        todoList.addTask("bake", "789");
        String list = todoList.seeList();
        Assertions.assertTrue(list.contains("clean") && list.contains("bake") && list.contains("book dentist appointment"));
    }

    @Test
    public void updateExistingTaskStatusTest() {
        TodoList todoList = new TodoList();
        todoList.addTask("clean", "123");
        Assertions.assertFalse(todoList.tasks.get("123").status);
        Assertions.assertTrue(todoList.updateTaskStatus("123", true));
        Assertions.assertTrue(todoList.tasks.get("123").status);
    }

    @Test
    public void updateNonExistingTaskStatusTest() {
        TodoList todoList = new TodoList();
        todoList.addTask("clean", "123");
        Assertions.assertFalse(todoList.updateTaskStatus("456", true));
    }

    @Test
    public void getCompletedTasksEmptyList() {
        TodoList todoList = new TodoList();
        Assertions.assertEquals(new ArrayList<>(), todoList.getCompletedTasks());
    }

    @Test
    public void getCompletedTasksNotEmptyList() {
        TodoList todoList = new TodoList();
        todoList.addTask("clean", "123");
        todoList.addTask("book dentist", "456");
        todoList.updateTaskStatus("123", true);
        Assertions.assertEquals(new ArrayList<>(Arrays.asList("clean")), todoList.getCompletedTasks());
    }

    @Test
    public void getUncompletedTasksEmptyList() {
        TodoList todoList = new TodoList();
        Assertions.assertEquals(new ArrayList<>(), todoList.getUncompletedTasks());
    }

    @Test
    public void getUncompletedTasksNotEmptyList() {
        TodoList todoList = new TodoList();
        todoList.addTask("clean", "123");
        todoList.addTask("book dentist", "456");
        todoList.updateTaskStatus("123", true);
        Assertions.assertEquals(new ArrayList<>(Arrays.asList("book dentist")), todoList.getUncompletedTasks());
    }

    @Test
    public void doesExistingTaskExist() {
        TodoList todoList = new TodoList();
        todoList.addTask("clean", "123");
        Assertions.assertEquals(	"The task exists!", todoList.doesTaskExist("123"));
    }

    @Test
    public void doesNonexistingTaskExist() {
        TodoList todoList = new TodoList();
        todoList.addTask("clean", "123");
        Assertions.assertEquals(	"The task doesn't exist!", todoList.doesTaskExist("456"));
    }

    @Test
    public void removeExistingTask() {
        TodoList todoList = new TodoList();
        todoList.addTask("clean", "123");
        Assertions.assertTrue(todoList.remove("123"));
    }

    @Test
    public void removeNonexistingTask() {
        TodoList todoList = new TodoList();
        todoList.addTask("clean", "123");
        Assertions.assertFalse(todoList.remove("456"));
    }

    @Test
    public void seeListInAlphabeticalAscendingOrder() {
        TodoList todoList = new TodoList();
        todoList.addTask("clean", "123");
        todoList.addTask("bake", "456");
        todoList.addTask("book bowling", "789");
        ArrayList<String> expected = new ArrayList<>(Arrays.asList("bake", "book bowling", "clean"));
        Assertions.assertEquals(expected, todoList.getListInAlphabeticalOrder(true));
    }

    @Test
    public void seeListInAlphabeticalDescendingOrder() {
        TodoList todoList = new TodoList();
        todoList.addTask("clean", "123");
        todoList.addTask("bake", "456");
        todoList.addTask("book bowling", "789");
        ArrayList<String> expected = new ArrayList<>(Arrays.asList("clean", "book bowling", "bake"));
        Assertions.assertEquals(expected, todoList.getListInAlphabeticalOrder(false));
    }

    @Test
    public void updateNameOfExistingTaskTest() {
        TodoList todoList = new TodoList();
        todoList.addTask("clean", "123");
        Assertions.assertTrue(todoList.updateNameOfExistingTask("123", "clean bedroom"));
        Assertions.assertEquals("clean bedroom", todoList.tasks.get("123").name);
    }

    @Test
    public void updateNameOfNonexistingTaskTest() {
        TodoList todoList = new TodoList();
        todoList.addTask("clean", "123");
        Assertions.assertFalse(todoList.updateNameOfExistingTask("122", "clean bedroom"));
    }

    @Test
    public void getTimeCreatedTest() {
        TodoList todoList = new TodoList();
        todoList.addTask("clean", "123");
        String time = new SimpleDateFormat("H:mm:ss").format(new Date());
        Assertions.assertEquals(time, todoList.getTimeCreated("123"));
    }

    @Test
    public void getDateCreatedTest() {
        TodoList todoList = new TodoList();
        todoList.addTask("clean", "123");
        String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        Assertions.assertEquals(date, todoList.getDateCreated("123"));
    }
}
