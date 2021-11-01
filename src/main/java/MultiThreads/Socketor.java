package MultiThreads;

public class Socketor {
    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Usage:\r\n " +
                    "java Socketor server 8000 /\r\n" +
                    "java Socketor client 127.0.0.1 8000 45 35");
            return;
        }
        Socketor socketor = new Socketor();
        if (args[0].equals("server")) {
            socketor.runServer(args[1], args[2]);
        }
        if(args[0].equals("client")) {
            socketor.runClient(args[1], args[2], args[3], args[4]) ;
        }
    }

    private void runServer(String port, String operation) {
        Phone phoneServer = new Phone(port);
        System.out.println("Started server with " + operation + " on " + port);
        while(true) {
            Phone phone = new Phone(phoneServer);
            System.out.println("Client accepted");
            new Thread(() -> {
                String a = phone.readLine();
                String b = phone.readLine();
                int result = calculate(operation, a, b);
                String message = a + " " + operation + " " + b + " = " + result;
                try { Thread.sleep(7000); } catch (InterruptedException e) {}
                phone.writeLine(message);
                System.out.println("Accepted + " + message);
                phone.close();
            }).start();
            if (Math.random() == 0.66) {
                break;
            }
        }
        phoneServer.closeServer();

    }


    private void runClient(String ip, String port, String a, String b) {
        Phone phone = new Phone(ip, port);
        phone.writeLine(a);
        phone.writeLine(b);
        String answer = phone.readLine();
        System.out.println(answer);
        phone.close();

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
