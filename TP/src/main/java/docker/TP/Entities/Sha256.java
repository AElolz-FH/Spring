package docker.TP.Entities;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import lombok.Builder;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Sha256 {
    private int id;
    private String method;
    private String input;
    private String output;

    public Sha256(String method, String input, String output) {
        this.method = method;
        this.input = input;
        this.output = output;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }
}
