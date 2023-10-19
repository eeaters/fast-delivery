package io.eeaters.delivery.util;

@FunctionalInterface
public interface NonExSupplier<R> {

    R exec() throws Exception;

    static <R> R exec(NonExSupplier<R> supplier) {
        try {
            return supplier.exec();
        } catch (Exception e) {
            e.printStackTrace();
            //todo log
            return null;
        }
    }

}
