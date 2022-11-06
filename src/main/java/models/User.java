package models;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class User {
    private int id;
    private String email;
    private String username;
    private String password;
    private String gender;
    private String country;
    private String sport;
    private String category;
    private int exp;
    private String aClass;
    private int countOfGroups;
    private String status;
}
