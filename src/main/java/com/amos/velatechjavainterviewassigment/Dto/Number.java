package com.amos.velatechjavainterviewassigment.Dto;

import lombok.Value;

@Value
public class Number {
  private  int  length;
  private boolean luhn;

    private Number(int length, boolean luhn) {
        this.length = length;
        this.luhn = luhn;
    }

    public static Number createNumber(int length, boolean luhn) {
        return new Number(length, luhn);
    }
}
