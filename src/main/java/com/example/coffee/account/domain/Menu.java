package com.example.coffee.account.domain;

import lombok.Getter;
import lombok.Setter;

public class Menu {

    @Getter
    @Setter
    public static class Drink{
        private int id;
        private boolean americano;
        private boolean choco;
        private boolean greenTea;
        private boolean chamomileTea;
        private boolean peppermintTea;
    }

}
