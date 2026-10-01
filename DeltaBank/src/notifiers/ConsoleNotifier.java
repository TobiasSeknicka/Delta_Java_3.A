package notifiers;

public class ConsoleNotifier implements Notifier {

    public void test(){

    }

    @Override
    public void notify(String message) {
        System.out.println("Console notify: " + message);
    }
}
