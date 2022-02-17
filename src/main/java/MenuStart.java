import java.util.ArrayList;

import java.util.Scanner;

public class MenuStart implements Menu {

    private ArrayList<Contact> arrayList = new ArrayList<>(10);
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void glavMenu() {
        System.out.println("1 - весь список");
        System.out.println("2 - Добавить контакты");
        System.out.println("3 - Удлить контакты");
        System.out.println("4 - Поиск");

        String otvet = scanner.nextLine();
        switch (otvet) {
            case "1":
                vse();
                break;
            case "2":
                addContact();
                break;
            case "3":
                delitContact();
                break;
            case "4":
                poisk();
                break;
            default:
                System.out.println("не верный пункт меню");
                glavMenu();
        }


    }

    private void vse() {
        for (Contact contact : arrayList) {
            System.out.println(contact);
        }
        exitGlavMenu();
    }


    @Override
    public void addContact() { //2
        System.out.println("Введите имя ");
        String name = scanner.nextLine();
        System.out.println("Введите фамилию");
        String surname = scanner.nextLine();
        System.out.println("Введите номер ");

        if (scanner.hasNextInt()) {
            int nomer = Integer.parseInt(scanner.nextLine());
            if (nomer <= 999999999) {
                Contact contact = addCont(name, surname, nomer);

                String contactyes="";
                boolean  n = false;
                for (Contact contact1 : arrayList) {
                    if(contact.equals(contact1)){
                        n = true;
                        contactyes =contact1.getName() +" "+  contact1.getSurname();
                    }
                }

                if (!n) {
                    arrayList.add(contact);
                    System.out.println("Контакт был добавлен");
                    exitGlavMenu();
                }else {
                    System.out.println("Такой номер уже был добавлен");
                    System.out.println("Он подписан как :" + contactyes);
                    exitGlavMenu();
                }
            }
        } else {
            System.out.println("Не коректный номер!Контакт не был добавлен");
            exitGlavMenu();
        }
    }

    @Override
    public void delitContact() {
        System.out.println("1 - Удаление по номеру");
        System.out.println("2 - Удаление по имени фамилии ");
        System.out.println("0 - выход");
        if (scanner.hasNextInt()) {
            int otvet = Integer.parseInt(scanner.nextLine());
            if (otvet == 1) {             //удаление по номеру
                System.out.println("Введите номер для удаления");
                delitNomer();

            } else if (otvet == 2) {        //удаление имени фамилии
                delitSurname();
            } else if (otvet == 0) {
                glavMenu();
            } else {
                System.out.println("Не верная команду");
                delitContact();
            }
        } else {
            System.out.println("вы ввели не верную команду попробуйте еще раз!");
            delitContact();
        }


    }

    @Override
    public void exitGlavMenu() {
        System.out.println("0 - Для выхода в глав меню");
        int exit = Integer.valueOf(scanner.nextLine());
        if (exit == 0) {
            glavMenu();
        } else {
            exitGlavMenu();
        }
    }


    private void delitSurname() {
        System.out.println("Введите имя и фамилию");
        String name = scanner.nextLine();

        Contact[] contact1 = new Contact[10];
        int count = 0;
        String[] n = name.split(" ");
        for (Contact contact : arrayList) {
            if (contact.getName().equals(n[0]) && contact.getSurname().equals(n[1])) {
                contact1[count] = contact;
                count++;
            }
        }

        if (count == 1) {
            arrayList.remove(contact1[0]);
            System.out.println("Контакт был удален");
            exitGlavMenu();
        } else if (count == 0) {
            System.out.println("Контакт не найден ");
            exitGlavMenu();
        } else {
            System.out.println("Контактов с тамим именем несколько введите номер для удаления");

            for (int i = 0; i < count; i++) {
                System.out.println(contact1[i].getName() + " " + contact1[i].getSurname() + " " + contact1[i].getNomer());
            }
            delitNomer();
        }

    }

    private Contact addCont(String name, String surname, int nomer) {
        Contact contact = new Contact(name, surname, nomer);
        return contact;


    }

    private void delitNomer() {
        boolean nn = false;
        if (scanner.hasNextInt()) {
            int s = Integer.valueOf(scanner.nextLine());
            if (s <= 1000000 && s > 0) {
                for (Contact contactfor : arrayList) {
                    if (contactfor.getNomer() == s) {
                        nn = true;
                        System.out.println(arrayList.remove(contactfor));
                        System.out.println("Контакт удален");
                        exitGlavMenu();
                    } else
                        System.out.println("не равен");
                }
                if (!nn) {
                    System.out.println("нет такого номера ");
                    exitGlavMenu();
                }
            } else {
                System.out.println("не верный формат номера");
                exitGlavMenu();
            }
        } else {
            System.out.println("вы ввели не корректный номер");
            exitGlavMenu();
        }
    }

    private void poisk() {
        System.out.println("1 - поиск по индексу ");
        System.out.println("2 - поиск по индексам ");
        System.out.println("0 - выйти");
        String otvet = scanner.nextLine();

        switch (otvet) {
            case "1":
                System.out.println("Введите индекс");
                if (scanner.hasNextInt()) {
                    int otvet1 = Integer.valueOf(scanner.nextLine());
                    if (otvet1 < arrayList.size()) {
                        System.out.println(arrayList.get(otvet1));
                        exitGlavMenu();
                    } else {
                        System.out.println("нету контакта под таким индексом");
                        poisk();
                    }
                } else {
                    System.out.println("не число");
                    poisk();
                }
                break;

            case "2":
                System.out.println("Введите через пробел от и до какого индекса вывести");
                int ot = scanner.nextInt();
                int doo = scanner.nextInt();
                if (Math.max(ot, doo) < arrayList.size()) {
                    for (int i = Math.min(ot, doo); i <= Math.max(ot, doo); i++) {
                        System.out.println(arrayList.get(i));
                    }
                    scanner.nextLine();
                    exitGlavMenu();
                } else {
                    String a = arrayList.size() < 1 ? "Книга Пустая" : "максимальный индекс " + (arrayList.size() - 1);
                    System.out.println(a);
                    scanner.nextLine();
                    exitGlavMenu();
                }
                break;

            case "0":
                glavMenu();
                break;

            default:
                System.out.println("не верный пункт меню");
                poisk();
        }

    }


}
