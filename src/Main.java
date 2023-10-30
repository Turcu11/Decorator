public class Main {
    public static void main(String[] args) {
        FileDataSource fileDataSource = new FileDataSource();
        fileDataSource.write("test.txt","Aici este text de proba sa vedem ca scrie in fisier");
        fileDataSource.read("D:\\Facultate\\Anul 3\\Semestrul I\\ingineria-software\\teme\\Decorator\\Decorator\\test.txt");

        EncryptionDecorator encd = new EncryptionDecorator(fileDataSource);
        encd.encrypt("", "Hello There I'm In Oradea");

        CompressionDecorator compd = new CompressionDecorator(fileDataSource);
        compd.compress("Content");
    }
}