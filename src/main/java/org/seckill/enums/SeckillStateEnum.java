package org.seckill.enums;

public enum SeckillStateEnum {
    SUCCESS(1, "Success seckill"),
    END(0, "Seckill closed"),
    REPEAT_KILL(-1, "Repeat kill"),
    INNER_ERROR(-2, "System error"),
    DATA_REWRITE(-3, "Data rewrited");

    private int state;

    private String stateInfo;

    SeckillStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public static SeckillStateEnum stateOf(int index) {
        for (SeckillStateEnum state : values()){
            if (state.getState() == index) {
                return state;
            }
        }
        return null;
     }
}
