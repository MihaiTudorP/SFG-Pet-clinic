package guru.springframework.sfgpetclinic.model;

public class VetSpecialty extends BaseEntity {
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
