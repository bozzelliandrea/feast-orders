package arch.component;

public interface LifeCycleStrategy {

    default void onBoot() {
    }

    default void onShutdown() {
    }
}
