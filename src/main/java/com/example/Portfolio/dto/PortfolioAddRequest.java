package com.example.Portfolio.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.Data;


@Data
public class PortfolioAddRequest implements Serializable {
	
	
	@NotEmpty(message = "名前を入力してください")
    @Size(max = 100, message = "名前は100桁以内で入力してください")
    private String name;
	
	@NotEmpty(message = "メールアドレスを入力してください")
    @Size(max = 100, message = "メールアドレスは100桁以内で入力してください")
    private String email;
	
	@Pattern(regexp = "", message = "電話番号の形式で入力してください")
    private String phone;
	
	@NotEmpty(message = "パスワードを入力してください")
    @Size(min = 8, max = 30, message = "パスワードは30桁以内で入力してください")
    private String password;

}
