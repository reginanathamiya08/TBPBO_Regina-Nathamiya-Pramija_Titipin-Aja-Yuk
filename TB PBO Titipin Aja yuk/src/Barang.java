import java.util.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

//implements
public class Barang implements Penitipan{

    //koneksi database
    Connection conn;
    String url = "jdbc:mysql://localhost:3306/penitipan_helm";

    int jamm, jamk, waktu;

    Scanner input = new Scanner(System.in);
    int  tagihan, bayar, kembalian, jnsHelm;
    String namaPemilik; 
    int noPenitipan;

    //override method dari kelas interface
    @Override
    public void jenisHelm() {
        // TODO Auto-generated method stub
        System.out.println("Jenis Helm  : \n1. Dewasa\n2. Anak-Anak");
        System.out.print("Masukkan Jenis Helm (angka)\t: ");
        this.jnsHelm = input.nextInt();
        input.nextLine();

        //perulangan while
        while(jnsHelm<1 || jnsHelm>2){
            System.out.println("Inputkan data dengan benar");
            System.out.print("Masukkan Jenis Helm (angka) : ");
            this.jnsHelm = input.nextInt();
            input.nextLine();
        }
        
    }

    //implements method penitipan
    @Override
    public void noPenitipan() {
        // TODO Auto-generated method stub
        System.out.println("Masukkan Nomor Penitipan\t:  ");
        this.noPenitipan = input.nextInt();
    }

    //implement method namaPemilik();
    @Override
    public void namaPemilik() {
        // TODO Auto-generated method stub
        System.out.println("Masukkan Nama Pemilik Helm\t:  ");
        this.namaPemilik = input.next();
    }
    @Override
    public void jamMasuk() {
        // TODO Auto-generated method stub
        
        GregorianCalendar date = new GregorianCalendar();
        this.jamm =  date.get(Calendar.HOUR_OF_DAY);
        System.out.println("Waktu masuk pada jam: "+this.jamm);
    }
    
    @Override
    public void jamKeluar() {
        // TODO Auto-generated method stub

        GregorianCalendar date = new GregorianCalendar();
        this.jamk =  date.get(Calendar.HOUR_OF_DAY);
        System.out.println("Waktu keluar pada jam: "+this.jamk);
    }

    public void penghitungan() throws SQLException {

        System.out.print("\n Nomor Penitipan yang akan keluar : ");
        String exit = input.nextLine();

        //mengakses database
                String sql = "SELECT jam_masuk FROM barang WHERE no_penitipan = " +exit;
                Statement statement = conn.createStatement();
                ResultSet result = statement.executeQuery(sql);

                while(result.next()){
                    this.jamm = result.getInt("jam_masuk");
                }

            this.waktu =  this.jamk-this.jamm;
            System.out.println("Lama waktu penitipan adalah: " + this.waktu + "jam\n\n");

            bayar();

            System.out.println("Tagihan selama " + this.waktu + "jam adalah sebesar Rp." + this.tagihan + ",00");
            System.out.print("Silahkan Masukkan nominal pembayaran: ");
            this.bayar = input.nextInt();
            
            while (this.bayar<this.tagihan){
                System.out.println("Uang yang diberikan kurang sebesar Rp."+ (this.tagihan - this.bayar)+ ",00");
                System.out.print("Silahkan masukkan nominal tambahan: ");
                this.bayar += input.nextInt();
            }
            
            if (this.bayar>this.tagihan){
                System.out.print("Uang kembalian sebesar Rp."+ (this.bayar - this.tagihan) + ",00");
            }else{
                System.out.println("Terimakasih,, Sampai jumpa lain waktu!! :D");
            }
    }

    @Override
    public void bayar() {
        // TODO Auto-generated method stub
        if (this.jnsHelm==1){
            this.tagihan = this.waktu*5000;
        } else {
            this.tagihan = this.waktu*2000;
        }
    }
    
    public void insertData() throws SQLException{
        //insert database
        String sql = "INSERT INTO penitipan (no_penitipan,jenis_helm,nama_pemilik,jam_masuk) VALUES ('"+this.noPenitipan+"', '"+this.jnsHelm+"','"+this.namaPemilik+"','"+this.jamm+"' )";
        conn = DriverManager.getConnection(url,"root","");
        Statement statement = conn.createStatement();
        statement.execute(sql);   
        System.out.println("\n Data telah berhasil dimasukkan"); 
    }

