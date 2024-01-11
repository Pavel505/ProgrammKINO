package com.example.progkino.Models;

public class User {
    private String name,lastname, login, password, email, userdescritpion, birthday, city;
    public User() {}

    public User(String name,String lastname,String  login,String  password,String  email,
                String userdescritpion,String  birthday,String  city) {
        this.name = name;
        this.lastname = lastname;
        this.login = login;
        this.password = password;
        this.email = email;
        this.userdescritpion = userdescritpion;
        this.birthday = birthday;
        this.city = city;
    }

    public void setName(String name){this.name = name;}
    public String getName(){return name;}

    public void setLastname(String lastname){this.lastname = lastname;}
    public String getLastnameName(){return lastname;}

    public void setLogin(String login){this.login = login;}
    public String getLogin(){return login;}

    public void setPassword(String password){this.password = password;}
    public String getPassword(){return password;}

    public void setEmail(String email){this.email = email;}
    public String getEmail(){return email;}

    public void setUserdescritpion(String userdescritpion){this.userdescritpion = userdescritpion;}
    public String getUserdescritpion(){return userdescritpion;}

    public void setBirthday(String birthday){this.birthday = birthday;}
    public String getBirthday(){return birthday;}

    public void setCity(String city){this.city = city;}
    public String getCity(){return city;}


}
