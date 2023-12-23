package com.example.joshenglish.Modals;

public class ReadingModal {
    String readNote,storyName,storyContent,storyId;

    public ReadingModal() {
    }

    public ReadingModal(String readNote, String storyName, String storyContent, String storyId) {
        this.readNote = readNote;
        this.storyName = storyName;
        this.storyContent = storyContent;
        this.storyId = storyId;
    }

    public String getReadNote() {
        return readNote;
    }

    public void setReadNote(String readNote) {
        this.readNote = readNote;
    }

    public String getStoryName() {
        return storyName;
    }

    public void setStoryName(String storyName) {
        this.storyName = storyName;
    }

    public String getStoryContent() {
        return storyContent;
    }

    public void setStoryContent(String storyContent) {
        this.storyContent = storyContent;
    }

    public String getStoryId() {
        return storyId;
    }

    public void setStoryId(String storyId) {
        this.storyId = storyId;
    }
}
