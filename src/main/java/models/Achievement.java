package models;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Achievement {
    private int id;
    private String name;
    private int requiredValue;
    private int currentValue;
    private String description;
    private String status;
}
