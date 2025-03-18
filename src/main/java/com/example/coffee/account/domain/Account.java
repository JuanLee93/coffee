package com.example.coffee.account.domain;

import lombok.Getter;
import lombok.Setter;

public class Account {

    @Getter
    @Setter
    public static class Member{
        private int id;
        private String name;
        private String phone;
        private boolean isBuy;

        @Override
        public String toString() {
            return "Member{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", phone='" + phone + '\'' +
                    '}';
        }
    }

    @Getter
    @Setter
    public static class ChooseBuyerSelector{
        private int id;
        private String name;
    }

    @Getter
    @Setter
    public static class ChooseBuyerForInform{
        private String buyerInform;
        private String dateInform;
        private String message;
    }
}
