public class SafeHouse extends NormalLoc {
    public SafeHouse(Player player) {
        super(player, "Güvenli Ev ");
    }
    @Override
    public boolean onLocation() {
        System.out.println("Güvenli evdesin. Canın güncellendi.");
        this.getPlayer().setHealth(this.getPlayer().getOriginalHealth());
        return true;

    }
}