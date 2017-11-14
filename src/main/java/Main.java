
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String rootPath = "/my_app/";
        String[] hosts = new String[]{
            "ec2-34-213-109-55.us-west-2.compute.amazonaws.com:2181",
            "ec2-34-213-109-55.us-west-2.compute.amazonaws.com:2181",
            "ec2-52-88-161-170.us-west-2.compute.amazonaws.com:2181"
        };
        String connectionString = "";
        for (int i = 0; i < hosts.length; i++) {
            connectionString += hosts[i];
            if (i != hosts.length - 1) {
                connectionString += ",";
            }
        }

        Scanner in = new Scanner(System.in);

        Client client = new Client(connectionString);

        boolean isRunning = true;
        while (isRunning) {
            System.out.println("Choose your action: ");
            System.out.println("1. Create a file;");
            System.out.println("2. Delete a file;");
            System.out.println("3. Read a file;");
            System.out.println("4. Append to a file;");
            System.out.println("5. Quit the program.");
            int action = in.nextInt();

            String fileName = null;
            switch (action) {
                case 1:
                    System.out.println("Enter fileName: ");
                    fileName = in.next();
                    client.createFile(rootPath + fileName);
                    break;
                case 2:
                    System.out.println("Enter fileName: ");
                    fileName = in.next();
                    client.deleteFile(rootPath + fileName);
                    break;
                case 3:
                    System.out.println("Enter fileName: ");
                    fileName = in.next();
                    client.readFile(rootPath + fileName);
                    break;
                case 4:
                    System.out.println("Enter fileName: ");
                    fileName = in.next();
                    in.nextLine();
                    System.out.println("Enter your text (line): ");
                    String line = in.nextLine();
                    client.appendToFile(rootPath + fileName, line);
                    break;
                case 5:
                    isRunning = false;
                    break;
                default:
                    System.out.println("The program did not understand your input.");
                    break;
            }
            System.out.println();
        }

        in.close();
    }
}
