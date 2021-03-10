import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

public class Main {
Scanner sc = new Scanner(System.in);
Random random = new Random();
Vector<String> vId = new Vector<>();
Vector<String> vNama =new Vector<>();
Vector<String> vJenisKelamin =new Vector<>();
Vector<String> vJabatan = new Vector<>();
Vector<Integer> vGaji = new Vector<>();
Vector<String> manager = new Vector<>();
Vector<String> supervisor = new Vector<>();
Vector<String> admin = new Vector<>();
Vector<String> vManagerId = new Vector<>();
Vector<String> vSupervisorId = new Vector<>();
Vector<String> vAdminId = new Vector<>();

	public Main() {
		mainMenu();
	}
	private void mainMenu() {
		// TODO Auto-generated method stub
		int choose = 0;
		do {
			System.out.println("PT.Mentol");
			System.out.println("=========");
			System.out.println("1. Input");
			System.out.println("2. Exit");
			System.out.println(">> ");
			try {
				choose = sc.nextInt();
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Input must be number");
			}
			sc.nextLine();
		} while (choose < 1 || choose > 2);
		switch (choose) {
		case 1:
			menu();
			break;
		case 2:
			System.out.println("Terima kasih telah menggunakan aplikasi ini");
			System.exit(0);
			break;
		}
	}
	private void menu() {
		// TODO Auto-generated method stub
		int choose = 0;
		do {
			System.out.println("PT.Mentol");
			System.out.println("---------");
			System.out.println("1. Add");
			System.out.println("2. View");
			System.out.println("3. Update");
			System.out.println("4. Delete");
			System.out.println(">> ");
			try {
				choose = sc.nextInt();
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Input harus angka");
			}
			sc.nextLine();
		} while (choose < 1 || choose > 4);
		switch (choose) {
		case 1:
			add();
			break;
		case 2:
			view();
			break;
		case 3:
			update();
			break;
		case 4:
			delete();
			break;
		}
	}
	private void delete() {
		// TODO Auto-generated method stub
		if(vNama.isEmpty()) {
			System.out.println("Tidak ada data");
			System.out.println("Enter...");
			sc.nextLine();
			clear();
			menu();
		}
		else {
			ascending();
			int deleteNum = 0;
			do {
				System.out.print("Pilih nomor karyawan yang ingin dihapus [1..."+ vNama.size() +"]: ");
				try {
					deleteNum  = sc.nextInt();
				} catch (Exception e) {
					
				}sc.nextLine();
			} while (deleteNum < 1 || deleteNum > vNama.size());
			
			vNama.remove(deleteNum-1);
			vJenisKelamin.remove(deleteNum-1);
			vJabatan.remove(deleteNum-1);
			vId.remove(deleteNum-1);
			vGaji.remove(deleteNum-1);
			
			System.out.println("Enter to return");
			sc.nextLine();
			clear();
			menu();
		}
	}
	private void update() {
		// TODO Auto-generated method stub
		if(vNama.isEmpty()) {
			System.out.println("Tidak ada data");
			System.out.println("Enter...");
			sc.nextLine();
			clear();
			menu();
		}
		else {
			ascending();
			int choose = 0;
			String id = "";
			String nama = "";
			String jenisKelamin = "";
			String jabatan = "";
			int gaji = 0;
			
			for(int i = 0; i < 2; i++) {
				id += (char)(random.nextInt(26) + 65);
			}
			
			id += "-";
			
			for(int i = 0; i < 4; i++) {
				id += random.nextInt(10);
			}
			
			do {
				System.out.println("Input nomor karyawan yang ingin di update[1.. " + vNama.size() + " ] : ");
				try {
					choose = sc.nextInt();
				} catch (Exception e) {
					// TODO: handle exception
				}
				sc.nextLine();
			} while (choose < 1 || choose > vNama.size());
			
			do {
				System.out.println("Input nama (>=3): ");
				nama = sc.nextLine();
			} while (nama.length() < 3);
			
			do {
				System.out.println("Input jenis kelamin(Laki-laki | Perempuan): ");
				jenisKelamin = sc.nextLine();
			} while (!jenisKelamin.equals("Laki-laki") && !jenisKelamin.equals("Perempuan"));
			
			do {
				System.out.println("Input jabatan(Manager | Supervisor | Admin): ");
				jabatan = sc.nextLine();
			} while (!jabatan.equals("Manager") && !jabatan.equals("Admin") && !jabatan.equals("Supervisor"));
			
			int number = 0;
			if (vJabatan.get(number -1).equals("Manager")) {
				manager.remove(manager.size()-1);
				vManagerId.remove(vId.get(number-1));
			}
			else if (vJabatan.get(number-1).equals("Supervisor")) {
				supervisor.remove(supervisor.size()-1);
				vSupervisorId.remove(vId.get(number-1));
			}
			else if (vJabatan.get(number-1).equals("Admin")) {
				admin.remove(admin.size()-1);
				vAdminId.remove(vId.get(number-1));
			}
			
			if(jabatan.equals("Manager")) {
				gaji = 8000000;
				manager.add(jabatan);
				vManagerId.add(id);
				}
			if(jabatan.equals("Admin")) {
				gaji = 4000000;
				admin.add(jabatan);
				vAdminId.add(id);
			}
			if(jabatan.equals("Supervisor")) {
				gaji = 6000000;
				supervisor.add(jabatan);
				vSupervisorId.add(id);
			}
			vNama.set((number-1), nama);
			vJenisKelamin.set((number-1), jenisKelamin);
			vJabatan.set((number-1), jabatan);
			vId.set((number-1), id);
			vGaji.set((number-1), gaji);
			
			if (manager.size() % 3 == 0 && manager.size() != 0) {
				for (int i = 0; i < manager.size(); i++) {
					vGaji.set(i, (vGaji.get(i) + (gaji * 10 / 100)));
				}
				System.out.println("Bonus sebesar 10% telah diberikan kepada karyawan dengan id " + (vManagerId.toString().replace("[", "").replace("]", "")));
			}
			
			if (supervisor.size() % 3 == 0 && supervisor.size() != 0) {
				for (int i = 0; i < supervisor.size(); i++) {
					vGaji.set(i, (int) (vGaji.get(i) + (gaji * 7.5 / 100)));
				}
				System.out.println("Bonus sebesar 7.5% telah diberikan kepada karyawan dengan id " + (vSupervisorId.toString().replace("[", "").replace("]", "")));
			}
			
			if (admin.size() % 3 == 0 && admin.size() != 0) {
				for (int i = 0; i < admin.size(); i++) {
					vGaji.set(i, (vGaji.get(i) + (gaji * 5 / 100)));
				}
				System.out.println("Bonus sebesar 5% telah diberikan kepada karyawan dengan id " + (vAdminId.toString().replace("[", "").replace("]", "")));
			}
			
			System.out.println("Enter to return");
			sc.nextLine();
			clear();
			menu();
		}
	}
	private void view() {
		// TODO Auto-generated method stub
		if(vNama.isEmpty()) {
			System.out.println("Tidak ada data");
			System.out.println("Enter...");
			sc.nextLine();
			clear();
			menu();
		}
		else {
			ascending();
			System.out.println("Enter...");
			sc.nextLine();
			clear();
			menu();
		}
	}
	
