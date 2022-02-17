import java.util.Objects;

public class Contact {
    private String name;
    private  String surname;
    private  int nomer;

    @Override
    public String toString() {
        return name+ " " + surname  +" : " + nomer;
    }

    public Contact(String name, String surname, int nomer) {
        this.name = name;
        this.surname = surname;
        this.nomer = nomer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getNomer() {
        return nomer;
    }

    public void setNomer(int nomer) {
        this.nomer = nomer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return nomer == contact.nomer;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomer);
    }
}
