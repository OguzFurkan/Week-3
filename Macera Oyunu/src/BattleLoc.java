import java.util.Random;

public abstract class BattleLoc extends Location {

    private Obstacle obstacle;
    private String award;
    private int maxObstacle;


    public BattleLoc(Player player, String name, Obstacle obstacle, String award, int maxObstacle) {
        super(player, name);
        this.obstacle = obstacle;
        this.award = award;
        this.maxObstacle = maxObstacle;
    }

    @Override
    public boolean onLocation() {
        int obsNumber = this.randomObstacleNumber();
        System.out.println("Şu an buradasın: " + this.getName());
        System.out.println("Dikkat et! Burada " + obsNumber + " tane " + this.getObstacle().getName() + " yaşıyor");
        System.out.print("<S>avaş ya da <K>aç: ");
        String selectCase = scanner.nextLine();
        selectCase = selectCase.toUpperCase();
        if (selectCase.equals("S")) {
            if (combat(obsNumber)) {
                System.out.println(this.getName() + " tüm düşmanları yok ettin!");
                if (this.award.equals("Food") && this.getPlayer().getInventory().isFood() == false) {
                    System.out.println(this.award + " Kazandın!");
                    this.getPlayer().getInventory().setFood(true);
                } else if (this.award.equals("Water") && this.getPlayer().getInventory().isWater() == false) {
                    System.out.println(this.award + " Kazandın!");
                    this.getPlayer().getInventory().setWater(true);
                } else if (this.award.equals("Firewood") && this.getPlayer().getInventory().isFirewood() == false) {
                    System.out.println(this.award + " Kazandın!");
                    this.getPlayer().getInventory().setFirewood(true);
                }
                return true;
            }
            if (this.getPlayer().getHealth() <= 0) {
                System.out.println("Oyun bitti, her şey buraya kadarmış :(");
                return false;
            }

        }

        return true;
    }


    public boolean combat(int obsNumber) {
        if (this.getObstacle().getName().equals("Snake")) {
            this.getObstacle().setDamage(snakeDamage());
        }
        int hitChance = 0;

        for (int i = 1; i <= obsNumber; i++) {
            this.getObstacle().setHealth(this.obstacle.getOriginalHealth());
            playerStats();
            obstacleStats(i);
            while (this.getPlayer().getHealth() > 0 && this.getObstacle().getHealth() > 0) {
                hitChance = (int) (Math.random() * 9 + 1);
                System.out.print("<V>ur ya da <K>aç: ");
                String selectCombat = scanner.nextLine().toUpperCase();
                if (selectCombat.equals("V")) {
                    if (hitChance < 5) {
                        System.out.println("Vurdun");
                        this.getObstacle().setHealth(this.getObstacle().getHealth() - this.getPlayer().getTotalDamage());
                        afterHit();

                    } else {
                        if (this.getObstacle().getHealth() > 0) {

                            System.out.println("Canavar vurdu");
                            int obstacleDamage = this.getObstacle().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                            if (obstacleDamage < 0) {
                                obstacleDamage = 0;
                            }
                            this.getPlayer().setHealth(this.getPlayer().getHealth() - obstacleDamage);
                            afterHit();

                        }
                    }

                } else {
                    return false;
                }

            }
            if (this.getObstacle().getHealth() < this.getPlayer().getHealth()) {
                System.out.println("Düşmanı Alt Ettin!");
                if (getObstacle().getName().equals("Snake")) {
                    drop();
                }
                System.out.println(this.getObstacle().getAward() + " parayı buldun :)");

                this.getPlayer().setMoney(this.getPlayer().getMoney() + this.getObstacle().getAward());
                System.out.println("Güncel bakiyen: " + this.getPlayer().getMoney());
            } else {
                return false;
            }
        }
        return true;
    }

    public int snakeDamage() {
        Random r = new Random();
        return r.nextInt(4) + 3;
    }

