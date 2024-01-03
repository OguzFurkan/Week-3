import java.util.Scanner;

public class Game {

    public Scanner scanner = new Scanner(System.in);

    public void start() {
        System.out.println("-> Yeni bir maceraya hazır ol! <-");

        Player player = new Player("Oğuzhan Furkan");
        System.out.println("Maceracı => " + player.getName() + " şimdi senin zamanın!");
        player.selectChar();

        Location location = null;
        while (true) {
            player.printInfo();
            System.out.println();
            System.out.println("*-Bolgeler-*");
            System.out.println( );
            System.out.println("1 - Guvenli Ev --> Burasi senin için güvenli. Düşman yok.");
            System.out.println("2 - Store --> Silah veya zırh satin alabilirsin.");
            System.out.println("3 - Mağara --> <Ödül: Yemek>, dikkatli ol zombi çıkabilir");
            System.out.println("4 - Orman --> <Ödül:  Odun>, dikkatli ol vampir çıkabilir");
            System.out.println("5 - Nehir --> <Ödül: Su>, dikkatli ol ayı çıkabilir");
            System.out.println("6 - Maden --> <Ödül: ?>, dikkatli ol yılan çıkabilir");
            System.out.println("0 - Çıkış Yap --> Oyunu sonlandır.");
            System.out.print("Aksiyona atılmak istediğin bölgeyi seç: ");

            int selectLoc = scanner.nextInt();

            switch (selectLoc) {
                case 0:
                    location=null;
                    break;
                case 1:
                    location = new SafeHouse(player);
                    break;
                case 2:
                    location = new ToolStore(player);
                    break;
                case 3:
                    location = new Cave(player);
                    break;
                case 4:
                    location = new Forest(player);
                    break;
                case 5:
                    location = new River(player);
                    break;
                case 6:
                    location = new Mine(player);
                default:
                    System.out.println("Lütfen gecerli bir bölge gir!");

            }
            if (location.getClass().getName().equals("SafeHouse")) {
                if (player.getInventory().isFirewood() && player.getInventory().isFood() && player.getInventory().isWater()) {
                    System.out.println("Tebrikler Düşmanlarını Yok Ederek Oyunu Kazanarak Gücünü Herkese Gösterdin!");
                    break;
                }
            }

            if (!location.onLocation()) {
                System.out.println("Oyun Bitti, Buraya Kadarmış :(");
                break;
            }

        }


    }
}