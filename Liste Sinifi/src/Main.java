public class Main {

    public static void main(String[] args) {
        MyList<Integer> list = new MyList<>();
        System.out.println("Dizideki Eleman Sayısı : " + list.size());
        System.out.println("Dizinin Kapasitesi : " + list.getCapacity());
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        System.out.println("Dizideki Eleman Sayısı : " + list.size());
        System.out.println("Dizinin Kapasitesi : " + list.getCapacity());
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(9);
        list.add(10);
        list.add(11);
        System.out.println("Dizideki Eleman Sayısı : " + list.size());
        System.out.println("Dizinin Kapasitesi : " + list.getCapacity());
        list.toArray();

        list.add(10);
        list.add(20);
        list.add(30);
        System.out.println("2. indisteki değer : " + list.get(2));
        list.remove(2);
        list.add(40);
        list.set(0, 100);
        System.out.println("2. indisteki değer : " + list.get(2));
        System.out.println(list.toString());


        System.out.println("Liste Durumu : " + (list.isEmpty() ? "Boş" : "Dolu"));
        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);
        list.add(20);
        list.add(50);
        list.add(60);
        list.add(70);

        System.out.println("Liste Durumu : " + (list.isEmpty() ? "Boş" : "Dolu"));

        System.out.println("Indeks : " + list.indexOf(20));

        System.out.println("Indeks :" + list.indexOf(100));

        System.out.println("Indeks : " + list.lastIndexOf(20));

        Object[] dizi = list.toArray();
        System.out.println("Object dizisinin ilk elemanı :" + dizi[0]);

        MyList<Integer> altListem = list.sublist(0, 3);
        System.out.println(altListem.toString());

        System.out.println("Listemde 20 değeri : " + list.contains(20));
        System.out.println("Listemde 120 değeri : " + list.contains(120));

        list.clear();
        System.out.println(list.toString());
    }
}