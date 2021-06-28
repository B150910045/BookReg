import java.io.*;



public class TextFile {



	public String line[][] = new String[10][4];



	public String read(String path) {

		String singleLine;

		String fullText = "";

		File file = new File(path);

		try {

			int i = 0;

			BufferedReader br = new BufferedReader(new FileReader(file));

			while ((singleLine = br.readLine()) != null) {

				fullText += singleLine;

				String tmp_line[] = singleLine.split("/");

				for (int j = 0; j < 4; j++) {

					line[i][j] = tmp_line[j];

				}

				i++;

			}

		} catch (IOException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

		return fullText;

	}



	public void write(String fileName, boolean isAppend) {

		String str = "File Handling in Java using " + " FileWriter and FileReader";



		FileWriter fw;

		try {

			if (isAppend == true) {

				// Текстийг хуучин файлын төгсгөлд залгаж бичих

				fw = new FileWriter(fileName, true);

			} else {

				// Текстийг хуучин файлд дарж бичих

				fw = new FileWriter(fileName);

			}

			fw.write(str);

			// Файлд текстийг бичээд шинэ мөрөнд зурсор байрлуулах

			fw.write(System.getProperty("line.separator"));

			fw.close();

		} catch (Exception e) {

			e.printStackTrace();

		}

	}



	public void print() {

		for (int i = 0; i < line.length; i++) {

			System.out.println(line[i][0] + " " + line[i][1] + " " + line[i][2] + " " + line[i][3]);

		}

	}



	public static void main(String[] args) throws Exception {

		String path = "C:\\Users\\Lenovo\\Desktop\\input.txt";

		String fileName = "C:\\Users\\Lenovo\\Desktop\\output.txt";

		TextFile readAndWrite = new TextFile();

		String result = readAndWrite.read(path);

		System.out.println(result);

		readAndWrite.write(fileName, true);

		readAndWrite.print();

	}

}