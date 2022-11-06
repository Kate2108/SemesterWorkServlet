package models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Schedule {
    private int hourStart;
    private int minuteStart;
    private int hourEnd;
    private int minuteEnd;
    private String group;
    private String day;
}