    public void drop() {
        Random r = new Random();
        int chance = r.nextInt() * 100;

        if (chance < 55) {
            Random r1 = new Random();
            int itemchance = r1.nextInt() * 100;
            if (itemchance <= 30) {
                int weaponChance = r1.nextInt() * 100;
                if (weaponChance <= 20) {
                    getPlayer().getInventory().setWeapon(Weapon.getWeaponObjByID(3));
                    System.out.println(getPlayer().getInventory().getWeapon().getName() + " kazandın.");
                }
                if (20 < weaponChance && weaponChance <= 50) {
                    getPlayer().getInventory().setWeapon(Weapon.getWeaponObjByID(2));
                    System.out.println(getPlayer().getInventory().getWeapon().getName() + " kazandın.");
                }
                if (50 < weaponChance) {
                    getPlayer().getInventory().setWeapon(Weapon.getWeaponObjByID(1));
                    System.out.println(getPlayer().getInventory().getWeapon().getName() + " kazandın.");
                }

            }
            if (30 < itemchance && itemchance <= 60) {
                Random r2 = new Random();
                int armorChance = r2.nextInt() * 100;
                if (armorChance <= 20) {
                    getPlayer().getInventory().setArmor(Armor.getArmorObjByID(3));
                    System.out.println(getPlayer().getInventory().getArmor().getName() + " ele geçirildi.");
                }
                if (20 < armorChance && armorChance <= 50) {
                    getPlayer().getInventory().setArmor(Armor.getArmorObjByID(2));
                    System.out.println(getPlayer().getInventory().getArmor().getName() + " ele geçirildi.");
                }
                if (50 < armorChance) {
                    getPlayer().getInventory().setArmor(Armor.getArmorObjByID(1));
                    System.out.println(getPlayer().getInventory().getArmor().getName() + " ele geçirildi.");
                }
            }
            if (60 < itemchance) {
                Random r3 = new Random();
                int goldChance = r3.nextInt() * 100;
                if (goldChance <= 20) {
                    getPlayer().setMoney(getPlayer().getMoney() + 10);
                    System.out.println("10 altın elde ettin.");
                }
                if (20 < goldChance && goldChance <= 50) {
                    getPlayer().setMoney(getPlayer().getMoney() + 5);
                    System.out.println("5 altın elde ettin.");
                }
                if (50 < goldChance) {
                    getPlayer().setMoney(getPlayer().getMoney() + 1);
                    System.out.println("1 altın elde ettin.");
                }
            }
        } else {
            System.out.println("Hicbir şey kazanamadın.");
        }
    }

    public void afterHit() {
        System.out.println("Canın: " + this.getPlayer().getHealth());
        System.out.println(this.getObstacle().getName() + " canı: " + this.getObstacle().getHealth());

    }

    public void playerStats() {
        System.out.println("Oyuncu değerleri");
        System.out.println("----------------");
        System.out.println("Sağlık: " + this.getPlayer().getHealth());
        System.out.println("Silah: " + this.getPlayer().getInventory().getWeapon().getName());
        System.out.println("Hasar: " + this.getPlayer().getTotalDamage());
        System.out.println("Zırh: " + this.getPlayer().getInventory().getArmor().getName());
        System.out.println("Savunma: " + this.getPlayer().getInventory().getArmor().getBlock());
        System.out.println("Para: " + this.getPlayer().getMoney());
        System.out.println();

    }

    public void obstacleStats(int i) {
        System.out.println(i + ". " + this.getObstacle().getName() + " Değerleri");
        System.out.println("-----------------");
        System.out.println("Sağlık:" + this.getObstacle().getHealth());
        System.out.println("Hasar: " + this.getObstacle().getDamage());
        System.out.println("Ödül: " + this.getObstacle().getAward());
        System.out.println();
    }

    public int randomObstacleNumber() {
        Random r = new Random();
        return r.nextInt(this.getMaxObstacle()) + 1;
    }

    public Obstacle getObstacle() {
        return obstacle;
    }

    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public int getMaxObstacle() {
        return maxObstacle;
    }

    public void setMaxObstacle(int maxObstacle) {
        this.maxObstacle = maxObstacle;
    }
}