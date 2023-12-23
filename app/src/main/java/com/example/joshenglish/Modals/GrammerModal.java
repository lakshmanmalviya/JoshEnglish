package com.example.joshenglish.Modals;

public class GrammerModal {
    String video,videoName,videoNotes,grammerId;

    public GrammerModal() {
    }

    public GrammerModal(String video, String videoName, String videoNotes, String grammerId) {
        this.video = video;
        this.videoName = videoName;
        this.videoNotes = videoNotes;
        this.grammerId = grammerId;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getVideoNotes() {
        return videoNotes;
    }

    public void setVideoNotes(String videoNotes) {
        this.videoNotes = videoNotes;
    }

    public String getGrammerId() {
        return grammerId;
    }

    public void setGrammerId(String grammerId) {
        this.grammerId = grammerId;
    }
}
