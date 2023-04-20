package cz.osu.project_todoholecekp_hrtonm.Model;

public enum SortingType {
    CATEGORY_TITLE_ASC (1), CATEGORY_TITLE_DESC(2), CATEGORY_TASKS_COUNT_ASC(3), CATEGORY_TASKS_COUNT_DESC(4),
    TASK_TITLE_ASC(5), TASK_TITLE_DESC(6), TASK_DEADLINE_ASC(7), TASK_DEADLINE_DESC(8),
    TASK_COMPLETED_ASC(9), TASK_COMPLETED_DESC(10);

    public final int value;

    SortingType(int value) {
        this.value = value;
    }


}
