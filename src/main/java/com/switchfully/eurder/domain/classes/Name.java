package com.switchfully.eurder.domain.classes;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@Embeddable
public class Name {
    @Column(name = "firstname")
  //  @Size(min = 1, message = "firstname is a mandatory field")
    private String first;
    @Column(name = "lastname")
  //  @Size(min = 1, message = "lastname is a mandatory field")
    private String last;

    public Name() {
    }

    public Name(String first, String last) {
        this.first = first;
        this.last = last;
    }

    public String getFirst() {
        return first;
    }

    public String getLast() {
        return last;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Name) obj;
        return Objects.equals(this.first, that.first) &&
                Objects.equals(this.last, that.last);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, last);
    }

    @Override
    public String toString() {
        return "Name[" +
                "first=" + first + ", " +
                "last=" + last + ']';
    }

}
