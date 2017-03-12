package com.college.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;

/**
 * Created by G on 2017/3/10.
 */
@Entity(name = "score")
@IdClass(SettleItemId.class)
public class Score implements Serializable{
    @Id
    @Column(name = "courseid")
    private int courseid;
    @Id
    @Column(name = "studentid")
    private int studentid;
    @Column(name = "score")
    private int score;

    public Score() {
    }

    public int getCourseid() {
        return courseid;
    }

    public void setCourseid(int courseid) {
        this.courseid = courseid;
    }

    public int getStudentid() {
        return studentid;
    }

    public void setStudentid(int studentid) {
        this.studentid = studentid;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
