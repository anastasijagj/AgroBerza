package finki.ukim.mk.agroberza.model;

import finki.ukim.mk.agroberza.model.enums.UserCategory;



public class RegistrationRequest {
    private String username;
    private String name;
    private String surname;
    private String password;
    private UserCategory userCategory;

    public RegistrationRequest() {
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPassword() {
        return password;
    }

    public UserCategory getUserCategory() {
        return userCategory;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserCategory(UserCategory userCategory) {
        this.userCategory = userCategory;
    }
}
