package o10callablesandfuturesDesign;

public class Future<T> implements Runnable {
    Thread currentTid = null;;

    Callable<T> ca=null;
    T t = null;

    public Future(Callable<T> ca) {
        this.ca = ca;
    }

    public T get() {

        while (currentTid == null) {

        }

        try {
            currentTid.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (t != null) {
            return t;
        }
        return null;
    }

    private void set(T t) {
        this.t = t;
    }

 
    @Override
    public void run() {
        synchronized (this) {
            currentTid = Thread.currentThread();
            System.out.println("i run!!");
            set(ca.call());
        }
    }
}
