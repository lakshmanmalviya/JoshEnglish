package com.example.joshenglish.Modals;

public class LessonMadal {
    String lessonName,lessonId,lessonImg;

    public LessonMadal() {
    }

    public LessonMadal(String lessonName, String lessonId, String lessonImg) {
        this.lessonName = lessonName;
        this.lessonId = lessonId;
        this.lessonImg = lessonImg;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public String getLessonId() {
        return lessonId;
    }

    public void setLessonId(String lessonId) {
        this.lessonId = lessonId;
    }

    public String getLessonImg() {
        return lessonImg;
    }

    public void setLessonImg(String lessonImg) {
        this.lessonImg = lessonImg;
    }
}
