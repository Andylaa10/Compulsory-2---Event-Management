package be;

public class Customer {

    private int id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String study;
    private String note;

    /**
     * Constructor
     */
    public Customer() {
    }

    /**
     * Constructor with id, firstName, lastName, phoneNumber and email
     * @param id
     * @param firstName
     * @param lastName
     * @param phoneNumber
     * @param email
     */
    public Customer(int id, String firstName, String lastName, String phoneNumber, String email, String study, String note) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.study = study;
        this.note = note;
    }

    /**
     * Gets the id
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id
     * Sets the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the firstName
     * @return firstname
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the firstName
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the lastName
     * @return lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the lastName
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the phoneNumber
     * @return phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the phoneNumber
     * @param phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets the email
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the education/study
     * @return
     */
    public String getStudy() {
        return study;
    }

    /**
     * Sets the education/study
     * @param study
     */
    public void setStudy(String study) {
        this.study = study;
    }

    /**
     * Gets the note
     * @return
     */
    public String getNote() {
        return note;
    }

    /**
     * Sets the note
     * @param note
     */
    public void setNote(String note) {
        this.note = note;
    }
}
