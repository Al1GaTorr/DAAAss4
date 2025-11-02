package utils;

public class SimpleMetrics implements Metrics {
    private long startTime, endTime;
    private int dfsVisits = 0, dfsEdges = 0;
    private int kahnPush = 0, kahnPop = 0;
    private int relax = 0;

    @Override
    public void startTimer() { startTime = System.nanoTime(); }

    @Override
    public void stopTimer() { endTime = System.nanoTime(); }

    @Override
    public long getElapsedTime() { return endTime - startTime; }

    @Override
    public void incDFSVisit() { dfsVisits++; }

    @Override
    public void incDFSEdge() { dfsEdges++; }

    @Override
    public void incKahnPush() { kahnPush++; }

    @Override
    public void incKahnPop() { kahnPop++; }

    @Override
    public void incRelax() { relax++; }

    // ✅ Добавляем геттеры для совместимости с Main.java
    public long getTimeNs() { return getElapsedTime(); }
    public int getDfsVisits() { return dfsVisits; }
    public int getDfsEdges() { return dfsEdges; }
    public int getKahnPush() { return kahnPush; }
    public int getKahnPop() { return kahnPop; }
    public int getRelax() { return relax; }

    @Override
    public String summary() {
        return String.format(
                "time(ns)=%d, dfsVisits=%d, dfsEdges=%d, push=%d, pop=%d, relax=%d",
                getTimeNs(), dfsVisits, dfsEdges, kahnPush, kahnPop, relax
        );
    }
}
