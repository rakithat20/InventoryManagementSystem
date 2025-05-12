package src.model;

public class Supplier {
    private int id;
    private String name;
    private String contactNumber;
    private String email;
    private String address;

    public Supplier(int id, String name, String contactNumber, String email, String address) {
        this.id = id;
        this.name = name;
        this.contactNumber = contactNumber;
        this.email = email;
        this.address = address;
    }

    public Supplier(String name, String contactNumber, String email, String address) {
        this.name = name;
        this.contactNumber = contactNumber;
        this.email = email;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.contactNumber = phone;
    }


    public void setAddress(String address) {
        this.address = address;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
