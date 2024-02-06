package my.portal.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestDto extends DtoBase{

	private static final long serialVersionUID = 1L;
	private String commandName;
}
