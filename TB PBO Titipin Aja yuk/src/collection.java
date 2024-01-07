import java.util.*;
import java.sql.*;


public class collection{

        static Connection conn;
        //method menu
        public static void menu(){
            
            Barang barang = new Barang();
        
        //inisialisasi scanner dan exception try and catch
        try (Scanner input = new Scanner (System.in)) {
            String pil;
            boolean isLanjutkan = true;

            String url = "jdbc:mysql://localhost:3306/penitipan_helm";
            try {
            	Class.forName("com.mysql.cj.jdbc.Driver");
            	conn = DriverManager.getConnection(url,"root","");
            	System.out.println("Class Driver ditemukan");
            	
                //perulangan while
            	while (isLanjutkan) {
                    System.out.println("");
                    System.out.println("=========================================");
                    System.out.println("             DAFTAR MENU PROGRAM         ");
                    System.out.println("=========================================");
                    System.out.println("1. Masukkan data penitipan helm");
                    System.out.println("2. Ubah data penitipan helm yang sedang dititip");
                    System.out.println("3. Cek daftar penitipan helm saat ini"); 
                    System.out.println("4. Hapus Data");
                    System.out.println("5. Keluar dari Program");
                    System.out.println("\n_________________________________________");
                    System.out.println("");
                    System.out.print("Masukkan Pilihan : ");
                    pil = input.next();
                    System.out.println("_________________________________________\n");
            		
                    //percabangan switch case
            		switch (pil) {
            			case "1":
                            barang.jenisHelm();
                            barang.noPenitipan();
                            barang.namaPemilik();
                            barang.jamMasuk();
                            barang.insertData();
            				break;

            			case "2":
                            barang.editData();
            				break;

            			case "3":
                            barang.showData();
            				break;

                        case "4":
                        barang.deleteData();
                        
                        break;

                        

            			case "5":
                            isLanjutkan = false;
                            System.out.println("Semoga Harimu Menyenangkan, Sampai Jumpa");
                            break;
                        
            			default:
            				System.err.println("\nInput tidak ditemukan\n pilih [1-5]");
            		}
            		
            		System.out.print("\nApakah Anda ingin melanjutkan [y/n]? ");
            		pil = input.next();
            		isLanjutkan = pil.equalsIgnoreCase("y");
            	}
            	System.out.println("\nSenang Bekerja sama dengan anda ");
            	
            }
            //exception koneksi database
            catch(ClassNotFoundException ex) {
            	System.err.println("Driver Error");
            	System.exit(0);
            }
            //sql exception
            catch(SQLException e){
            	System.err.println("Koneksi tidak berhasil");
            }
        }
        }
    
        //method penilaian
        public static void penilaian(){
            //collection hashmap 
            HashMap<Integer, String> data = new HashMap<Integer, String>();
    
            //set data hashmap
            data.put(1, "Buruk");
            data.put(2, "Biasa saja");
            data.put(3, "Cukup baik");
            data.put(4, "Sangat baik dan memuaskan");
            
            //perulangan utnuk setiap set data
            for(Map.Entry nama: data.entrySet()){
                System.out.println("kunci: " + nama.getKey() + " Nilai: " + nama.getValue()) ;
            }
    
            System.out.println("Sampaikan Feedback anda melalui email kami titipinajayuk.com\n");
            System.out.println("Instagram : @titpinajayuk");
            System.out.println("Hp        : 081288889999");
            }
    
}
