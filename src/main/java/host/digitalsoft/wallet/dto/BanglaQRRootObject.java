package host.digitalsoft.wallet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BanglaQRRootObject{

	private String key = "";
	private String length = "";
	private String maxlength = "";
	private String value = "";

}