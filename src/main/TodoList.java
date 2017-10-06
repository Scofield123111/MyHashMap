package main;

import java.util.Iterator;
import java.util.PriorityQueue;

public class TodoList extends PriorityQueue<TodoList.TodoItem> {

    public static void main(String[] args) {
        TodoList todoList = new TodoList();
        todoList.add("AS", 'A', 1);
        todoList.add("SS", 'C', 1);
        todoList.add("FS", 'B', 1);
        todoList.add("GS", 'B', 2);
        todoList.add("HS", 'B', 3);
        todoList.add("QS", 'A', 2);

        System.out.println(todoList);

        Iterator it = todoList.iterator();
        while (it.hasNext()){
            System.out.println(it.next());
        }
        System.out.println("\r\n");

        while (!todoList.isEmpty()) {
            System.out.println(todoList.poll());
        }
    }

    public void add(String s, char c, int i) {
        super.add(new TodoItem(c, i, s));
    }

    static class TodoItem implements Comparable<TodoItem> {
        String s;
        private char primaryPriority;
        private int secondPriority;

        public TodoItem(char primaryPriority, int secondPriority, String s) {
            this.primaryPriority = primaryPriority;
            this.secondPriority = secondPriority;
            this.s = s;
        }

        @Override
        public int compareTo(TodoItem o) {
            int result = Character.compare(primaryPriority, o.primaryPriority);
            if (result != 0) {
                return result;
            }
            return Integer.compare(secondPriority, o.secondPriority);
        }

        @Override
        public String toString() {
            return Character.toString(primaryPriority) + secondPriority + ": " + s;
        }
    }
}
