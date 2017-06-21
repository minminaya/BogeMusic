package com.minminaya.data.model;

/**
 * 每行歌词的实体类，实现Comparable接口为了利于List<LrcRow>的sort排序
 */
public class LrcRowModel implements Comparable<LrcRowModel> {

    /**
     * 开始时间，00:00:00
     */
    private String timeStr;

    /**
     * 开始时间，毫秒，1000
     */
    private int time;
    /**
     * 歌词内容
     */
    private String content;
    /**
     * 该行歌词显示的总时间
     */
    private int totalTime;

    public LrcRowModel(String timeStr, int time, String content, int totalTime) {
        this.timeStr = timeStr;
        this.time = time;
        this.content = content;
        this.totalTime = totalTime;
    }

    public String getTimeStr() {
        return timeStr;
    }

    public void setTimeStr(String timeStr) {
        this.timeStr = timeStr;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }


    @Override
    public int compareTo(LrcRowModel o) {
        return 0;
    }
}