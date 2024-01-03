import java.util.Arrays;

public class ToolStore extends NormalLoc {
    public ToolStore(Player player) {
        super(player, "Mağaza");
    }

    @Override
    public boolean onLocation() {
        System.out.println("->Magazaya hos geldiniz<-");
        boolean showMenu = true;
        while (showMenu) {
            System.out.println("1- Silahlar");
            System.out.println("2- Zırhlar");
            System.out.println("3- Çıkış");
            System.out.print("Seçimin: ");
            int selectCase = scanner.nextInt();
            while (selectCase < 1 || selectCase > 3) {
                System.out.print("Geçersiz değer! Tekrar girin.");
                selectCase = scanner.nextInt();
            }
            switch (selectCase) {
                case 1:
                    printWeapon();
                    buyWeapon();
                    break;
                case 2:
                    printArmor();
                    buyArmor();
                    break;
                case 3:
                    System.out.println("Gücüne Güç Katmak İstediğin Her An Gelebilirsin :)");
                    showMenu = false;
                    break;

            }
        }
        return true;


    }


    public void printWeapon() {
        System.out.println("->Silahlar<-");
        for (Weapon w : Weapon.weapons()) {
            System.out.println(w.getId() + " - " + w.getName() + " <Para: " + w.getPrice() +
                    " , Hasar: " + w.getDamage() + ">");
        }
        System.out.println("0 - Çıkış Yap");

    }

    private void buyWeapon() {
        System.out.println("Bir silah seç: ");
        int selectWeaponID = scanner.nextInt();
        while (selectWeaponID < 0 || selectWeaponID > Weapon.weapons().length) {
            System.out.print("Geçersiz değer! Tekrar girin.");
            selectWeaponID = scanner.nextInt();
        }

        if (selectWeaponID != 0) {
            Weapon selectedWeapon = Weapon.getWeaponObjByID(selectWeaponID);
            if (selectedWeapon != null) {
                if (selectedWeapon.getPrice() > this.getPlayer().getMoney()) {
                    System.out.println("O Kadar Paran Yok!");
                } else {
                    // satın alma işlemi
                    System.out.println(selectedWeapon.getName() + " silahını satın aldın.");
                    int balance = this.getPlayer().getMoney() - selectedWeapon.getPrice();
                    this.getPlayer().setMoney(balance);
                    System.out.println("Kalan paran: " + this.getPlayer().getMoney());
                    this.getPlayer().getInventory().setWeapon(selectedWeapon);
                }
            }
        }


    }

    private void printArmor() {
        System.out.println("->Zırhlar<-");
        for (Armor a : Armor.armors()) {
            System.out.println(a.getId() + " - " + a.getName() + " <Para: " + a.getPrice() +
                    " , Savunma: " + a.getBlock() + ">");
        }
        System.out.println("0 - Çıkış Yap");
    }

    private void buyArmor() {
        System.out.println("Bir zırh seç: ");
        int selectArmorID = scanner.nextInt();
        while (selectArmorID < 0 || selectArmorID > Armor.armors().length) {
            System.out.print("Geçersiz değer! Tekrar girin.");
            selectArmorID = scanner.nextInt();
        }
        if (selectArmorID != 0) {
            Armor selectedArmor = Armor.getArmorObjByID(selectArmorID);
            if (selectedArmor != null) {
                if (selectedArmor.getPrice() > this.getPlayer().getMoney()) {
                    System.out.println("O Kadar Paran Yok!.");
                } else {
                    System.out.println(selectedArmor.getName() + " zırhını satın aldın.");
                    int balance = this.getPlayer().getMoney() - selectedArmor.getPrice();
                    this.getPlayer().setMoney(balance);
                    System.out.println("Kalan paran: " + this.getPlayer().getMoney());
                    this.getPlayer().getInventory().setArmor(selectedArmor);
                }
            }
        }
    }
}