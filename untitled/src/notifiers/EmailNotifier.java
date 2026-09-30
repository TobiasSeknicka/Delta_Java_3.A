package notifiers;

public class EmailNotifier implements Notifier {

    public void test() {
        // ...
    }

    @Override
    public void notify(String message) {
        System.out.println("Email notify: " + message);
    }
}
