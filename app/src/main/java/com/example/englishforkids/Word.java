package com.example.englishforkids;

public class Word {
    private String arabicWord;
    private String word;
    private int imageId;
    private int AudioId;

    public Word(String arabicWord, String word, int imageId, int audioId) {
        this.arabicWord = arabicWord;
        this.word = word;
        this.imageId = imageId;
        AudioId = audioId;
    }

    public String getArabicWord() {
        return arabicWord;
    }

    public String getWord() {
        return word;
    }

    public int getImageId() {
        return imageId;
    }

    public int getAudioId() {
        return AudioId;
    }

}
