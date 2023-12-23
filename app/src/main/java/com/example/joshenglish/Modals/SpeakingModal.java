package com.example.joshenglish.Modals;

public class SpeakingModal {
    String topicName,rating,speakId;

    public SpeakingModal() {
    }

    public SpeakingModal(String topicName, String rating, String speakId) {
        this.topicName = topicName;
        this.rating = rating;
        this.speakId = speakId;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getSpeakId() {
        return speakId;
    }

    public void setSpeakId(String speakId) {
        this.speakId = speakId;
    }
}
