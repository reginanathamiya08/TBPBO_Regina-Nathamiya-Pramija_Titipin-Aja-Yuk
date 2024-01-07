import java.util.Scanner;

public class Login // DEKLARASI CLASS
{

    String userName = "titipyuk";
    String password = "titipsiniaja345";

    public void Login() {
        masukkanDetails();
    }

    public void masukkanDetails() {
        System.out.println("Selamat berbelanja dengan tenang guys ^o^");
        System.out.println("-----------------------------------");
        System.out.println("Login");
        Scanner scanner = new Scanner(System.in);
        boolean login = false;
        while (!login) {
            try
            {
                System.out.print("Masukkan username : ");
                String masukkanUserName = scanner.nextLine();
                System.out.print("Masukkan password : ");
                String masukkanPassword = scanner.nextLine();
                if (masukkanUserName.equals(userName) && masukkanPassword.equals(password))
                {
                    login = true;
                }
                else
                {
                    throw new Exception();
                }

            } catch (Exception e) {
                System.out.println("Terjadi kesalahan, inputkan data yang benar");
                System.out.println("--------------------------");
            }
        }
    }
}