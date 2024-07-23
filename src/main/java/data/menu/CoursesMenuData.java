package data.menu;

public enum CoursesMenuData {

    PROGRAMMING("Программирование"),
    TESTING("Тестирование");

    private String name; //добавить поле стринг, которое будет содержать эти имена

    CoursesMenuData(String name) { //добавить конструктор
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
