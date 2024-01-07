public class Program extends collection {

    
    public static void main(String[] args) throws Exception {
        Login login = new Login();
        login.masukkanDetails();

        //penggunaan String
        String title     = "=              TITIPIN AJA YUK               ";
        String head      = "     Selamat Datang diProgram Penitipan Helm   ";
        String kepala    = head.replace("Selamat pagi", "\nsemoga hal yang baik selalu bersamu"); 
        System.out.println(kepala);  //method replace()
        
        System.out.println("__________________________________________________");
        System.out.println(title.toUpperCase()); //method toUpperCase()
        System.out.println("__________________________________________________");
        
        
        //penggunaan method dari collection (is a)
        menu();
        
        penilaian();
	}
}
    //Login Class
    

