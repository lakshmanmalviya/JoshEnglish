package com.example.joshenglish.Modals;

public class VocabModal {
    String vocabName,vocabImage,vocabSentence,vocabId;

    public VocabModal() {
    }

    public VocabModal(String vocabName, String vocabImage, String vocabSentence, String vocabId) {
        this.vocabName = vocabName;
        this.vocabImage = vocabImage;
        this.vocabSentence = vocabSentence;
        this.vocabId = vocabId;
    }

    public String getVocabName() {
        return vocabName;
    }

    public void setVocabName(String vocabName) {
        this.vocabName = vocabName;
    }

    public String getVocabImage() {
        return vocabImage;
    }

    public void setVocabImage(String vocabImage) {
        this.vocabImage = vocabImage;
    }

    public String getVocabSentence() {
        return vocabSentence;
    }

    public void setVocabSentence(String vocabSentence) {
        this.vocabSentence = vocabSentence;
    }

    public String getVocabId() {
        return vocabId;
    }

    public void setVocabId(String vocabId) {
        this.vocabId = vocabId;
    }
}