	private void add() {
		// TODO Auto-generated method stub
		String id = "";
		String nama = "";
		String jenisKelamin = "";
		String jabatan = "";
		int gaji = 0;
		
		for(int i = 0; i < 2; i++) {
			id += (char)(random.nextInt(26) + 65);
		}
		
		id += "-";
		
		for(int i = 0; i < 4; i++) {
			id += random.nextInt(10);
		}
		
		do {
			System.out.println("Input nama (>=3): ");
			nama = sc.nextLine();
		} while (nama.length() < 3);
		
		do {
			System.out.println("Input jenis kelamin(Laki-laki | Perempuan): ");
			jenisKelamin = sc.nextLine();
		} while (!jenisKelamin.equals("Laki-laki") && !jenisKelamin.equals("Perempuan"));
		
		do {
			System.out.println("Input jabatan(Manager | Supervisor | Admin): ");
			jabatan = sc.nextLine();
		} while (!jabatan.equals("Manager") && !jabatan.equals("Admin") && !jabatan.equals("Supervisor"));
		
		if(jabatan.equals("Manager")) {
			gaji = 8000000;
			manager.add(jabatan);
			vManagerId.add(id);
			}
		if(jabatan.equals("Admin")) {
			gaji = 4000000;
			admin.add(jabatan);
			vAdminId.add(id);
		}
		if(jabatan.equals("Supervisor")) {
			gaji = 6000000;
			supervisor.add(jabatan);
			vSupervisorId.add(id);
		}
		System.out.println("Berhasil menambahkan karyawan dengan id" + id);
		
		vId.add(id);
		vNama.add(nama);
		vJabatan.add(jabatan);
		vJenisKelamin.add(jenisKelamin);
		vGaji.add(gaji);
		
		if (manager.size() % 3 == 0 && manager.size() != 0) {
			for (int i = 0; i < manager.size(); i++) {
				vGaji.set(i, (vGaji.get(i) + (gaji * 10 / 100)));
			}
			System.out.println("Bonus sebesar 10% telah diberikan kepada karyawan dengan id " + (vManagerId.toString().replace("[", "").replace("]", "")));
		}
		
		if (supervisor.size() % 3 == 0 && supervisor.size() != 0) {
			for (int i = 0; i < supervisor.size(); i++) {
				vGaji.set(i, (int) (vGaji.get(i) + (gaji * 7.5 / 100)));
			}
			System.out.println("Bonus sebesar 7.5% telah diberikan kepada karyawan dengan id " + (vSupervisorId.toString().replace("[", "").replace("]", "")));
		}
		
		if (admin.size() % 3 == 0 && admin.size() != 0) {
			for (int i = 0; i < admin.size(); i++) {
				vGaji.set(i, (vGaji.get(i) + (gaji * 5 / 100)));
			}
			System.out.println("Bonus sebesar 5% telah diberikan kepada karyawan dengan id " + (vAdminId.toString().replace("[", "").replace("]", "")));
		}
		
		System.out.println("Enter to return");
		sc.nextLine();
		clear();
		menu();
	}
	
