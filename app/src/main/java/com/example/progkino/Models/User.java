package com.example.progkino.Models;

public class User {
    private String id, name,lastname, login, password, email, userdescritpion, birthday, city, role;
    public User() {}

    public User(String id, String name,String lastname,String  login,String  password,String  email,
                String  birthday,String  city, String userdescritpion) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.login = login;
        this.password = password;
        this.email = email;
        this.birthday = birthday;
        this.city = city;
        this.userdescritpion = userdescritpion;
        this.role = role;
    }
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name){this.name = name;}
    public String getName(){return name;}

    public void setLastName(String lastname){this.lastname = lastname;}
    public String getLastName(){return lastname;}

    public void setLogin(String login){this.login = login;}
    public String getLogin(){return login;}

    public void setPassword(String password){this.password = password;}
    public String getPassword(){return password;}

    public void setEmail(String email){this.email = email;}
    public String getEmail(){return email;}

    public void setBirthday(String birthday){this.birthday = birthday;}
    public String getBirthday(){return birthday;}

    public void setCity(String city){this.city = city;}
    public String getCity(){return city;}
    public void setUserdescritpion(String userdescritpion){this.userdescritpion = userdescritpion;}
    public String getUserdescritpion(){return userdescritpion;}
}
