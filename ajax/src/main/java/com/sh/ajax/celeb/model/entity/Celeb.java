package com.sh.ajax.celeb.model.entity;

public class Celeb {
    private Long id;
    private String name;
    private String profile;
    private Type type;

    public Celeb() {
    }

    public Celeb(Long id, String name, String profile, Type type) {
        this.id = id;
        this.name = name;
        this.profile = profile;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfile() {
        if(profile == null){
            return "default.png";
        }
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Celeb{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", profile='" + profile + '\'' +
                ", type=" + type +
                '}';
    }
}
