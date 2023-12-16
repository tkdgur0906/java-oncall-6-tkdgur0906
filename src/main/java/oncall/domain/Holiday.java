package oncall.domain;

public enum Holiday {
    NEW_YEAR(1, 1, "신정"),
    INDEPENDENCE_MOVEMENT_DAY(3, 1, "삼일절"),
    CHILDRENS_DAY(5, 5, "어린이날"),
    MEMORIAL_DAY(6, 6, "현충일"),
    LIBERATION_DAY(8, 15, "광복절"),
    FOUNDATION_DAY(10, 3, "개천절"),
    HANGUL_DAY(10, 9, "한글날"),
    CHRISTMAS(12, 25, "성탄절");

    private final int month;
    private final int day;
    private final String name;

    Holiday(int month, int day, String name) {
        this.month = month;
        this.day = day;
        this.name = name;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public String getName() {
        return name;
    }

}

