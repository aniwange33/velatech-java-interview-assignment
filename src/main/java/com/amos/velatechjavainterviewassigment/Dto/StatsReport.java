package com.amos.velatechjavainterviewassigment.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatsReport {
    private boolean success;
    private int start;
    private int limit;
    private int size;
    private Object payload;

    private StatsReport(int start, int limit, int size) {
        this.start = start;
        this.limit = limit;
        this.size = size;
    }

    public static StatsReport createStatsReport(int start, int limit, int size) {
        return new StatsReport(start, limit, size);
    }

}
