package net.dante;

/*Main-klassen för projektet. Den här klassen gör inte mycket i sig självt, utan är endast kopplad
till andra klasser som hanterar programmets logik */

public class Main {
    void main() {

        StartMenu Menu = new StartMenu();

        Menu.FetchAll();

        while (true) {
            Menu.ShowMenu();
        }
    }
}