	public void clear() {
		for(int i=0; i < 30; i++) {
			System.out.println();
		}
	}
	
	public void ascending() {
		for(int i = 0; i < vNama.size()-1;i++ ) {
			for(int j = i+1; j <vNama.size(); j++) {
				if(vNama.get(i).compareToIgnoreCase(vNama.get(j))>0) {
					String tempNama = vNama.get(i);
					vNama.set(i,vNama.get(j));
					vNama.set(j, tempNama);
					
					String tempId = vId.get(i);
					vId.set(i, vId.get(j));
					vId.set(j, tempId);
					
					String tempJabatan = vJabatan.get(i);
					vJabatan.set(i, vJabatan.get(j));
					vJabatan.set(j, tempJabatan);
					
					String tempJK = vJenisKelamin.get(i);
					vJenisKelamin.set(i, vJenisKelamin.get(j));
					vJenisKelamin.set(j, tempJK);
					
					int tempGaji = vGaji.get(i);
					vGaji.set(i, vGaji.get(j));
					vGaji.set(j, tempGaji);
				}
			}
		}
		
		for(int i = 0; i < vNama.size(); i++) {
			System.out.println("No. : " + (i+1));
			System.out.println("Kode Karyawan : " + vId.get(i));
			System.out.println("Nama Karyawan : " + vNama.get(i));
			System.out.println("Jenis Kelamin : " + vJenisKelamin.get(i));
			System.out.println("Jabatan : " + vJabatan.get(i));
			System.out.println("Gaji Karyawan : " + vGaji.get(i));
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main();
	}

}
