package utils;

public interface Metrics {
    void startTimer();
    void stopTimer();
    long getElapsedTime();

    void incDFSVisit();
    void incDFSEdge();
    void incKahnPush();
    void incKahnPop();
    void incRelax();

    String summary();
}
