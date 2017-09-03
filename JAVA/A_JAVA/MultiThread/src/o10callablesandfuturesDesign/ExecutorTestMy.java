package o10callablesandfuturesDesign;

public class ExecutorTestMy {

  
   public static void main(String[] args) {
       
       Future<String> fu = new Future<>(new Callable<String>() {

        @Override
        public String call() {
           
            return "hello!!";
        }
    });
       
       new Thread(fu).start();
       
       System.out.println(fu.get());
    
   }
   
}
