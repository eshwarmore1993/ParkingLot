package com.barclays;

public class Token {

    private int tokenNumber;
    private int cost;

    public Token(int tokenNumber, int cost) {
        this.tokenNumber = tokenNumber;
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Token token = (Token) o;

        return tokenNumber == token.tokenNumber;

    }

}
