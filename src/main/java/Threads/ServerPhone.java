package Threads;

public class ServerPhone implements Runnable {
    Phone phone;
    String operation;

    public ServerPhone(Phone phone, String operation) {
        this.phone = phone;
        this.operation = operation;
    }

    @Override
    public void run() {
        while(true){
            System.out.println("Waiting for client");
            phone.accept();
            String a = phone.readLine();
            String b = phone.readLine();
            int result = calculate(operation, a, b);
            String message = a + " " + operation + " " + b + " = " + result;
            try { Thread.sleep(7000); } catch (InterruptedException e) {}
            phone.writeLine(message);
            System.out.println("Accepted + " + message);
            phone.close();
        }
    }

    private int calculate(String operation, String a, String b) {
        int a1 = Integer.parseInt(a);
        int b2 = Integer.parseInt(b);
        int result = 0;
        if (operation.equals("+")) {
            result = a1 + b2;
        }
        if (operation.equals("-")) {
            result = a1 - b2;
        }
        if (operation.equals("*")) {
            result = a1 * b2;
        }
        if (operation.equals("/")) {
            result = a1 * b2;
        }
        return result;
    }
}
