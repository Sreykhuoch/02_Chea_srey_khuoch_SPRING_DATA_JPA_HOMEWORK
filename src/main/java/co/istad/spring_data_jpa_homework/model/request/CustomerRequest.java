package co.istad.spring_data_jpa_homework.model.request;

import co.istad.spring_data_jpa_homework.model.Customer;
import co.istad.spring_data_jpa_homework.model.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequest {
    private String name;
    private String address;
    private String phoneNumber;
    private String email;

    public Customer toEntity(){
        Email emailEntity = new Email(null, this.email, null);
        return new Customer(null, this.name, this.address, this.phoneNumber, emailEntity, null);
    }
}