    public void showData() throws SQLException{
        int count = 1;
        String sql = "SELECT no_penitipan, nama_pemilik, jenis_helm, jam_masuk FROM penitipan";
        conn = DriverManager.getConnection(url,"root","");
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(sql);

        //perulangan
        while(result.next()){
            System.out.println(count+". ");
            System.out.print("Nomor Penitipan :");
            System.out.println(result.getInt("no_penitipan"));
            System.out.print("Jenis Helm\t :");
            System.out.println(result.getInt("jenis_helm"));
            System.out.print("Nama Pemilik\t:");
            System.out.println(result.getString("nama_pemilik"));
            System.out.print("Masuk Pada Jam\t:");
            System.out.println(result.getInt("jam_masuk"));
            count++;
        }
    }

    //method edit database
    public void editData()throws SQLException {
        try{
            System.out.println("\n==== Edit Data Penitipan Barang ====");
            showData();
            Integer pil;
            System.out.print("\n Nomor Penitipan yang ingin di ubah : ");
            String edit = input.next();

            //mengakses database
            String sql = "SELECT * FROM penitipan WHERE no_penitipan='"+edit+"'";
            conn = DriverManager.getConnection(url,"root","");
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);

            //percabangan if
            if (result.next()){
                System.out.println("Data yang ingin diubah\n1. Nomor Penitipan\n2. Nama Pemilik\n3. Jenis Helm");
                System.out.print("Pilihan : ");
                pil = input.nextInt();
                input.nextLine();

                //percabangan switch case
                    switch (pil){
                        case 1:
                            System.out.print("Nomor Penitipan <"+result.getInt("no_penitipan")+">\t: ");
                            String editPenitipan = input.next();
                            //update database
                            sql = "UPDATE penitipan SET no_penitipan = '"+editPenitipan+"' WHERE no_penitipan ='"+edit+"'";
                            if(statement.executeUpdate(sql) > 0){
                                System.out.println("\n```Berhasil mengubah nomor penitipan menjadi |"+editPenitipan+"|```\n");
                            }
                            break;
                            
                        case 2:
                            System.out.print("Nama Pemilik <"+result.getString("nama_pemilik")+"> menjadi\t: ");
                            String editNama = input.next();
                            //update database
                            sql = "UPDATE penitipan SET nama_pemilik = '"+editNama+"' WHERE no_penitipan ='"+edit+"'";
                            if(statement.executeUpdate(sql) > 0){
                                System.out.println("\n```Berhasil mengubah Nama Pemilik menjadi |"+editNama+"|```\n");
                            }
                            break;

                        case 3:
                            System.out.print("Jenis Helm <"+result.getInt("jenis_helm")+">\t: ");
                            int editJenis = input.nextInt();
                            //update database
                            sql = "UPDATE penitipan SET jenis_helm = '"+editJenis+"' WHERE no_penitipan ='"+edit+"'";
                            if(statement.executeUpdate(sql) > 0){
                                System.out.println("\n```Berhasil mengubah Jenis Helm menjadi |"+editJenis+"|```\n");
                            }
                            break;

                        

                        default :
                            System.out.println("Inputan harus berupa angka 1/2/3/4/5/6 !");
                            break;
                    }
                    
            }
            else{
                System.out.println("Nomor penitipan yang diinputkan salah");
            }
        }
        //exeption SQL 
        catch (SQLException e) {
            System.err.println("Kesalahan update data");
        }

        //exception input angka
        catch (InputMismatchException e){
            System.err.println("Inputan harus berupa angka!");
        }
    }
    
    public void deleteData()throws SQLException{
        try{
            System.out.println("\n==== Hapus Data Penitipan Helm ====\n");
            showData();
            System.out.print("\n Nomor penitipan yang ingin di hapus : ");
            int del = input.nextInt();
            //menghapus database
            String sql = "DELETE FROM penitipan WHERE no_penitipan LIKE'%"+del+"%'";
            conn = DriverManager.getConnection(url,"root","");
            Statement statement = conn.createStatement();
            if(statement.executeUpdate(sql) > 0){
                System.out.println("Berhasil menghapus data nomor penitipan "+del);
                System.out.println("Pelanggan Dipersilahkan keluar");
            }

            
        }
		catch(SQLException e){
	        System.out.println("Terjadi kesalahan dalam menghapus data");
	    }

        catch(Exception e){
            System.out.println("Input data yang benar!");
        }

        finally{
            //date and time
            LocalDate currdate = LocalDate.now();
            LocalTime currtime = LocalTime.now();
            System.out.println("\n===================================");
            System.out.println("Waktu Akses :");
            System.out.println("Tanggal\t= "+currdate.toString());
            System.out.println("Waktu\t= "+currtime.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
            System.out.println("=====================================\n");
        }
    }


    public void noPenitipanBarang() {
    }


}
