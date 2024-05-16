package com.example.Portfolio.dto;

import jakarta.validation.constraints.NotEmpty;

public class PortfolioSearchRequest {
	
	  @NotEmpty
	  private String name;  // ユーザー名を保存するための場所

	  @NotEmpty  // パスワードは空であってはならないというルール
	  private String password;  // パスワードを保存するための場所

	  @NotEmpty  // メールアドレスは空であってはならないというルール
	  private String email;  // メールアドレスを保存するための場所

	    // 以下は各値を取得するためのメソッド（ゲッター）です。
	  public String getName() {
	      return name;  // ユーザー名を返す
	  }

	    public void setUsername(String name) {
	        this.name = name;  // ユーザー名を設定する
	    }

	    public String getPassword() {
	        return password;  // パスワードを返す
	    }

	    public void setPassword(String password) {
	        this.password = password;  // パスワードを設定する
	    }

	    public String getEmail() {
	        return email;  // メールアドレスを返す
	    }

	    public void setEmail(String email) {
	        this.email = email;  // メールアドレスを設定する
	    }

}
