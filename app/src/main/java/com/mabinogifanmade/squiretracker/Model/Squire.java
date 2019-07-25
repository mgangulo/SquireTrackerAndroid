package com.mabinogifanmade.squiretracker.Model;

public class Squire {
    private String name;
    private boolean hasHints;
    private String sequenceConvo;
    private String sequenceHint;
    private int imageSquire;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isHasHints() {
        return hasHints;
    }

    public void setHasHints(boolean hasHints) {
        this.hasHints = hasHints;
    }

    public String getSequenceConvo() {
        return sequenceConvo;
    }

    public void setSequenceConvo(String sequenceConvo) {
        this.sequenceConvo = sequenceConvo;
    }

    public String getSequenceHint() {
        return sequenceHint;
    }

    public void setSequenceHint(String sequenceHint) {
        this.sequenceHint = sequenceHint;
    }

    public int getImageSquire() {
        return imageSquire;
    }

    public void setImageSquire(int imageSquire) {
        this.imageSquire = imageSquire;
    }
}
