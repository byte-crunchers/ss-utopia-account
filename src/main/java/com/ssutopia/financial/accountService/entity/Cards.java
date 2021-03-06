package com.ssutopia.financial.accountService.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.lang.reflect.Field;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cards {
    @Id
    private Long card_num;

    private Integer pin;

    private Integer cvc1;

    private Integer cvc2;

    private LocalDate exp_date;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "accounts_id")
	@JsonBackReference
    private Accounts accounts;

    public Long getCardNum() {
    	return card_num;
    }

	// print all variables to console
	public void printFields() {
		StringBuilder result = new StringBuilder();
		String newLine = System.getProperty("line.separator");

		result.append("{" + newLine);

		// determine fields declared in this class only (no fields of superclass)
		Field[] fields = this.getClass().getDeclaredFields();

		// print field names paired with their values
		for (Field field : fields) {
			try {
				result.append("    " + field.getName() + " = " + field.get(this) + newLine);
			} catch (IllegalAccessException ex) {
				System.out.println(ex);
			}
		}
		result.append("}");

		System.out.println(result.toString());
	}

}
