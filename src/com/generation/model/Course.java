package com.generation.model;

public class Course
{
    private final String code;
    private final String name;
    private final int credits;
    private final Module module;

    public Course(String code, String name, int credits, Module module)
    {
        this.code = code;
        this.name = name;
        this.credits = credits;
        this.module = module;
    }

    public String getCode()
    {
        return code;
    }

    public String getName()
    {
        return name;
    }

    public int getCredits()
    {
        return credits;
    }

    public Module getModule()
    {
        return module;
    }

    @Override
    public String toString()
    {
        return "Course{" + "code='" + code + '\'' + ", name='" + name + '\'' + ", credits=" + credits + ", module="
                + module + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course)) return false;
        Course course = (Course) o;
        return code != null ? code.equals(course.code) : course.code == null;
    }

    @Override
    public int hashCode() {
        return code != null ? code.hashCode() : 0;
    }
}
