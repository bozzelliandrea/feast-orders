package atomic.enums;

public enum OrderStatus {
    TODO, PROGRESS, DONE, DECLINED;

    public boolean isClosed() {
        return this.equals(DONE) || this.equals(DECLINED);
    }
}
