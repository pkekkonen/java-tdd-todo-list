package com.booleanuk.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
        Assertions.assertTrue(todoList.addTask("clean"));
    }

    @Test
    public void addAlreadyExistingTaskTest() {
        TodoList todoList = new TodoList();
        todoList.addTask("clean");
        Assertions.assertFalse(todoList.addTask("clean"));
    }

    @Test
    public void seeListTest() {
        TodoList todoList = new TodoList();
        todoList.addTask("clean");
        todoList.addTask("bake");
        todoList.addTask("book dentist appointment");
        String expected = "clean/nbake/nbook dentist appointment";
        Assertions.assertEquals(expected, todoList.seeList());
    }
}
