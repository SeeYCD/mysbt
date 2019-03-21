package com.crh.entity;

public class User {
    @Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", age=" + age
				+ ", email=" + email + "]";
	}

	private Integer id;

    private String name;

    private Integer age;

    private String email;
    public User (){    	 
    }
    public User (String name,Integer age,String email){
    	this.name=name;
    	this.age=age;
    	this.email=email;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }
}